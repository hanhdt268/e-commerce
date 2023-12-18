package com.example.graduationbe.entities.commerce;

import com.example.graduationbe.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reId;

    @Column(length = 10000)
    private String content;
    private Double value;

    private String image;
    private Date dateCreate;
    private boolean active = true;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @OneToOne
    private User user;

    public Review(String content, Double value, String image, Date dateCreate) {
        this.content = content;
        this.value = value;
        this.image = image;
        this.dateCreate = dateCreate;
    }
}
