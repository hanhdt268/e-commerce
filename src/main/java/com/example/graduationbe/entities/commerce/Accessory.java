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
public class Accessory implements Serializable {
    @Id
    @Column(name = "access_id")
    private Long access_id;

    private String compatible;
    private String resolution;

    private String howToConnect;

    private String wireLength;
    private String typePin;
    private String weight;
    private String brand;
    private String madeIn;
    private String manufacturer;

    @OneToOne
    @JsonIgnore
    @MapsId
    @JoinColumn(name = "access_id")
    private Product accessId;

}
