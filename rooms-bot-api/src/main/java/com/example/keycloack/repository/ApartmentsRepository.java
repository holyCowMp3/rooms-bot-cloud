package com.example.keycloack.repository;

import com.example.keycloack.models.Apartments.Apartments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public interface ApartmentsRepository extends MongoRepository<Apartments, String> {

    Apartments findByInternalId(Long id);

    //1
    @Query("{'type': ?0, 'location.locationName': ?1, 'category': ?2}")
    @Async
    CompletableFuture<List<Apartments>> findByTypeCityCategory(String type, String city, String category);

    //2
    @Query("{'type': ?0, 'location.locationName': ?1, 'category': ?2, 'price.value': {$gte: ?3, $lte: ?4}}")
    @Async
    CompletableFuture<List<Apartments>> findByTypeCityCategoryPrice(String type, String city, String category, int priceMin, int priceMax);

    //3
    @Query("{'type': ?0, 'location.locationName': ?1, 'category': ?2, 'price.value': {$gte: ?3, $lte: ?4}, 'rooms': ?5}")
    @Async
    CompletableFuture<List<Apartments>> findByTypeCityCategoryPriceRooms(String type, String city, String category, int priceMin, int priceMax, int rooms);

    //4
    @Query("{'type': ?0, 'location.locationName': ?1, 'category': ?2, 'price.value': {$gte: ?3, $lte: ?4}, 'rooms': ?5, " +
            "'location.subLocationName': ?6, 'location.metro.name': ?7}")
    @Async
    CompletableFuture<List<Apartments>> findByTypeCityCategoryPriceRoomsRegionMetro(String type, String city, String category, int priceMin, int priceMax, int rooms, String subLocationName, String metro);

    //5
    @Query("{'type': ?0, 'location.locationName': ?1, 'category': ?2, 'price.value': {$gte: ?3, $lte: ?4}, 'rooms': ?5, 'location.metro.name': ?6}")
    @Async
    CompletableFuture<List<Apartments>> findByTypeCityCategoryPriceRoomsMetro(String type, String city, String category, int priceMin, int priceMax, int rooms, String metro);

    //6
    @Query("{'type': ?0, 'location.locationName': ?1, 'category': ?2, 'price.value': {$gte: ?3, $lte: ?4}, 'rooms': ?5, 'location.subLocationName': ?6}")
    @Async
    CompletableFuture<List<Apartments>> findByTypeCityCategoryPriceRoomsRegion(String type, String city, String category, int priceMin, int priceMax, int rooms, String subLocationName);

    //7
    @Query("{'type': ?0, 'location.locationName': ?1, 'category': ?2, 'price.value': {$gte: ?3, $lte: ?4}, " +
            "'location.subLocationName': ?5, 'location.metro.name': ?6}")
    @Async
    CompletableFuture<List<Apartments>> findByTypeCityCategoryPriceRegionMetro(String type, String city, String category, int priceMin, int priceMax, String subLocationName, String metro);

    //8
    @Query("{'type': ?0, 'location.locationName': ?1, 'category': ?2, 'price.value': {$gte: ?3, $lte: ?4}, " +
            "'location.subLocationName': ?5}")
    @Async
    CompletableFuture<List<Apartments>> findByTypeCityCategoryPriceRegion(String type, String city, String category, int priceMin, int priceMax, String subLocationName);

    //9
    @Query("{'type': ?0, 'location.locationName': ?1, 'category': ?2, 'price.value': {$gte: ?3, $lte: ?4}, 'location.metro.name': ?5}")
    @Async
    CompletableFuture<List<Apartments>> findByTypeCityCategoryPriceMetro(String type, String city, String category, int priceMin, int priceMax, String metro);



    //1
    @Query("{'type': ?0, 'location.locationName': ?1}")
    @Async
    CompletableFuture<List<Apartments>> findByTypeCity(String type, String city);

    //2
    @Query("{'type': ?0, 'location.locationName': ?1, 'price.value': {$gte: ?2, $lte: ?3}}")
    @Async
    CompletableFuture<List<Apartments>> findByTypeCityPrice(String type, String city, int priceMin, int priceMax);

    //3
    @Query("{'type': ?0, 'location.locationName': ?1, 'price.value': {$gte: ?2, $lte: ?3}, 'rooms': ?4}")
    @Async
    CompletableFuture<List<Apartments>> findByTypeCityPriceRooms(String type, String city, int priceMin, int priceMax, int rooms);

    //4
    @Query("{'type': ?0, 'location.locationName': ?1, 'price.value': {$gte: ?2, $lte: ?3}, 'rooms': ?4, " +
            "'location.subLocationName': ?5, 'location.metro.name': ?6}")
    @Async
    CompletableFuture<List<Apartments>> findByTypeCityPriceRoomsRegionMetro(String type, String city, int priceMin, int priceMax, int rooms, String subLocationName, String metro);

    //5
    @Query("{'type': ?0, 'location.locationName': ?1, 'price.value': {$gte: ?2, $lte: ?3}, 'rooms': ?4, 'location.metro.name': ?5}")
    @Async
    CompletableFuture<List<Apartments>> findByTypeCityPriceRoomsMetro(String type, String city,  int priceMin, int priceMax, int rooms, String metro);

    //6
    @Query("{'type': ?0, 'location.locationName': ?1, 'price.value': {$gte: ?2, $lte: ?3}, 'rooms': ?4, 'location.subLocationName': ?5}")
    @Async
    CompletableFuture<List<Apartments>> findByTypeCityPriceRoomsRegion(String type, String city, int priceMin, int priceMax, int rooms, String subLocationName);

    //7
    @Query("{'type': ?0, 'location.locationName': ?1,  'price.value': {$gte: ?2, $lte: ?3}, " +
            "'location.subLocationName': ?4, 'location.metro.name': ?5}")
    @Async
    CompletableFuture<List<Apartments>> findByTypeCityPriceRegionMetro(String type, String city, int priceMin, int priceMax, String subLocationName, String metro);

    //8
    @Query("{'type': ?0, 'location.locationName': ?1, 'price.value': {$gte: ?2, $lte: ?3}, " +
            "'location.subLocationName': ?4}")
    @Async
    CompletableFuture<List<Apartments>> findByTypeCityPriceRegion(String type, String city, int priceMin, int priceMax, String subLocationName);

    //9
    @Query("{'type': ?0, 'location.locationName': ?1,  'price.value': {$gte: ?2, $lte: ?3}, 'location.metro.name': ?4}")
    @Async
    CompletableFuture<List<Apartments>> findByTypeCityPriceMetro(String type, String city, int priceMin, int priceMax, String metro);

}
