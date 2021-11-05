package com.example.keycloack.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date creationDate = new Date();
    private String type;
    private String name;
    private String lastName;
    private String nickname;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SavedApartments> savedApartments;

    private String city;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Region> region;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MetroNames> metroNames;

    private String phone;
    private String idTelegram;
    private long daysOfSubscription;
    private long priceMin;
    private long priceMax;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Rooms> rooms;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TodayCompilation> todayCompilation;


    private String language;
    private int freeCounterSearch;
    private int userStatus;

}
