package com.example.graduationbe.entities.commerce;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SmartPhone implements Serializable {
    @Id
    @Column(name = "smart_id")
    private Long smart_id;
    private String screen;
    private String beforeCamera;
    private String afterCamera;
    private String ram;
    private String sim;
    private String chip;
    private String operatingSystem;
    private String storage;
    private String pin;

    @OneToOne
    @JsonIgnore
    @MapsId
    @JoinColumn(name = "smart_id")
    private Product smartId;
}
