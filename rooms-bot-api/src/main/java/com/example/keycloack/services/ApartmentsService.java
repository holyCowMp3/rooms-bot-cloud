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

    public Apartments findByInternalId(Long id) {
        return repository.findByInternalId(id);
    }

    @SneakyThrows
    public List<Apartments> findByTypeCityCategory(String type, String city, String category) {
        return repository.findByTypeCityCategory(type, city, category).get();
    }

    @SneakyThrows
    public List<Apartments> findByTypeCityCategoryPrice(String type, String city, String category, int priceMin, int priceMax) {
        return repository.findByTypeCityCategoryPrice(type, city, category, priceMin, priceMax).get();
    }

    @SneakyThrows
    public List<Apartments> findByTypeCityCategoryPriceRooms(String type, String city, String category, int priceMin, int priceMax, int rooms) {
        return repository.findByTypeCityCategoryPriceRooms(type, city, category, priceMin, priceMax, rooms).get();
    }

    @SneakyThrows
    public List<Apartments> findByTypeCityCategoryPriceRoomsRegionMetro(String type, String city, String category, int priceMin, int priceMax, int rooms, String subLocationName, String metro) {
        return repository.findByTypeCityCategoryPriceRoomsRegionMetro(type, city, category, priceMin, priceMax, rooms, subLocationName, metro).get();
    }

    @SneakyThrows
    public List<Apartments> findByTypeCityCategoryPriceRoomsMetro(String type, String city, String category, int priceMin, int priceMax, int rooms, String metro) {
        return repository.findByTypeCityCategoryPriceRoomsMetro(type, city, category, priceMin, priceMax, rooms, metro).get();
    }

    @SneakyThrows
    public List<Apartments> findByTypeCityCategoryPriceRoomsRegion(String type, String city, String category, int priceMin, int priceMax, int rooms, String subLocationName) {
        return repository.findByTypeCityCategoryPriceRoomsRegion(type, city, category, priceMin, priceMax, rooms, subLocationName).get();
    }

    @SneakyThrows
    public List<Apartments> findByTypeCityCategoryPriceRegionMetro(String type, String city, String category, int priceMin, int priceMax, String subLocationName, String metro) {
        return repository.findByTypeCityCategoryPriceRegionMetro(type, city, category, priceMin, priceMax, subLocationName, metro).get();
    }

    @SneakyThrows
    public List<Apartments> findByTypeCityCategoryPriceRegion(String type, String city,String category, int priceMin, int priceMax, String subLocationName) {
        return repository.findByTypeCityCategoryPriceRegion(type, city, category, priceMin, priceMax, subLocationName).get();
    }

    @SneakyThrows
    public List<Apartments> findByTypeCityCategoryPriceMetro(String type, String city, String category, int priceMin, int priceMax, String metro) {
        return repository.findByTypeCityCategoryPriceMetro(type, city, category, priceMin, priceMax, metro).get();
    }




    @SneakyThrows
    public List<Apartments> findByTypeCity(String type, String city) {
        return repository.findByTypeCity(type, city).get();
    }

    @SneakyThrows
    public List<Apartments> findByTypeCityPrice(String type, String city, int priceMin, int priceMax) {
        return repository.findByTypeCityPrice(type, city, priceMin, priceMax).get();
    }

    @SneakyThrows
    public List<Apartments> findByTypeCityPriceRooms(String type, String city, int priceMin, int priceMax, int rooms) {
        return repository.findByTypeCityPriceRooms(type, city, priceMin, priceMax, rooms).get();
    }

    @SneakyThrows
    public List<Apartments> findByTypeCityPriceRoomsRegionMetro(String type, String city, int priceMin, int priceMax, int rooms, String subLocationName, String metro) {
        return repository.findByTypeCityPriceRoomsRegionMetro(type, city, priceMin, priceMax, rooms, subLocationName, metro).get();
    }

    @SneakyThrows
    public List<Apartments> findByTypeCityPriceRoomsMetro(String type, String city,  int priceMin, int priceMax, int rooms, String metro) {
        return repository.findByTypeCityPriceRoomsMetro(type, city,  priceMin, priceMax, rooms, metro).get();
    }

    @SneakyThrows
    public List<Apartments> findByTypeCityPriceRoomsRegion(String type, String city,  int priceMin, int priceMax, int rooms, String subLocationName) {
        return repository.findByTypeCityPriceRoomsRegion(type, city, priceMin, priceMax, rooms, subLocationName).get();
    }

    @SneakyThrows
    public List<Apartments> findByTypeCityPriceRegionMetro(String type, String city,  int priceMin, int priceMax, String subLocationName, String metro) {
        return repository.findByTypeCityPriceRegionMetro(type, city, priceMin, priceMax, subLocationName, metro).get();
    }

    @SneakyThrows
    public List<Apartments> findByTypeCityPriceRegion(String type, String city, int priceMin, int priceMax, String subLocationName) {
        return repository.findByTypeCityPriceRegion(type, city, priceMin, priceMax, subLocationName).get();
    }

    @SneakyThrows
    public List<Apartments> findByTypeCityPriceMetro(String type, String city,  int priceMin, int priceMax, String metro) {
        return repository.findByTypeCityPriceMetro(type, city, priceMin, priceMax, metro).get();
    }

}
