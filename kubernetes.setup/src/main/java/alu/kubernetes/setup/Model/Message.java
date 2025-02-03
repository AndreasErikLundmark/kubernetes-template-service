package alu.kubernetes.setup.Model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @Column(columnDefinition = "MESSAGE")
    private String message;

    public Message(String message) {
        this.message = message;
    }

    public Message() {

    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
