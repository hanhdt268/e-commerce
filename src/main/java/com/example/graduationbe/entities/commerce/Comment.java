package com.example.graduationbe.entities.commerce;

import com.example.graduationbe.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long comId;

    @Column(length = 10000)
    private String content;

    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @OneToOne
    private User user;

}
