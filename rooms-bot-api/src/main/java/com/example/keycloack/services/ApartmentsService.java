package com.example.keycloack.services;

import com.example.keycloack.models.Apartments.Apartments;
import com.example.keycloack.repository.ApartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return repository.findById(id).get();
    }

    public List<Apartments> findByTwoParams(String type, String city) {
        return repository.findByTwoParams(type, city);
    }

    public List<Apartments> findByThreeParams(String type, String city, int priceMin, int priceMax) {
        return repository.findByThreeParams(type, city, priceMin, priceMax);
    }

    public List<Apartments> findByFourParams(String type, String city, int priceMin, int priceMax, int rooms) {
        return repository.findByFourParams(type, city, priceMin, priceMax, rooms);
    }

    public List<Apartments> findBySixParams(String type, String city, int priceMin, int priceMax, int rooms, String subLocationName, String metro) {
        return repository.findBySixParams(type, city, priceMin, priceMax, rooms, subLocationName, metro);
    }

    public List<Apartments> findByParamsMetro(String type, String city, int priceMin, int priceMax, int rooms, String metro) {
        return repository.findByParamsMetro(type, city, priceMin, priceMax, rooms, metro);
    }

    public List<Apartments> findByParamsSubLocationName(String type, String city, int priceMin, int priceMax, int rooms, String subLocationName) {
        return repository.findByParamsSubLocationName(type, city, priceMin, priceMax, rooms, subLocationName);
    }

    public List<Apartments> findByNotRooms(String type, String city, int priceMin, int priceMax, String subLocationName, String metro) {
        return repository.findByNotRooms(type, city, priceMin, priceMax, subLocationName, metro);
    }

    public List<Apartments> findByNotRoomsAndMetro(String type, String city, int priceMin, int priceMax, String subLocationName) {
      return   repository.findByNotRoomsAndMetro(type, city, priceMin, priceMax, subLocationName);
    }

    public Apartments findByInternalId(Long id) {
        return repository.findByInternalId(id);
    }
}
