package chatbot.model;

import chatbot.strategy.AnsweringStrategy;

public class QuestionProcessor {
    private AnsweringStrategy strategy; // Menyimpan strategi yang digunakan untuk menjawab(rule-based)

    // Ketika objek QuestionProcessor terbuat langsung menetapkan strategy
    public QuestionProcessor(AnsweringStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(AnsweringStrategy strategy) {
        this.strategy = strategy;
    }

    public String process(String question) { // Menerima pertanyaan (tipe data String)
        return strategy.getAnswer(question); // Memanggil srtategi yang digunakan 
    }
}
