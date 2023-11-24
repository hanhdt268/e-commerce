package com.example.graduationbe.entities.commerce;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pId;

    private String title;

    @Column(length = 10000000)
    private String description;

    private String config;
    private Double price;
    private Double discountPrice;
    private String images;
    private Boolean active = false;
    private Integer quantum;
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    private Manufacturer manufacturer;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "product_images", joinColumns = {
            @JoinColumn(name = "product_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "image_id")
    }
    )
    private Set<ImageModel> productImages;

    @OneToOne(mappedBy = "lap_id")
    private Laptop laptopConfig;

    @OneToOne(mappedBy = "smartId")
    private SmartPhone smartPhoneConfig;

    @OneToOne(mappedBy = "accessId")
    private Accessory accessoryConfig;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonIgnore
    private Set<Comment> comment = new HashSet<>();
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Review> reviews = new HashSet<>();
}
