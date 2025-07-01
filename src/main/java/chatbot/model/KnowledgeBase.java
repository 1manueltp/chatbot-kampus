package chatbot.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class KnowledgeBase {
    private static KnowledgeBase instance; // Memastikan hanya satu Knowledge Base saja yang digunakan selama program berjalan
    private final Map<String, String> faq = new HashMap<>(); // Menyimpan pertanyaan sebagai 'key', jawaban sebagai 'value'
    private final String filePath; // Menyimpan lokasi file JSON tempat FAQ disimpan

    // Memastikan hanya satu objek Knowledge Base yang terbuat, jika belum ada dibuat dulu, jika sudah ada lansgung dipakai ulang
    public static KnowledgeBase getInstance() { 
        if (instance == null) {
            instance = new KnowledgeBase("src/resources/faq.json");
        }
        return instance;
    }

    // Membaca file faq.json, setiap pertanyaan dan jawaban dimasukkan ke dalam faq
    private KnowledgeBase(String filePath) { 
        this.filePath = filePath;   
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject json = new JSONObject(content);
            for (String key : json.keySet()) {
                faq.put(key.toLowerCase(), json.getString(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Mencari jawaban
    public String findAnswer(String question) {
        String lowerQuestion = question.toLowerCase(); // Convert pertanyaan user ke huruf kecil
        for (String key : faq.keySet()) { 
            if (key.contains(lowerQuestion)) { // Mengecek apakah pertanyaan yang mengandung kata mirip
                return faq.get(key); // Jika ketemu mengembalikan jawaban
            }
        }
        return "Pertanyaan tidak ditemukan di basis FAQ."; // Jika tidak menampilkan pesan tersebut 
    }

    // Menambahkan FAQ baru (menambahkan pertanyaan dan jawaban baru ke map)
    public void addFAQ(String question, String answer) {
        faq.put(question.toLowerCase(), answer);
    }

    // Menyimpan ke file JSON (mengubah faq (MAP) ke format JSON, menyimpan hasilnya ke file faq.json)
    public void saveToFile() {
    try (PrintWriter out = new PrintWriter(filePath)) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        out.println(gson.toJson(faq));
        } catch (Exception e) {
        e.printStackTrace();
        }
    }

    // Getter untuk ambil semua FAQ (mengembalikan semua data FAQ)
    public Map<String, String> getFaqMap() {
        return faq;
    }
}


