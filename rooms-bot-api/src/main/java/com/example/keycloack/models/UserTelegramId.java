package com.example.keycloack.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTelegramId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String telegramIds;

    @ManyToOne(fetch = FetchType.EAGER)
    private Messages messages;
}
