package com.example.keycloack.services;

import com.example.keycloack.models.Apartments.Apartments;
import com.example.keycloack.models.User;
import com.example.keycloack.repository.ApartmentsRepository;
import com.example.keycloack.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ApartmentsRepository apartmentsService;

    @Autowired
    public UserService(UserRepository repository, ApartmentsRepository apartmentsService) {
        this.repository = repository;
        this.apartmentsService = apartmentsService;
    }

    public void deleteById(String id) {
        repository.deleteById(id);
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


        for (User user : users) {
            Set<Apartments> apartments = new HashSet<>();

            if (user.getType() == null || user.getCity() == null
                    || user.getType().equals("") || user.getCity().equals("") || (user.getPriceMin() == 0 && user.getPriceMax() == 0))
                continue;

            String[] type = user.getType().split(":");

//            if (user.getPriceMax() != 0 && user.getPriceMin() != 0 && user.getRooms().size() != 0 && user.getRegion().size() != 0 && user.getMetroNames().size() != 0) {
//                for (int room : user.getRooms())
//                    for (String region : user.getRegion())
//                        for (String metro : user.getMetroNames())
//                            apartments.addAll(apartmentsService.findByTypeCityCategoryPriceRoomsRegionMetro(user.getType(), user.getCity(), user.getCategory(), user.getPriceMin(), user.getPriceMax(), room, region, metro));
//            } else if (user.getPriceMin() != 0 && user.getPriceMax() != 0 && user.getRooms().size() == 0 && user.getRegion().size() == 0 && user.getMetroNames().size() == 0) {
//                apartments.addAll(apartmentsService.findByTypeCityCategoryPrice(user.getType(), user.getCity(), user.getCategory(), user.getPriceMin(), user.getPriceMax()));
//            } else if (user.getPriceMin() != 0 && user.getPriceMax() != 0 && user.getRooms().size() != 0 && user.getRegion().size() == 0 && user.getMetroNames().size() == 0) {
//                for (int room : user.getRooms())
//                    apartments.addAll(apartmentsService.findByTypeCityCategoryPriceRooms(user.getType(), user.getCity(), user.getCategory(), user.getPriceMin(), user.getPriceMax(), room));
//            } else if (user.getPriceMin() != 0 && user.getPriceMax() != 0 && user.getRooms().size() != 0 && user.getRegion().size() != 0 && user.getMetroNames().size() == 0) {
//                for (int room : user.getRooms())
//                    for (String region : user.getRegion())
//                        apartments.addAll(apartmentsService.findByTypeCityCategoryPriceRoomsRegion(user.getType(), user.getCity(), user.getCategory(), user.getPriceMin(), user.getPriceMax(), room, region));
//            } else if (user.getPriceMin() != 0 && user.getPriceMax() != 0 && user.getRooms().size() == 0 && user.getRegion().size() != 0 && user.getMetroNames().size() == 0) {
//                for (String region : user.getRegion())
//                    apartments.addAll(apartmentsService.findByTypeCityCategoryPriceRegion(user.getType(), user.getCity(), user.getCategory(), user.getPriceMin(), user.getPriceMax(), region));
//            } else if (user.getPriceMin() != 0 && user.getPriceMax() != 0 && user.getRooms().size() == 0 && user.getRegion().size() != 0 && user.getMetroNames().size() != 0) {
//                for (String region : user.getRegion())
//                    for (String metro : user.getMetroNames())
//                        apartments.addAll(apartmentsService.findByTypeCityCategoryPriceRegionMetro(user.getCity(), user.getCity(), user.getCategory(), user.getPriceMin(), user.getPriceMax(), region, metro));
//            } else if (user.getPriceMin() != 0 && user.getPriceMax() != 0 && user.getRooms().size() == 0 && user.getRegion().size() == 0 && user.getMetroNames().size() != 0) {
//                for (String metro : user.getMetroNames())
//                    apartments.addAll(apartmentsService.findByTypeCityCategoryPriceMetro(user.getType(), user.getCity(), user.getCategory(), user.getPriceMin(), user.getPriceMax(), metro));
//            } else if (user.getPriceMin() != 0 && user.getPriceMax() != 0 && user.getRooms().size() != 0 && user.getRegion().size() == 0 && user.getMetroNames().size() != 0) {
//                for (int room : user.getRooms())
//                    for (String metro : user.getMetroNames())
//                        apartments.addAll(apartmentsService.findByTypeCityCategoryPriceRoomsMetro(user.getType(), user.getCity(), user.getCategory(), user.getPriceMin(), user.getPriceMax(), room, metro));
//            }

            if (type.length == 2) {
                if (user.getRooms() != null)
                    continue;

                if (user.getRegion() != null && user.getMetroNames() != null) {
                    for (String region : user.getRegion())
                        for (String metro : user.getMetroNames())
                            apartments.addAll(apartmentsService.findByTypeCityCategoryPriceRegionMetro(type[0], user.getCity(), type[1], user.getPriceMin(), user.getPriceMax(), region, metro));
                } else if (user.getRegion() == null && user.getMetroNames() == null) {
                    apartments.addAll(apartmentsService.findByTypeCityCategoryPrice(type[0], user.getCity(), type[1], user.getPriceMin(), user.getPriceMax()));
                } else if (user.getRegion() != null && user.getMetroNames() == null) {
                    for (String region : user.getRegion())
                        apartments.addAll(apartmentsService.findByTypeCityCategoryPriceRegion(type[0], user.getCity(), type[1], user.getPriceMin(), user.getPriceMax(), region));
                } else if (user.getRegion() == null && user.getMetroNames() != null) {
                    for (String metro : user.getMetroNames())
                        apartments.addAll(apartmentsService.findByTypeCityCategoryPriceMetro(type[0], user.getCity(), type[1], user.getPriceMin(), user.getPriceMax(), metro));
                }

            } else if (type.length == 1) {
                if (user.getRooms() != null && user.getRegion() != null && user.getMetroNames() != null) {
                    for (int room : user.getRooms())
                        for (String region : user.getRegion())
                            for (String metro : user.getMetroNames())
                                apartments.addAll(apartmentsService.findByTypeCityPriceRoomsRegionMetro(type[0], user.getCity(), user.getPriceMin(), user.getPriceMax(), room, region, metro));
                } else if (user.getRooms() == null && user.getRegion() == null && user.getMetroNames() == null) {
                    apartments.addAll(apartmentsService.findByTypeCityPrice(type[0], user.getCity(), user.getPriceMin(), user.getPriceMax()));
                } else if (user.getRooms() != null && user.getRegion() == null && user.getMetroNames() == null) {
                    for (int room : user.getRooms())
                        apartments.addAll(apartmentsService.findByTypeCityPriceRooms(type[0], user.getCity(), user.getPriceMin(), user.getPriceMax(), room));
                } else if (user.getRooms() != null && user.getRegion() != null && user.getMetroNames() == null) {
                    for (int room : user.getRooms())
                        for (String region : user.getRegion())
                            apartments.addAll(apartmentsService.findByTypeCityPriceRoomsRegion(type[0], user.getCity(), user.getPriceMin(), user.getPriceMax(), room, region));
                } else if (user.getRooms() == null && user.getRegion() != null && user.getMetroNames() == null) {
                    for (String region : user.getRegion())
                        apartments.addAll(apartmentsService.findByTypeCityPriceRegion(type[0], user.getCity(), user.getPriceMin(), user.getPriceMax(), region));
                } else if (user.getRooms() == null && user.getRegion() != null && user.getMetroNames() != null) {
                    for (String region : user.getRegion())
                        for (String metro : user.getMetroNames())
                            apartments.addAll(apartmentsService.findByTypeCityPriceRegionMetro(type[0], user.getCity(), user.getPriceMin(), user.getPriceMax(), region, metro));
                } else if (user.getRooms() == null && user.getRegion() == null && user.getMetroNames() != null) {
                    for (String metro : user.getMetroNames())
                        apartments.addAll(apartmentsService.findByTypeCityPriceMetro(type[0], user.getCity(), user.getPriceMin(), user.getPriceMax(), metro));
                } else if (user.getRooms() != null && user.getRegion() == null && user.getMetroNames() != null) {
                    for (int room : user.getRooms())
                        for (String metro : user.getMetroNames())
                            apartments.addAll(apartmentsService.findByTypeCityPriceRoomsMetro(type[0], user.getCity(), user.getPriceMin(), user.getPriceMax(), room, metro));
                }
            } else {
                System.out.println("empty array");
            }

            user.getTodayCompilation().clear();
            user.setTodayCompilation(apartments.stream().map(Apartments::getInternalId).collect(Collectors.toList()));

            System.out.println("----------------------------------------------------------------------------------------");
            System.out.println("saved: " + user);
            System.out.println("----------------------------------------------------------------------------------------");

            repository.save(user);
        }
    }

    public void todayCompilationUser(User user) {
        if ((!user.getType().equals("") || user.getType() != null) && (!user.getCity().equals("") || user.getCity() != null)
                && user.getPriceMin() >= 0 && user.getPriceMax() != 0) {
            String[] type = user.getType().split(":");
            Set<Apartments> apartments = new HashSet<>();

            if (type.length == 2) {
                if (type[1].equals("комната")) {
                    if (user.getRooms() != null)
                        user.setRooms(null);

                    if (user.getRegion() != null && user.getMetroNames() != null)
                        for (String region : user.getRegion())
                            for (String metro : user.getMetroNames())
                                apartments.addAll(apartmentsService.findByTypeCityCategoryPriceRegionMetro(type[0], user.getCity(), type[1], user.getPriceMin(), user.getPriceMax(), region, metro));
                    else if (user.getRegion() == null && user.getMetroNames() != null)
                        for (String metro : user.getMetroNames())
                            apartments.addAll(apartmentsService.findByTypeCityCategoryPriceMetro(type[0], user.getCity(), type[1], user.getPriceMin(), user.getPriceMax(), metro));
                    else if (user.getRegion() != null && user.getMetroNames() == null)
                        for (String region : user.getRegion())
                            apartments.addAll(apartmentsService.findByTypeCityCategoryPriceRegion(type[0], user.getCity(), type[1], user.getPriceMin(), user.getPriceMax(), region));
                    else
                        apartments.addAll(apartmentsService.findByTypeCityCategoryPrice(type[0], user.getCity(), type[1], user.getPriceMin(), user.getPriceMax()));
                } else if (type[1].equals("квартира")) {
                    if (user.getRooms() != null) {
                        if (user.getRegion() != null && user.getMetroNames() != null)
                            for (String region : user.getRegion())
                                for (String metro : user.getMetroNames())
                                    for (int room : user.getRooms())
                                        apartments.addAll(apartmentsService.findByTypeCityCategoryPriceRoomsRegionMetro(type[0], user.getCity(), type[1], user.getPriceMin(), user.getPriceMax(), room, region, metro));
                        else if (user.getRegion() == null && user.getMetroNames() != null)
                            for (String metro : user.getMetroNames())
                                for (int room : user.getRooms())
                                    apartments.addAll(apartmentsService.findByTypeCityCategoryPriceRoomsMetro(type[0], user.getCity(), type[1], user.getPriceMin(), user.getPriceMax(), room, metro));
                        else if (user.getRegion() != null && user.getMetroNames() == null)
                            for (String region : user.getRegion())
                                for (int room : user.getRooms())
                                    apartments.addAll(apartmentsService.findByTypeCityCategoryPriceRoomsRegion(type[0], user.getCity(), type[1], user.getPriceMin(), user.getPriceMax(), room, region));
                        else
                            for (int room : user.getRooms())
                                apartments.addAll(apartmentsService.findByTypeCityCategoryPriceRooms(type[0], user.getCity(), type[1], user.getPriceMin(), user.getPriceMax(), room));
                    }
                }

            } else if (type.length == 1) {

                if (user.getRooms() != null) {
                    if (user.getRegion() != null && user.getMetroNames() != null)
                        for (String region : user.getRegion())
                            for (String metro : user.getMetroNames())
                                for (int room : user.getRooms())
                                    apartments.addAll(apartmentsService.findByTypeCityPriceRoomsRegionMetro(type[0], user.getCity(), user.getPriceMin(), user.getPriceMax(), room, region, metro));
                    else if (user.getRegion() == null && user.getMetroNames() != null)
                        for (String metro : user.getMetroNames())
                            for (int room : user.getRooms())
                                apartments.addAll(apartmentsService.findByTypeCityPriceRoomsMetro(type[0], user.getCity(), user.getPriceMin(), user.getPriceMax(), room, metro));
                    else if (user.getRegion() != null && user.getMetroNames() == null)
                        for (String region : user.getRegion())
                            for (int room : user.getRooms())
                                apartments.addAll(apartmentsService.findByTypeCityPriceRoomsRegion(type[0], user.getCity(), user.getPriceMin(), user.getPriceMax(), room, region));
                    else
                        for (int room : user.getRooms())
                            apartments.addAll(apartmentsService.findByTypeCityPriceRooms(type[0], user.getCity(), user.getPriceMin(), user.getPriceMax(), room));
                }

            }

            user.setTodayCompilation(apartments.stream().map(Apartments::getInternalId).collect(Collectors.toList()));

            repository.save(user);
        }
    }

}
