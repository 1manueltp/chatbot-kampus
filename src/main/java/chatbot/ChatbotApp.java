package chatbot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import chatbot.view.MainView;


public class ChatbotApp extends Application {
    // Method utama yang dipanggil JavaFX saat aplikasi dimulai
    @Override
    public void start(Stage primaryStage) {
        MainView mainView = new MainView(); // Membuat instance tampilan utama dari GUI chatbot
        Scene scene = new Scene(mainView, 1000, 400); // Bungkus tampilan ke dalam Scene (kanvas GUI)
        primaryStage.setTitle("Chatbot Kampus Pintar"); // Mengatur judul jendela aplikasi
        primaryStage.setScene(scene); // Menetapkan tampilan scene ke dalam jendela utama
        primaryStage.show(); // Menampilkan jendela aplikasi ke pengguna
    }

    public static void main(String[] args) {
        launch(args);
    }
}
