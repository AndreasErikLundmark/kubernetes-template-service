package alu.kubernetes.setup.Service;

import alu.kubernetes.setup.Model.Messages;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private Messages messages;
    private boolean first;

    public MessageService(Messages messages) {
        this.messages = messages;
        this.first = true;
    }

    public String getMessages() {
        String result = null;
        if (first) {
            result = messages.getFirst();
            first = false;
        }else{
            result = messages.getSecond();
            first = true;
        }
        return result;
    }


}
