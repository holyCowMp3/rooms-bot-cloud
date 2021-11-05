package com.example.keycloack.models.Apartments.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Metro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String distance;

    @OneToOne(mappedBy = "metro")
    @JsonIgnore
    private Location location;

    public Metro(String name, String distance) {
        this.name = name;
        this.distance = distance;
    }
}
