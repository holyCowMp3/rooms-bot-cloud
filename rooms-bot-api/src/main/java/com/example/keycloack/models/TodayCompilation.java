package com.example.keycloack.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodayCompilation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long todayCompilation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public TodayCompilation(Long todayCompilation) {
        this.todayCompilation = todayCompilation;
    }
}
