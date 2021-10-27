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

    @Async
    public CompletableFuture<List<Apartments>> findAll() {
        return CompletableFuture.completedFuture(repository.findAll());
    }

    @Async
    public void delete(Apartments apartments) {
        repository.delete(apartments);
    }

    @Async
    public void save(Apartments apartments) {
        repository.save(apartments);
    }


    public Apartments findById(String id) {
        Optional<Apartments> apartments = repository.findById(id);
        return apartments.orElse(null);
    }

    @SneakyThrows
    public List<Apartments> findByTwoParams(String type, String city) {
        return repository.findByTwoParams(type, city).get();
    }

    @SneakyThrows
    public List<Apartments> findByThreeParams(String type, String city, int priceMin, int priceMax) {
        return repository.findByThreeParams(type, city, priceMin, priceMax).get();
    }

    @SneakyThrows
    public List<Apartments> findByFourParams(String type, String city, int priceMin, int priceMax, int rooms) {
        return repository.findByFourParams(type, city, priceMin, priceMax, rooms).get();
    }

    @SneakyThrows
    public List<Apartments> findBySixParams(String type, String city, int priceMin, int priceMax, int rooms, String subLocationName, String metro) {
        return repository.findBySixParams(type, city, priceMin, priceMax, rooms, subLocationName, metro).get();
    }

    @SneakyThrows
    public List<Apartments> findByParamsMetro(String type, String city, int priceMin, int priceMax, int rooms, String metro) {
        return repository.findByParamsMetro(type, city, priceMin, priceMax, rooms, metro).get();
    }

    @SneakyThrows
    public List<Apartments> findByParamsSubLocationName(String type, String city, int priceMin, int priceMax, int rooms, String subLocationName) {
        return repository.findByParamsSubLocationName(type, city, priceMin, priceMax, rooms, subLocationName).get();
    }

    @SneakyThrows
    public List<Apartments> findByNotRooms(String type, String city, int priceMin, int priceMax, String subLocationName, String metro) {
        return repository.findByNotRooms(type, city, priceMin, priceMax, subLocationName, metro).get();
    }

    @SneakyThrows
    public List<Apartments> findByNotRoomsAndMetro(String type, String city, int priceMin, int priceMax, String subLocationName) {
      return   repository.findByNotRoomsAndMetro(type, city, priceMin, priceMax, subLocationName).get();
    }

    public Apartments findByInternalId(Long id) {
        return repository.findByInternalId(id);
    }
}
