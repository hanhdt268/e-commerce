package com.example.graduationbe.entities.commerce;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "notifications")
public class Notifications implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idNo;

    private int count;
    private Date date;
    @OneToOne
    private Product product;

    public Notifications() {
    }

    public Notifications(int count, Date date, Product product) {
        this.count = count;
        this.date = date;
        this.product = product;
    }
}