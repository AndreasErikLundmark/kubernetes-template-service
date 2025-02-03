package alu.kubernetes.setup.Service;

import alu.kubernetes.setup.Model.Message;
import alu.kubernetes.setup.repository.MessageRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class MessageService {
    private Long lastMessageId;
    private MessageRepository messageRepository;
    private List<String> cachedMessages;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
        this.cachedMessages = getMessagesAsStringList();
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