package alu.kubernetes.setup.Controller;

import alu.kubernetes.setup.Model.Message;
import alu.kubernetes.setup.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MessageController {


    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        try {
            this.messageService = messageService;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping("/message")
    public ResponseEntity<String> getMessage() {
        String message = messageService.getFreshMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @PostMapping("/message/new")
    public ResponseEntity<String> addMessage(@RequestBody Message message) {
        messageService.createMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).body("Message added");
    }

}