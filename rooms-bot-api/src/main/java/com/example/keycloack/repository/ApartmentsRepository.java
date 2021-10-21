package com.example.keycloack.repository;

import com.example.keycloack.models.Apartments.Apartments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentsRepository extends MongoRepository<Apartments, String> {

    Apartments findByInternalId(Long id);

    @Query("{'type': ?0, 'location.locationName': ?1}")
    List<Apartments> findByTwoParams(String type, String city);

    @Query("{'type': ?0, 'location.locationName': ?1, 'price.value': {$gte: ?2, $lte: ?3}}")
    List<Apartments> findByThreeParams(String type, String city, int priceMin, int priceMax);

    @Query("{'type': ?0, 'location.locationName': ?1, 'price.value': {$gte: ?2, $lte: ?3}, 'rooms': ?4}")
    List<Apartments> findByFourParams(String type, String city, int priceMin, int priceMax, int rooms);

    @Query("{'type': ?0, 'location.locationName': ?1, 'price.value': {$gte: ?2, $lte: ?3}, 'rooms': ?4, " +
            "'location.subLocationName': ?5, 'location.metro.name': ?6}")
    List<Apartments> findBySixParams(String type, String city, int priceMin, int priceMax, int rooms, String subLocationName, String metro);

    @Query("{'type': ?0, 'location.locationName': ?1, 'price.value': {$gte: ?2, $lte: ?3}, 'rooms': ?4, 'location.metro.name': ?5}")
    List<Apartments> findByParamsMetro(String type, String city, int priceMin, int priceMax, int rooms, String metro);

    @Query("{'type': ?0, 'location.locationName': ?1, 'price.value': {$gte: ?2, $lte: ?3}, 'rooms': ?4, 'location.subLocationName': ?5}")
    List<Apartments> findByParamsSubLocationName(String type, String city, int priceMin, int priceMax, int rooms, String subLocationName);


    @Query("{'type': ?0, 'location.locationName': ?1, 'price.value': {$gte: ?2, $lte: ?3}, " +
            "'location.subLocationName': ?4, 'location.metro.name': ?5}")
    List<Apartments> findByNotRooms(String type, String city, int priceMin, int priceMax, String subLocationName, String metro);

    @Query("{'type': ?0, 'location.locationName': ?1, 'price.value': {$gte: ?2, $lte: ?3}, " +
            "'location.subLocationName': ?4}")
    List<Apartments> findByNotRoomsAndMetro(String type, String city, int priceMin, int priceMax, String subLocationName);

}
