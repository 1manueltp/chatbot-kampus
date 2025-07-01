package chatbot.controller;

import chatbot.model.QuestionProcessor;
import chatbot.strategy.AnsweringStrategy;
import chatbot.strategy.RuleBasedStrategy;

public class ChatController {
    private QuestionProcessor processor;

    public ChatController() {
        this.processor = new QuestionProcessor(new RuleBasedStrategy()); // Mengatur strategi awal sebagai Rule-Based Strategy
    }

    public void setStrategy(String strategyName) {
        processor.setStrategy(new RuleBasedStrategy());
    }   

    public String processInput(String input) { // Menerima input dari user
        return processor.process(input); // Memanggil QuestionProcessor untuk memproses dan mencari jawaban
    }
}
