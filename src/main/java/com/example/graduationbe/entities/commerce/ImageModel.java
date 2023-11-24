package com.example.graduationbe.entities.commerce;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "image_model")
public class ImageModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;

    @Column(length = 50000000)
    private byte[] pcyByte;


    public ImageModel() {
    }

    public ImageModel(String name, String type, byte[] pcyByte) {
        this.name = name;
        this.type = type;
        this.pcyByte = pcyByte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPcyByte() {
        return pcyByte;
    }

    public void setPcyByte(byte[] pcyByte) {
        this.pcyByte = pcyByte;
    }
}