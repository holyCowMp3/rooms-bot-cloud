package com.example.keycloack.models.Apartments.pojos;

import com.example.keycloack.models.Apartments.Apartments;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;
    private String unit;

    @OneToOne(mappedBy = "area")
    @JsonIgnore
    private Apartments apartments;

    public Area(String value, String unit) {
        this.value = value;
        this.unit = unit;
    }
}
