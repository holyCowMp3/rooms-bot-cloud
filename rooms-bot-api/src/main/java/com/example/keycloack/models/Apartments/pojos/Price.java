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
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long value;
    private String currency;

    @OneToOne(mappedBy = "price")
    @JsonIgnore
    private Apartments apartments;

    public Price(Long value, String currency) {
        this.value = value;
        this.currency = currency;
    }
}
