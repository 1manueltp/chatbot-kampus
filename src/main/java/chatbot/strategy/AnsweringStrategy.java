package chatbot.strategy;


public interface AnsweringStrategy { // Membuat interface
    String getAnswer(String input); // Setiap strategi menjawab harus punya method
}
