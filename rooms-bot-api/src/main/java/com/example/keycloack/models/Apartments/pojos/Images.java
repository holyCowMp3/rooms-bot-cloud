package com.example.keycloack.models.Apartments.pojos;

import com.example.keycloack.models.Apartments.Apartments;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Apartments apartments;
}
