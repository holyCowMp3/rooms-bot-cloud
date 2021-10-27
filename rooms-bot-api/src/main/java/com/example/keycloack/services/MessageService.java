package com.example.keycloack.services;

import com.example.keycloack.models.Messages;
import com.example.keycloack.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository repository;

    @Autowired
    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public Messages save(Messages messages) {
        return repository.save(messages);
    }

    public Messages findById(String id) {
        return repository.findById(id).get();
    }

    public List<Messages> findAll() {
        return repository.findAll();
    }
}
