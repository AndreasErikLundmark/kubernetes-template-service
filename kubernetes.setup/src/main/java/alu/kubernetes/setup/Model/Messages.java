package alu.kubernetes.setup.Model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Messages {

    private static final String FIRST = "Heureka kubernetes setup working!!!!";
    private static final String SECOND = "It is so wonderful with kubernetes";

    private ArrayList<String> messages;

    public Messages() {
        messages = new ArrayList<>();
        messages.add(FIRST);
        messages.add(SECOND);
    }

    public String getFirst() {
        return messages.get(0);
    }
    public String getSecond() {
        return messages.get(1);
    }


}
