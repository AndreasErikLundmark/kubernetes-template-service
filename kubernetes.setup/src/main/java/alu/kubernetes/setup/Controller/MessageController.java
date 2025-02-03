package alu.kubernetes.setup.Controller;

import alu.kubernetes.setup.Model.Message;
import alu.kubernetes.setup.Service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/message")
    public ResponseEntity<String> getMessage() {
        String message = messageService.getFreshMessage();
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}