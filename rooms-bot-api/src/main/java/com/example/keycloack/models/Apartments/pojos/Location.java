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
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;
    private String region;
    private String locationName;
    private String subLocationName;
    private String nonAdminSubLocality;
    private String address;
    private String house;

    @OneToOne(cascade = CascadeType.ALL)
    private Metro metro;

    @OneToOne(mappedBy = "location")
    @JsonIgnore
    private Apartments apartments;

    public Location(String country, String region, String locationName, String subLocationName, String nonAdminSubLocality, String address, String house) {
        this.country = country;
        this.region = region;
        this.locationName = locationName;
        this.subLocationName = subLocationName;
        this.nonAdminSubLocality = nonAdminSubLocality;
        this.address = address;
        this.house = house;
    }
}
