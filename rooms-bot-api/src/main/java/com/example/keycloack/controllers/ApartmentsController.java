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


    @ResponseBody
    @GetMapping("/all")
    public ResponseEntity<List<Apartments>> all() {
        return ResponseEntity.ok(apartmentsService.findAll());
    }

}
