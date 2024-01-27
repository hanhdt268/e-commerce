package com.example.graduationbe.entities.commerce;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReturnOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long returnId;
    private String note;
    private Date dateReturn;

    @OneToOne
    @JsonIgnore
    private Order order;
}
