package com.example.keycloack.services;

import com.example.keycloack.models.Apartments.Apartments;
import com.example.keycloack.repository.ApartmentsRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ApartmentsService {

    private final ApartmentsRepository repository;

    @Autowired
    public ApartmentsService(ApartmentsRepository repository) {
        this.repository = repository;
    }


    public List<Apartments> findAll() {
        return repository.findAll();
    }


    public void delete(Apartments apartments) {
        repository.delete(apartments);
    }

    public void save(Apartments apartments) {
        repository.save(apartments);
    }


    public Apartments findById(String id) {
        Optional<Apartments> apartments = repository.findById(id);
        return apartments.orElse(null);
    }

    public Apartments findByInternalId(Long id) {
        return repository.findByInternalId(id);
    }


}
