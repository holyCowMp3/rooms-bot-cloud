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
public class SalesAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;

    @OneToOne(mappedBy = "salesAgent")
    @JsonIgnore
    private Apartments apartments;

    public SalesAgent(String phone) {
        this.phone = phone;
    }
}
