package chatbot.strategy;

import chatbot.model.KnowledgeBase;

public class RuleBasedStrategy implements AnsweringStrategy {

    @Override
    public String getAnswer(String question) {
        return KnowledgeBase.getInstance().findAnswer(question); // Mengambil satu - satunya instance dari KnowledgeBase, memanggil method findAnswer, mengembalikan jawaban berdasarkan pencocokan pertanyaan
    }
}
