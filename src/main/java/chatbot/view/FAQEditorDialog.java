package chatbot.view;

import chatbot.model.KnowledgeBase;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FAQEditorDialog extends Stage {
    // Inisialisasi komponen GUI
    private final TextArea displayArea = new TextArea();
    private final TextField questionField = new TextField();
    private final TextArea answerField = new TextArea();
    private final Button addButton = new Button("Tambah FAQ");
    private final Button saveButton = new Button("Simpan");

    // Mengambil instance tunggal dari KnowledgeBase (yang menyimpan dan mengelola data FAQ)
    private final KnowledgeBase kb = KnowledgeBase.getInstance();

    public FAQEditorDialog() {
        // Mengatur properti komponen 
        setTitle("Kelola FAQ"); // Judul
        initModality(Modality.APPLICATION_MODAL); // Mode dialog: harus ditutup sebelum kembali ke jendela utama 
        
        // Layout dan penempatan komponen
        questionField.setPrefWidth(400);
        answerField.setPrefWidth(400);
        answerField.setWrapText(true);
        answerField.setPrefRowCount(5);
        displayArea.setEditable(false);
        
        answerField.textProperty().addListener((obs, oldText, newText) -> {
            answerField.setScrollTop(Double.MAX_VALUE);
        });

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        displayArea.setEditable(false);
        
        // Isi data awal FAQ
        refreshFAQDisplay(); // Menampilkan semua pertanyaan - jawaban yang ada di KnowledgeBase saat dialog terbuka

        HBox inputBox = new HBox(10, new Label("Pertanyaan:"), questionField);
        VBox answerBox = new VBox(5, new Label("Jawaban:"), answerField);
        HBox buttonBox = new HBox(10, addButton, saveButton);

        root.getChildren().addAll(new Label("Daftar FAQ:"), displayArea, inputBox, answerBox, buttonBox);

        // Tombol tambah FAQ
        addButton.setOnAction(e -> {
            String q = questionField.getText().trim(); // Ambil input dari questionField
            String a = answerField.getText().trim(); // Ambil input dari answerField
            if (!q.isEmpty() && !a.isEmpty()) { 
                kb.addFAQ(q, a); // Tambahkan ke KnowledgeBase
                refreshFAQDisplay(); // Refresh tampilan
                questionField.clear(); // Kosongkan field input
                answerField.clear(); // Kosongkan field input
            }
        });

        // Tombol simpan
        saveButton.setOnAction(e -> {
            kb.saveToFile(); // Simpan semua FAQ ke file faq.json
            this.close(); // Tutup jendela editor
        });

        Scene scene = new Scene(root, 600, 400);
        setScene(scene);
    }

    // Mengambil semua FAQ dari Knowledgebase dan menampilkan dalam displayArea
    private void refreshFAQDisplay() {
        StringBuilder sb = new StringBuilder();
        kb.getFaqMap().forEach((q, a) -> {
            sb.append("Q: ").append(q).append("\nA: ").append(a).append("\n\n");
        });
        displayArea.setText(sb.toString());
    }
}
