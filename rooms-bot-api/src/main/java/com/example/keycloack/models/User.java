package com.example.keycloack.models;

import com.example.keycloack.models.Apartments.Apartments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;

    private String type;

    private String name;
    private String lastName;

    private String nickname;
    private String password;
    private String email;

    private String role;
    private String typeSubscription;

    private List<Apartments> savedApartments;

    private boolean isConfirmed;
    private boolean isLocked;

    private String city;
    private List<String> region;
    private List<String> metroNames;

    private String phone;
    private String idTelegram;

    private long daysOfSubscription;
    private boolean isRent;

    private int priceMin;
    private int priceMax;

    private List<Integer> rooms;

    private List<Long> todayCompilation;
    private List<String> viewed;
    private String lastViewed;


    private String supportReceiverTelegramId;

}
