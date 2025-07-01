package chatbot.view;

import chatbot.controller.ChatController;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.StandardOpenOption;

public class MainView extends VBox {
    // Komponen GUI
    private final TextArea chatArea = new TextArea();
    private final TextField inputField = new TextField();
    private final Button sendButton = new Button("Kirim");
    private final Button clearButton = new Button("Clear");
    private final ChatController controller = new ChatController();

    private final TreeItem<String> rootItem = new TreeItem<>("Riwayat Pertanyaan");
    private final TreeView<String> treeView = new TreeView<>(rootItem);
    private final Map<String, TreeItem<String>> dateGroups = new HashMap<>();
    private final List<String> answers = new ArrayList<>();
    private final List<String> flatQuestions = new ArrayList<>();
    private String lastTanggal = "";

    public MainView() {
        // Menu Admin
        MenuBar menuBar = new MenuBar();
        Menu adminMenu = new Menu("Admin");
        MenuItem editFAQItem = new MenuItem("Kelola FAQ");
        adminMenu.getItems().add(editFAQItem);
        menuBar.getMenus().addAll(adminMenu);

        editFAQItem.setOnAction(e -> { // Menu untuk membuka editor FAQ 
            FAQEditorDialog dialog = new FAQEditorDialog();
            dialog.showAndWait();
        });

        VBox mainPanel = new VBox(10);
        // Panel Chat (berisi tampilan, kolom input, tombol kirim/hapus)
        chatArea.setEditable(false);
        inputField.setPrefWidth(400);
        HBox inputBox = new HBox(10, inputField, sendButton, clearButton);
        mainPanel.getChildren().addAll(chatArea, inputBox);

        // TreeView menampilkan pertanyaan dalam grup berdasarkan tanggal (saat diklik akan menampilkan jawaban dalam bentuk popup(alert))
        treeView.setShowRoot(false);
        treeView.setPrefWidth(300);

        treeView.setCellFactory(tv -> new TreeCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item);
                setStyle("");
                if (!empty && getTreeItem().isLeaf() && getTreeItem().getParent() != null) {
                    setStyle("-fx-text-fill: black;");
                } else if (!empty) {
                    setStyle("-fx-text-fill: black; -fx-font-style: italic;");
                }
            }
        });

        // Lihat Jawaban, saat user klik pertanyaan di TreeView, munculkan popup alert berisi jawaban lengkap
        treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            TreeItem<String> selectedItem = treeView.getSelectionModel().getSelectedItem();
            if (selectedItem != null && selectedItem.getParent() != null && selectedItem.isLeaf()) {
                String rawText = selectedItem.getValue();
                String originalQuestion = rawText.split("\n")[0]; // hanya ambil pertanyaan
                int index = flatQuestions.indexOf(originalQuestion);
                if (index >= 0 && index < answers.size()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Jawaban");
                    alert.setHeaderText("Jawaban untuk: " + originalQuestion);
                    alert.setContentText(answers.get(index));
                    alert.showAndWait();
                }
            }
        });
        
        // Label Sidebar Riwayat
        Label historyLabel = new Label("Riwayat Chat");
        historyLabel.setStyle("-fx-font-wight: bold, -fx-padding: 5 0 5 10");
        historyLabel.setMaxWidth(Double.MAX_VALUE);
        historyLabel.setAlignment(Pos.CENTER);
        VBox sidebar = new VBox(5, historyLabel, treeView);
        sidebar.setPrefWidth(300);
        
        // Layout Utama
        HBox contentBox = new HBox(10, sidebar, mainPanel);
        VBox.setVgrow(contentBox, Priority.ALWAYS);
        HBox.setHgrow(mainPanel, Priority.ALWAYS);
        getChildren().addAll(menuBar, contentBox);

        parseChatHistory();
        sendButton.setOnAction(e -> sendMessage());
        clearButton.setOnAction(e -> chatArea.clear());
        inputField.setOnAction(e -> sendMessage());
    }

    // Fungsi Preview Jawaban dengan maks. 50 karakter di TreeView
    private String getPreview(String answer) {
        return answer.length() > 50 ? answer.substring(0, 47) + "..." : answer;
    }
    
    // Mengirim pertanyaan
    private void sendMessage() {
        String input = inputField.getText().trim();
        if (!input.isEmpty()) {
            String response = controller.processInput(input);
            chatArea.appendText("Pertanyaan: " + input + "\nJawaban: " + response + "\n\n");
            inputField.clear();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("id", "ID"));
            String tanggal = LocalDateTime.now().format(formatter);
            String tanggalLine = "[" + tanggal + "]";

            try {
                String log = tanggalLine + "\n" +
                        "Q: " + input + "\n" +
                        "A: " + response + "\n\n";
                Files.write(Paths.get("riwayat_chat.txt"), log.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.err.println("Gagal menyimpan riwayat chat: " + e.getMessage());
            }

            TreeItem<String> dateNode = dateGroups.get(tanggalLine);
            if (dateNode == null) {
                dateNode = new TreeItem<>(tanggalLine);
                dateGroups.put(tanggalLine, dateNode);
                rootItem.getChildren().add(dateNode);
            }

            String preview = getPreview(response);
            TreeItem<String> questionNode = new TreeItem<>(input + "\n  → " + preview);
            dateNode.getChildren().add(questionNode);

            flatQuestions.add(input);
            answers.add(response);
        }
    }

    // Menampilkan riwayat chat
    private void parseChatHistory() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("riwayat_chat.txt"));
            String tanggal = "";
            String question = "";

            for (String line : lines) {
                line = line.trim();
                if (line.startsWith("[") && line.endsWith("]")) {
                    tanggal = line;
                    if (!dateGroups.containsKey(tanggal)) {
                        TreeItem<String> dateNode = new TreeItem<>(tanggal);
                        dateGroups.put(tanggal, dateNode);
                        rootItem.getChildren().add(dateNode);
                    }
                } else if (line.startsWith("Q:")) {
                    question = line.substring(2).trim();
                } else if (line.startsWith("A:")) {
                    String answer = line.substring(2).trim();
                    TreeItem<String> dateNode = dateGroups.get(tanggal);
                    if (dateNode != null) {
                        String preview = getPreview(answer);
                        TreeItem<String> qNode = new TreeItem<>(question + "\n → " + preview);
                        dateNode.getChildren().add(qNode);
                        flatQuestions.add(question);
                        answers.add(answer);
                    }
                }
            }
        } catch (IOException e) {
            TreeItem<String> errorItem = new TreeItem<>("[Riwayat tidak dapat dimuat]");
            rootItem.getChildren().add(errorItem);
        }
    }
}
