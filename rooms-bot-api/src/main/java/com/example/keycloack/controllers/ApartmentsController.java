package com.example.keycloack.controllers;

import com.example.keycloack.models.Apartments.Apartments;
import com.example.keycloack.services.ApartmentsService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/apartments")
@AllArgsConstructor
@Slf4j
@CrossOrigin
public class ApartmentsController {

    private final ApartmentsService apartmentsService;


//    @ResponseBody
//    @GetMapping("/randomByParams")
//    public ResponseEntity<Apartments> getRandom(@RequestParam(value = "type", defaultValue = "аренда") String type,
//                                                @RequestParam(value = "city", defaultValue = "Киев") String city,
//                                                @RequestParam(value = "category", defaultValue = "квартира") String category,
//                                                @RequestParam(value = "priceMin", required = false, defaultValue = "0") int priceMin,
//                                                @RequestParam(value = "priceMax", required = false, defaultValue = "0") int priceMax,
//                                                @RequestParam(value = "rooms", required = false, defaultValue = "") int[] rooms,
//                                                @RequestParam(value = "subLocationName", required = false, defaultValue = "") String[] subLocationName,
//                                                @RequestParam(value = "metro", required = false, defaultValue = "") String[] metro) {
//
//        Random random = new Random();
//        List<Apartments> apartmentsList = new ArrayList<>();
//
//        try {
//            if (priceMin == 0 && priceMax == 0 && rooms.length == 0 && subLocationName.length == 0 && metro.length == 0) {
//                apartmentsList = apartmentsService.findByTypeCityCategory(type, city, category);
//                return ResponseEntity.ok(apartmentsList.get(random.nextInt(apartmentsList.size())));
//
//            } else if (priceMin != 0 && priceMax != 0 && rooms.length == 0 && subLocationName.length == 0 && metro.length == 0) {
//                apartmentsList = apartmentsService.findByTypeCityCategoryPrice(type, city, category, priceMin, priceMax);
//                return ResponseEntity.ok(apartmentsList.get(random.nextInt(apartmentsList.size())));
//
//            } else if (priceMin != 0 && priceMax != 0 && rooms.length != 0 && subLocationName.length == 0 && metro.length == 0) {
//
//                for (int room : rooms) {
//                    apartmentsList.addAll(apartmentsService.findByTypeCityCategoryPriceRooms(type, city, category, priceMin, priceMax, room));
//                }
//
//                return ResponseEntity.ok(apartmentsList.get(random.nextInt(apartmentsList.size())));
//
//            } else if (priceMin != 0 && priceMax != 0 && rooms.length != 0 && subLocationName.length != 0 && metro.length == 0) {
//                for (int room : rooms) {
//                    for (String region : subLocationName) {
//                        apartmentsList.addAll(apartmentsService.findByTypeCityCategoryPriceRoomsRegion(type, city,category, priceMin, priceMax, room, region));
//                    }
//                }
//
//                return ResponseEntity.ok(apartmentsList.get(random.nextInt(apartmentsList.size())));
//            } else if (priceMin != 0 && priceMax != 0 && rooms.length != 0 && subLocationName.length == 0) {
//                for (int room : rooms) {
//                    for (String metroName : metro) {
//                        apartmentsList.addAll(apartmentsService.findByTypeCityCategoryPriceRoomsMetro(type, city,category, priceMin, priceMax, room, metroName));
//                    }
//                }
//
//                return ResponseEntity.ok(apartmentsList.get(random.nextInt(apartmentsList.size())));
//            } else if (priceMin != 0 && priceMax != 0 && rooms.length != 0) {
//
//                for (String region : subLocationName) {
//                    for (String metroName : metro) {
//                        for (int room : rooms) {
//                            apartmentsList.addAll(apartmentsService.findByTypeCityCategoryPriceRoomsRegionMetro(type, city,category, priceMin, priceMax, room, region, metroName));
//                        }
//                    }
//                }
//
//                return ResponseEntity.ok(apartmentsList.get(random.nextInt(apartmentsList.size())));
//
//            } else
//                return new ResponseEntity<>(new Apartments(), HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            log.error("Apartments in database not found");
//            return new ResponseEntity<>(new Apartments(), HttpStatus.NOT_FOUND);
//        }
//
//    }
//
//    @ResponseBody
//    @GetMapping("/allByParams")
//    public ResponseEntity<List<Apartments>> getAll(@RequestParam(value = "type", defaultValue = "аренда") String type,
//                                                   @RequestParam(value = "city", defaultValue = "Киев") String city,
//                                                   @RequestParam(value = "category", defaultValue = "квартира") String category,
//                                                   @RequestParam(value = "priceMin", required = false, defaultValue = "0") int priceMin,
//                                                   @RequestParam(value = "priceMax", required = false, defaultValue = "0") int priceMax,
//                                                   @RequestParam(value = "rooms", required = false, defaultValue = "") int[] rooms,
//                                                   @RequestParam(value = "subLocationName", required = false, defaultValue = "") String[] subLocationName,
//                                                   @RequestParam(value = "metro", required = false, defaultValue = "") String[] metro) {
//
//        List<Apartments> apartments = new ArrayList<>();
//        if (priceMin == 0 && priceMax == 0 && rooms.length == 0 && subLocationName.length == 0 && metro.length == 0) {
//            return ResponseEntity.ok(apartmentsService.findByTypeCityCategory(type, city, category));
//
//        } else if (priceMin != 0 && priceMax != 0 && rooms.length == 0 && subLocationName.length == 0 && metro.length == 0) {
//            return ResponseEntity.ok(apartmentsService.findByTypeCityCategoryPrice(type, city, category, priceMin, priceMax));
//
//        } else if (priceMin != 0 && priceMax != 0 && rooms.length != 0 && subLocationName.length == 0 && metro.length == 0) {
//
//            for (int room : rooms) {
//                apartments.addAll(apartmentsService.findByTypeCityCategoryPriceRooms(type, city, category, priceMin, priceMax, room));
//            }
//            return ResponseEntity.ok(apartments);
//
//        } else if (priceMin != 0 && priceMax != 0 && rooms.length != 0 && subLocationName.length != 0 && metro.length == 0) {
//
//            for (int room : rooms) {
//                for (String region : subLocationName) {
//                    apartments.addAll(apartmentsService.findByTypeCityCategoryPriceRoomsRegion(type, city,category, priceMin, priceMax, room, region));
//                }
//            }
//
//            return ResponseEntity.ok(apartments);
//        } else if (priceMin != 0 && priceMax != 0 && rooms.length != 0 && subLocationName.length == 0) {
//
//            for (int room : rooms) {
//                for (String metroName : metro) {
//                    apartments.addAll(apartmentsService.findByTypeCityCategoryPriceRoomsMetro(type, city,category, priceMin, priceMax, room, metroName));
//                }
//            }
//
//            return ResponseEntity.ok(apartments);
//        } else if (priceMin != 0 && priceMax != 0 && rooms.length != 0) {
//
//            for (String region : subLocationName) {
//                for (String metroName : metro) {
//                    for (int room : rooms) {
//                        apartments.addAll(apartmentsService.findByTypeCityCategoryPriceRoomsRegionMetro(type, city,category, priceMin, priceMax, room, region, metroName));
//                    }
//                }
//            }
//            return ResponseEntity.ok(apartments);
//
//        } else
//            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
//
//    }

    @ResponseBody
    @GetMapping("/find")
    public ResponseEntity<List<Apartments>> findByIdRoom(@RequestParam(value = "id") long[] id) {
        if (id == null)
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);

        List<Apartments> apartments = new ArrayList<>();
        for (long item : id) {
            apartments.add(apartmentsService.findByInternalId(item));
        }

        return ResponseEntity.ok(apartments);
    }

    @SneakyThrows
    @ResponseBody
    @GetMapping("/all")
    public ResponseEntity<List<Apartments>> all() {
        return ResponseEntity.ok(apartmentsService.findAll().get());
    }

}
