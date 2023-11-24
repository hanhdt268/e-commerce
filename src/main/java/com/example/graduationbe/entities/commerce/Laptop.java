package com.example.graduationbe.entities.commerce;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Laptop implements Serializable {
    @Id
    @Column(name = "laptop_id")
    private Long lId;
    private String cpu;
    private String ram;
    private String hardDrive;

    private String screen;
    private String graphic;
    private String connector;
    private String special;
    private String operatingSystem;
    private String design;

    private String sizeMass;
    private String releaseTime;

    @OneToOne
    @JsonIgnore
    @MapsId
    @JoinColumn(name = "laptop_id")
    private Product lap_id;
}
