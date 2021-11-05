package com.example.keycloack.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "messages", cascade = CascadeType.ALL)
    private List<UserTelegramId> userTelegramId;

    private String messageText;
}
