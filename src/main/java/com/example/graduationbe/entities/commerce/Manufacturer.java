package com.example.graduationbe.entities.commerce;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Manufacturer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long manuId;
    private String title;
    private Boolean completed = false;
    @OneToMany(mappedBy = "manufacturer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Product> products = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

}
