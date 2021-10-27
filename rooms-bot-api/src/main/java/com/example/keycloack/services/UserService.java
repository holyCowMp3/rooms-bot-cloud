package com.example.keycloack.services;

import com.example.keycloack.models.Apartments.Apartments;
import com.example.keycloack.models.User;
import com.example.keycloack.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    private final UserRepository repository;
    private final ApartmentsService apartmentsService;

    @Autowired
    public UserService(UserRepository repository, ApartmentsService apartmentsService) {
        this.repository = repository;
        this.apartmentsService = apartmentsService;
    }


    public List<User> findAll() {
        return repository.findAll();
    }

    public User save(User user) {
        return repository.save(user);
    }

    public User findById(String id) {
        return repository.findById(id).get();
    }

    public User findByIdTelegram(String id) {
        return repository.findByIdTelegram(id);
    }

    public void todayCompilation() {
        List<User> users = repository.findAll();
        Set<Apartments> apartments = new HashSet<>();

        for (User user : users) {

            if (user.getType() == null || user.getCity() == null
                    || user.getType().equals("") || user.getCity().equals(""))
                continue;


            if (user.getPriceMax() != 0 && user.getPriceMin() != 0 && user.getRooms().size() != 0 && user.getRegion().size() != 0 && user.getMetroNames().size() != 0) {
                for (int room : user.getRooms())
                    for (String region : user.getRegion())
                        for (String metro : user.getMetroNames())
                            apartments.addAll(apartmentsService.findBySixParams(user.getType(), user.getCity(), user.getPriceMin(), user.getPriceMax(), room, region, metro));
            } else if (user.getPriceMin() != 0 && user.getPriceMax() != 0 && user.getRooms().size() == 0 && user.getRegion().size() == 0 && user.getMetroNames().size() == 0) {
                apartments.addAll(apartmentsService.findByThreeParams(user.getType(), user.getCity(), user.getPriceMin(), user.getPriceMax()));
            } else if (user.getPriceMin() != 0 && user.getPriceMax() != 0 && user.getRooms().size() != 0 && user.getRegion().size() == 0 && user.getMetroNames().size() == 0) {
                for (int room : user.getRooms())
                    apartments.addAll(apartmentsService.findByFourParams(user.getType(), user.getCity(), user.getPriceMin(), user.getPriceMax(), room));
            } else if (user.getPriceMin() != 0 && user.getPriceMax() != 0 && user.getRooms().size() != 0 && user.getRegion().size() != 0 && user.getMetroNames().size() == 0) {
                for (int room : user.getRooms())
                    for (String region : user.getRegion())
                        apartments.addAll(apartmentsService.findByParamsSubLocationName(user.getType(), user.getCity(), user.getPriceMin(), user.getPriceMax(), room, region));
            } else if (user.getPriceMin() != 0 && user.getPriceMax() != 0 && user.getRooms().size() == 0 && user.getRegion().size() != 0 && user.getMetroNames().size() == 0) {
                for (String region : user.getRegion())
                    apartments.addAll(apartmentsService.findByNotRoomsAndMetro(user.getType(), user.getCity(), user.getPriceMin(), user.getPriceMax(), region));
            } else if (user.getPriceMin() != 0 && user.getPriceMax() != 0 && user.getRooms().size() == 0 && user.getRegion().size() != 0 && user.getMetroNames().size() != 0) {
                for (String region : user.getRegion())
                    for (String metro : user.getMetroNames())
                        apartments.addAll(apartmentsService.findByNotRooms(user.getCity(), user.getCity(), user.getPriceMin(), user.getPriceMax(), region, metro));
            } else if (user.getPriceMin() != 0 && user.getPriceMax() != 0 && user.getRooms().size() == 0 && user.getRegion().size() == 0 && user.getMetroNames().size() != 0) {
                for (String metro : user.getMetroNames())
                    apartments.addAll(apartmentsService.findByMetro(user.getType(), user.getCity(), user.getPriceMin(), user.getPriceMax(), metro));
            } else if (user.getPriceMin() != 0 && user.getPriceMax() != 0 && user.getRooms().size() != 0 && user.getRegion().size() == 0 && user.getMetroNames().size() != 0) {
                for (int room : user.getRooms())
                    for (String metro : user.getMetroNames())
                        apartments.addAll(apartmentsService.findByParamsMetro(user.getType(), user.getCity(), user.getPriceMin(), user.getPriceMax(), room, metro));
            }

            user.setTodayCompilation(apartments.stream().map(Apartments::getInternalId).collect(Collectors.toList()));

            System.out.println("----------------------------------------------------------------------------------------");
            System.out.println("saved: " + user);
            System.out.println("----------------------------------------------------------------------------------------");

            repository.save(user);
        }
    }
}
