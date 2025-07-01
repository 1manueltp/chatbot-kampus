package chatbot.model;

import java.time.LocalDateTime;

public class ChatMessage {
    private String sender;
    private String message;
    private LocalDateTime timestamp;

    public ChatMessage(String sender, String message) {
        this.sender = sender;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getSender() { // Mengambil nilai 
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}