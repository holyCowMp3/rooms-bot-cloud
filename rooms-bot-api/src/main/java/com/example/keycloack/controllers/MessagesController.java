package com.example.keycloack.controllers;

import com.example.keycloack.models.Messages;
import com.example.keycloack.services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/message")
public class MessagesController {

    private final MessageService messageService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Messages> addMessage(@RequestBody Messages messages) {
        return ResponseEntity.ok(messageService.save(messages));
    }

    @GetMapping("/find")
    @ResponseBody
    public ResponseEntity<Messages> find() {
        List<Messages> messagesList = messageService.findAll();
        System.out.println(messagesList);

        if (messagesList.size() == 0)
            return ResponseEntity.ok(new Messages());

        return ResponseEntity.ok(messagesList.get(messagesList.size() - 1));
    }
}
