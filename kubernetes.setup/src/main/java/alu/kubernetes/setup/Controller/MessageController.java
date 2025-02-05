package alu.kubernetes.setup.Controller;

import alu.kubernetes.setup.Model.Message;
import alu.kubernetes.setup.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Tag(name = "Message Service API", description = "Get random messages about kubernetes")
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

    @Operation(
            summary = "Gets a random message from the database",
            description = "response body with String")
    @GetMapping("/message")
    public ResponseEntity<String> getMessage() {
        String message = messageService.getFreshMessage();
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    @Operation(
            summary = "Add a custom message to database",
            description = "request body")
    @PostMapping("/message/new")
    public ResponseEntity<String> addMessage(@RequestBody Message message) {
        messageService.createMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).body("Message added");
    }


}