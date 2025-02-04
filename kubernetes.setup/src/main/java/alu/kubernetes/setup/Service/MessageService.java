package alu.kubernetes.setup.Service;

import alu.kubernetes.setup.Model.Message;
import alu.kubernetes.setup.repository.MessageRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class MessageService {

    private Long lastMessageId;
    private MessageRepository messageRepository;
    private List<String> cachedMessages;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @PostConstruct
    public void init() {
        waitForDatabaseConnection();
        this.cachedMessages = getMessagesAsStringList();
    }

    private void waitForDatabaseConnection() {
        int attempts = 0;
        while (attempts < 5) {
            try {
                messageRepository.findAll();
                System.out.println("Database connection established.");
                return;
            } catch (Exception e) {
                System.out.println("Database not ready, retrying... (" + (attempts + 1) + "/5)");
                attempts++;
                try {
                    Thread.sleep(5000);  // Retry every 5 seconds
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();  // Restore the interrupt flag
                }
            }
        }
        throw new RuntimeException("Database connection not ready after 5 attempts.");
    }

    public Iterable<Message> getMessages() {
        return messageRepository.findAll();
    }

    public Message getMessage(String id) {
        return messageRepository.findById(Long.parseLong(id)).orElse(null);
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public Message updateMessage(String id, Message message) {
        message.setId(Long.parseLong(id));
        return messageRepository.save(message);
    }

    public void deleteMessage(String id) {
        messageRepository.deleteById(Long.parseLong(id));
    }

    private List<String> getMessagesAsStringList() {
        List<Message> messages = (List<Message>) getMessages();
        return messages.stream()
                .map(Message::getMessage)
                .toList();
    }

    public String getFreshMessage() {
        if (cachedMessages == null || cachedMessages.isEmpty()) {
            // Refresh cachedMessages if it's null or empty
            cachedMessages = getMessagesAsStringList();
        }

        if (!cachedMessages.isEmpty()) {
            Random rand = new Random();
            int randomIndex = rand.nextInt(cachedMessages.size());
            return cachedMessages.get(randomIndex);
        }

        return getMessagesAsStringList().stream()
                .findFirst()
                .orElse("No messages available");
    }
}
