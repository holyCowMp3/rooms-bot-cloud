package com.example.keycloack.controllers;

import com.example.keycloack.models.Apartments.Apartments;
import com.example.keycloack.services.ApartmentsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/apartments")
@AllArgsConstructor
@CrossOrigin
public class ApartmentsController {

    private final ApartmentsService apartmentsService;

    @GetMapping("/get")
    public String get() {
        return "HELLo";
    }

    @ResponseBody
    @GetMapping("/randomByParams")
    public ResponseEntity<Apartments> getRandom(@RequestParam(value = "type", defaultValue = "аренда") String type,
                                                @RequestParam(value = "city", defaultValue = "Киев") String city,
                                                @RequestParam(value = "priceMin", required = false, defaultValue = "0") int priceMin,
                                                @RequestParam(value = "priceMax", required = false, defaultValue = "0") int priceMax,
                                                @RequestParam(value = "rooms", required = false, defaultValue = "") int[] rooms,
                                                @RequestParam(value = "subLocationName", required = false, defaultValue = "") String[] subLocationName,
                                                @RequestParam(value = "metro", required = false, defaultValue = "") String[] metro)  {

        Random random = new Random();
        List<Apartments> apartmentsList = new ArrayList<>();

        if (priceMin == 0 && priceMax == 0 && rooms.length == 0 && subLocationName.length == 0 && metro.length == 0) {
            apartmentsList = apartmentsService.findByTwoParams(type, city);
            return ResponseEntity.ok(apartmentsList.get(random.nextInt(apartmentsList.size())));

        } else if (priceMin != 0 && priceMax != 0 && rooms.length == 0 && subLocationName.length == 0 && metro.length == 0) {
            apartmentsList = apartmentsService.findByThreeParams(type, city, priceMin, priceMax);
            return ResponseEntity.ok(apartmentsList.get(random.nextInt(apartmentsList.size())));

        } else if (priceMin != 0 && priceMax != 0 && rooms.length != 0 && subLocationName.length == 0 && metro.length == 0) {

            for (int room : rooms) {
                apartmentsList.addAll(apartmentsService.findByFourParams(type, city, priceMin, priceMax, room));
            }

            return ResponseEntity.ok(apartmentsList.get(random.nextInt(apartmentsList.size())));

        } else if (priceMin != 0 && priceMax != 0 && rooms.length != 0 && subLocationName.length != 0 && metro.length == 0) {
            for (int room : rooms) {
                for (String region : subLocationName) {
                    apartmentsList.addAll(apartmentsService.findByParamsSubLocationName(type, city, priceMin, priceMax, room, region));
                }
            }

            return ResponseEntity.ok(apartmentsList.get(random.nextInt(apartmentsList.size())));
        } else if (priceMin != 0 && priceMax != 0 && rooms.length != 0 && subLocationName.length == 0) {
            for (int room : rooms) {
                for (String metroName : metro) {
                    apartmentsList.addAll(apartmentsService.findByParamsMetro(type, city, priceMin, priceMax, room, metroName));
                }
            }

            return ResponseEntity.ok(apartmentsList.get(random.nextInt(apartmentsList.size())));
        } else if (priceMin != 0 && priceMax != 0 && rooms.length != 0) {

            for (String region : subLocationName) {
                for (String metroName : metro) {
                    for (int room : rooms) {
                        apartmentsList.addAll(apartmentsService.findBySixParams(type, city, priceMin, priceMax, room, region, metroName));
                    }
                }
            }

            return ResponseEntity.ok(apartmentsList.get(random.nextInt(apartmentsList.size())));

        } else
            return new ResponseEntity<>(new Apartments(), HttpStatus.BAD_REQUEST);

    }

    @ResponseBody
    @GetMapping("/allByParams")
    public ResponseEntity<List<Apartments>> getAll(@RequestParam(value = "type", defaultValue = "аренда") String type,
                                                   @RequestParam(value = "city", defaultValue = "Киев") String city,
                                                   @RequestParam(value = "priceMin", required = false, defaultValue = "0") int priceMin,
                                                   @RequestParam(value = "priceMax", required = false, defaultValue = "0") int priceMax,
                                                   @RequestParam(value = "rooms", required = false, defaultValue = "") int[] rooms,
                                                   @RequestParam(value = "subLocationName", required = false, defaultValue = "") String[] subLocationName,
                                                   @RequestParam(value = "metro", required = false, defaultValue = "") String[] metro)  {

        List<Apartments> apartments = new ArrayList<>();
        if (priceMin == 0 && priceMax == 0 && rooms.length == 0 && subLocationName.length == 0 && metro.length == 0) {
            return ResponseEntity.ok(apartmentsService.findByTwoParams(type, city));

        } else if (priceMin != 0 && priceMax != 0 && rooms.length == 0 && subLocationName.length == 0 && metro.length == 0) {
            return ResponseEntity.ok(apartmentsService.findByThreeParams(type, city, priceMin, priceMax));

        } else if (priceMin != 0 && priceMax != 0 && rooms.length != 0 && subLocationName.length == 0 && metro.length == 0) {

            for (int room : rooms) {
                apartments.addAll(apartmentsService.findByFourParams(type, city, priceMin, priceMax, room));
            }
            return ResponseEntity.ok(apartments);

        } else if (priceMin != 0 && priceMax != 0 && rooms.length != 0 && subLocationName.length != 0 && metro.length == 0) {

            for (int room : rooms) {
                for (String region : subLocationName) {
                    apartments.addAll(apartmentsService.findByParamsSubLocationName(type, city, priceMin, priceMax, room, region));
                }
            }

            return ResponseEntity.ok(apartments);
        } else if (priceMin != 0 && priceMax != 0 && rooms.length != 0 && subLocationName.length == 0) {

            for (int room : rooms) {
                for (String metroName : metro) {
                    apartments.addAll(apartmentsService.findByParamsMetro(type, city, priceMin, priceMax, room, metroName));
                }
            }

            return ResponseEntity.ok(apartments);
        } else if (priceMin != 0 && priceMax != 0 && rooms.length != 0) {

            for (String region : subLocationName) {
                for (String metroName : metro) {
                    for (int room : rooms) {
                        apartments.addAll(apartmentsService.findBySixParams(type, city, priceMin, priceMax, room, region, metroName));
                    }
                }
            }
            return ResponseEntity.ok(apartments);

        } else
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    @ResponseBody
    @GetMapping("/find")
    public ResponseEntity<List<Apartments>> findByIdRoom(@RequestParam(value = "id") long[] id) {
        if (id == null)
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);

        List<Apartments> apartments = new ArrayList<>();
        for (long item: id) {
            apartments.add(apartmentsService.findByInternalId(item));
        }

        return ResponseEntity.ok(apartments);
    }
}
