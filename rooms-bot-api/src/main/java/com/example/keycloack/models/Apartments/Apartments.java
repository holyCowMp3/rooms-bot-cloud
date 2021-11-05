package com.example.keycloack.models.Apartments;

import com.example.keycloack.models.Apartments.pojos.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Apartments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Long internalId;

    private String creationDate;
    private String lastUpdateDate;

    private String type;
    private String propertyType;
    private String category;
    private Integer rooms;

    @OneToOne(cascade = CascadeType.ALL)
    private Area area;

    private Integer floor;
    private Integer floorsTotal;

    @OneToOne(cascade = CascadeType.ALL)
    private Price price;

    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    private String url;

    @OneToOne(cascade = CascadeType.ALL)
    private SalesAgent salesAgent;

    @Column(length = 200000)
    private String description;

    @OneToMany(mappedBy = "apartments", cascade = CascadeType.ALL)
    private List<Images> images;

    private String urlTelegraph;
}
