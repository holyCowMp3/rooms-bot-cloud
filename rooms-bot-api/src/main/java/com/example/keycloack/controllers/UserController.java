package com.example.keycloack.controllers;

import com.example.keycloack.models.User;
import com.example.keycloack.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> user = userService.findAll();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{idTelegram}")
    @ResponseBody
    public ResponseEntity<User> getOneUser(@PathVariable String idTelegram) {
        User user = userService.findByIdTelegram(idTelegram);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        User userSave = userService.save(user);
        userService.todayCompilation();

        return new ResponseEntity<>(userSave, HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody User user) {

        if (user.getId() == null) {
            return new ResponseEntity<>("missed param: id", HttpStatus.NOT_FOUND);
        }

        userService.save(user);
        userService.todayCompilation();
        return new ResponseEntity<>("User updated", HttpStatus.OK);
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<User> updateById(@PathVariable String id, @RequestBody User user) {
        User userFromDb = userService.findById(id);

        if (userFromDb == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userFromDb.setName(user.getName());
        userFromDb.setLastName(user.getLastName());
        userFromDb.setNickname(user.getNickname());
        userFromDb.setPassword(user.getPassword());
        userFromDb.setEmail(user.getEmail());
        userFromDb.setRole(user.getRole());
        userFromDb.setTypeSubscription(user.getTypeSubscription());
        userFromDb.setSavedApartments(user.getSavedApartments());
        userFromDb.setConfirmed(user.isConfirmed());
        userFromDb.setLocked(user.isLocked());
        userFromDb.setPhone(user.getPhone());
        userFromDb.setIdTelegram(user.getIdTelegram());
        userFromDb.setDaysOfSubscription(user.getDaysOfSubscription());
        userFromDb.setRent(user.isRent());
        userFromDb.setRooms(user.getRooms());
        userFromDb.setSupportReceiverTelegramId(user.getSupportReceiverTelegramId());
        userFromDb.setPriceMin(user.getPriceMin());
        userFromDb.setPriceMax(user.getPriceMax());
        userFromDb.setCity(user.getCity());
        userFromDb.setRegion(user.getRegion());
        userFromDb.setMetroNames(user.getMetroNames());
        userFromDb.setTodayCompilation(user.getTodayCompilation());
        userFromDb.setLastViewed(user.getLastViewed());
        userFromDb.setViewed(user.getViewed());
        userFromDb.setType(user.getType());

        userService.save(userFromDb);
        userService.todayCompilation();
        return ResponseEntity.ok(userFromDb);
    }
}
