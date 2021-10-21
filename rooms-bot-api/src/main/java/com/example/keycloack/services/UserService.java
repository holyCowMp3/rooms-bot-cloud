package com.example.keycloack.services;

import com.example.keycloack.models.Apartments.Apartments;
import com.example.keycloack.models.User;
import com.example.keycloack.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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


    @Async
    public void todayCompilation() {
        List<User> users = repository.findAll();
        List<Apartments> apartments = new ArrayList<>();


        for (User user : users) {

            if (user.getPriceMin() == 0 || user.getPriceMax() == 0)
                continue;


            if (user.getRooms() == null && user.getRegion() == null && user.getMetroNames() == null) {
                apartments.addAll(apartmentsService.findByThreeParams(user.getType(), user.getCity(), user.getPriceMin(), user.getPriceMax()));
            } else if (user.getRooms() != null && user.getRegion() == null && user.getMetroNames() == null) {
                for (int room : user.getRooms())
                    apartments.addAll(apartmentsService.findByFourParams(user.getType(), user.getCity(), user.getPriceMin(),
                            user.getPriceMax(), room));
            } else if (user.getRooms() != null && user.getRegion() != null && user.getMetroNames() == null) {
                for (int room : user.getRooms())
                    for (String region : user.getRegion())
                        apartments.addAll(apartmentsService.findByParamsSubLocationName(user.getType(), user.getCity(),
                                user.getPriceMin(), user.getPriceMax(), room, region));
            } else if (user.getRooms() != null && user.getRegion() != null && user.getMetroNames() != null) {
                for (int room : user.getRooms())
                    for (String region : user.getRegion())
                        for (String metro : user.getMetroNames())
                            apartments.addAll(apartmentsService.findBySixParams(user.getType(), user.getCity(),
                                    user.getPriceMin(), user.getPriceMax(), room, region, metro));
            }


            user.setTodayCompilation(apartments.stream().map(Apartments::getInternalId).collect(Collectors.toList()));

            log.info("----------------------------------------------------------------------------------------");
            log.info("saved: " + user);
            log.info("----------------------------------------------------------------------------------------");

            repository.save(user);
        }
    }
}
