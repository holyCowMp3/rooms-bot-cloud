package com.example.keycloack.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
}
