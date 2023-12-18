package com.example.graduationbe.dto;

import com.example.graduationbe.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class ReviewDto implements Serializable {
    private Long reId;

    @Column(length = 10000)
    private String content;
    private Double value;
    private String image;
    private Date dateCreate;
    private boolean active = true;
    private User user;

}
