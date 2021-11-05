package com.example.keycloack.repository;

import com.example.keycloack.models.Apartments.Apartments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentsRepository extends JpaRepository<Apartments, Long> {

    Apartments findByInternalId(Long id);

    //1
    @Query(value = "SELECT a FROM Apartments a WHERE a.type = :type and a.location.locationName = :city and a.category = :category")
    List<Apartments> findByTypeCityCategory(@Param("type") String type,
                                            @Param("city") String city,
                                            @Param("category") String category);

    //2
    @Query("SELECT a FROM Apartments a WHERE a.type = :type and a.location.locationName = :city and a.category = :category and a.price.value BETWEEN :priceMin and :priceMax")
    List<Apartments> findByTypeCityCategoryPrice(@Param("type") String type,
                                                 @Param("city") String city,
                                                 @Param("category") String category,
                                                 @Param("priceMin") long priceMin,
                                                 @Param("priceMax") long priceMax);

    //3
    @Query("SELECT a FROM Apartments a WHERE a.type = :type and a.location.locationName = :city and a.category = :category and a.price.value BETWEEN :priceMin and :priceMax" +
            " and a.location.subLocationName = :subLocationName and a.location.metro.name = :metro")
    List<Apartments> findByTypeCityCategoryPriceRegionMetro(@Param("type") String type,
                                                            @Param("city") String city,
                                                            @Param("category") String category,
                                                            @Param("priceMin") long priceMin,
                                                            @Param("priceMax") long priceMax,
                                                            @Param("subLocationName") String subLocationName,
                                                            @Param("metro") String metro);

    //4
    @Query("SELECT a FROM Apartments a WHERE  a.type = :type and a.location.locationName = :city and a.category = :category and a.price.value BETWEEN :priceMin and :priceMax" +
            " and a.location.metro.name = :metro")
    List<Apartments> findByTypeCityCategoryPriceMetro(@Param("type") String type,
                                                      @Param("city") String city,
                                                      @Param("category") String category,
                                                      @Param("priceMin") long priceMin,
                                                      @Param("priceMax") long priceMax,
                                                      @Param("metro") String metro);

    //5
    @Query("SELECT a FROM Apartments a WHERE a.type = :type and a.location.locationName = :city and a.category = :category and a.price.value BETWEEN :priceMin and :priceMax" +
            " and a.location.subLocationName = :subLocationName")
    List<Apartments> findByTypeCityCategoryPriceRegion(@Param("type") String type,
                                                       @Param("city") String city,
                                                       @Param("category") String category,
                                                       @Param("priceMin") long priceMin,
                                                       @Param("priceMax") long priceMax,
                                                       @Param("subLocationName") String subLocationName);


    //    //------------------------------------------------
//2
    @Query("SELECT a FROM Apartments a WHERE a.type = :type and a.location.locationName = :city and a.category = :category and a.price.value BETWEEN :priceMin and :priceMax" +
            " and a.rooms = :rooms")
    List<Apartments> findByTypeCityCategoryPriceRooms(@Param("type") String type,
                                                      @Param("city") String city,
                                                      @Param("category") String category,
                                                      @Param("priceMin") long priceMin,
                                                      @Param("priceMax") long priceMax,
                                                      @Param("rooms") int rooms);

    //3
    @Query("SELECT a FROM Apartments a WHERE a.type = :type and a.location.locationName = :city and a.category = :category and a.price.value BETWEEN :priceMin and :priceMax" +
            " and a.rooms = :rooms and a.location.subLocationName = :subLocationName and a.location.metro.name = :metro")
    List<Apartments> findByTypeCityCategoryPriceRoomsRegionMetro(@Param("type") String type,
                                                                 @Param("city") String city,
                                                                 @Param("category") String category,
                                                                 @Param("priceMin") long priceMin,
                                                                 @Param("priceMax") long priceMax,
                                                                 @Param("rooms") int rooms,
                                                                 @Param("subLocationName") String subLocationName,
                                                                 @Param("metro") String metro);

    //4
    @Query("SELECT a FROM Apartments a WHERE a.type = :type and a.location.locationName = :city and a.category = :category and a.price.value BETWEEN :priceMin and :priceMax" +
            " and a.rooms = :rooms and a.location.metro.name = :metro")
    List<Apartments> findByTypeCityCategoryPriceRoomsMetro(@Param("type") String type,
                                                           @Param("city") String city,
                                                           @Param("category") String category,
                                                           @Param("priceMin") long priceMin,
                                                           @Param("priceMax") long priceMax,
                                                           @Param("rooms") int rooms,
                                                           @Param("metro") String metro);

    //5
    @Query("SELECT a FROM Apartments a WHERE a.type = :type and a.location.locationName = :city and a.category = :category and a.price.value BETWEEN :priceMin and :priceMax" +
            " and a.rooms = :rooms and a.location.subLocationName = :subLocationName")
    List<Apartments> findByTypeCityCategoryPriceRoomsRegion(@Param("type") String type,
                                                            @Param("city") String city,
                                                            @Param("category") String category,
                                                            @Param("priceMin") long priceMin,
                                                            @Param("priceMax") long priceMax,
                                                            @Param("rooms") int rooms,
                                                            @Param("subLocationName") String subLocationName);
    //------------------------------------------------


    //1
    @Query("SELECT a FROM Apartments a WHERE a.type = :type and a.location.locationName = :city")
    List<Apartments> findByTypeCity(@Param("type") String type,
                                    @Param("city") String city);

    //2
    @Query("SELECT a FROM Apartments a WHERE a.type = :type and a.location.locationName = :city and a.price.value BETWEEN :priceMin and :priceMax")
    List<Apartments> findByTypeCityPrice(@Param("type") String type,
                                         @Param("city") String city,
                                         @Param("priceMin") long priceMin,
                                         @Param("priceMax") long priceMax);

    //3
    @Query("SELECT a FROM Apartments a WHERE a.type = :type and a.location.locationName = :city and a.price.value BETWEEN :priceMin and :priceMax" +
            " and a.rooms = :rooms")
    List<Apartments> findByTypeCityPriceRooms(@Param("type") String type,
                                              @Param("city") String city,
                                              @Param("priceMin") long priceMin,
                                              @Param("priceMax") long priceMax,
                                              @Param("rooms") int rooms);

    //4
    @Query("SELECT a FROM Apartments a WHERE a.type = :type and a.location.locationName = :city and a.price.value BETWEEN :priceMin and :priceMax" +
            " and a.rooms = :rooms and a.location.subLocationName = :subLocationName and a.location.metro.name = :metro")
    List<Apartments> findByTypeCityPriceRoomsRegionMetro(@Param("type") String type,
                                                         @Param("city") String city,
                                                         @Param("priceMin") long priceMin,
                                                         @Param("priceMax") long priceMax,
                                                         @Param("rooms") int rooms,
                                                         @Param("subLocationName") String subLocationName,
                                                         @Param("metro") String metro);

    //5
    @Query("SELECT a FROM Apartments a WHERE a.type = :type and a.location.locationName = :city and a.price.value BETWEEN :priceMin and :priceMax" +
            " and a.rooms = :rooms and a.location.metro.name = :metro")
    List<Apartments> findByTypeCityPriceRoomsMetro(@Param("type") String type,
                                                   @Param("city") String city,
                                                   @Param("priceMin") long priceMin,
                                                   @Param("priceMax") long priceMax,
                                                   @Param("rooms") int rooms,
                                                   @Param("metro") String metro);

    //6
    @Query("SELECT a FROM Apartments a WHERE a.type = :type and a.location.locationName = :city and a.price.value BETWEEN :priceMin and :priceMax" +
            " and a.rooms = :rooms and a.location.subLocationName = :subLocationName")
    List<Apartments> findByTypeCityPriceRoomsRegion(@Param("type") String type,
                                                    @Param("city") String city,
                                                    @Param("priceMin") long priceMin,
                                                    @Param("priceMax") long priceMax,
                                                    @Param("rooms") int rooms,
                                                    @Param("subLocationName") String subLocationName);

    //7
    @Query("SELECT a FROM Apartments a WHERE a.type = :type and a.location.locationName = :city and a.price.value BETWEEN :priceMin and :priceMax" +
            " and a.location.subLocationName = :subLocationName and a.location.metro.name = :metro")
    List<Apartments> findByTypeCityPriceRegionMetro(@Param("type") String type,
                                                    @Param("city") String city,
                                                    @Param("priceMin") long priceMin,
                                                    @Param("priceMax") long priceMax,
                                                    @Param("subLocationName") String subLocationName,
                                                    @Param("metro") String metro);

    //8
    @Query("SELECT a FROM Apartments a WHERE a.type = :type and a.location.locationName = :city and a.price.value BETWEEN :priceMin and :priceMax" +
            " and a.location.subLocationName = :subLocationName")
    List<Apartments> findByTypeCityPriceRegion(@Param("type") String type,
                                               @Param("city") String city,
                                               @Param("priceMin") long priceMin,
                                               @Param("priceMax") long priceMax,
                                               @Param("subLocationName") String subLocationName);

    //9
    @Query("SELECT a FROM Apartments a WHERE a.type = :type and a.location.locationName = :city and a.price.value BETWEEN :priceMin and :priceMax" +
            " and a.location.metro.name = :metro")
    List<Apartments> findByTypeCityPriceMetro(@Param("type") String type,
                                              @Param("city") String city,
                                              @Param("priceMin") long priceMin,
                                              @Param("priceMax") long priceMax,
                                              @Param("metro") String metro);

}
