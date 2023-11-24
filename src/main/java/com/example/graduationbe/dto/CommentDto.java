package com.example.graduationbe.dto;

import com.example.graduationbe.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto implements Serializable {
    private Long comId;

    @Column(length = 10000)
    private String content;

    private Date date;
    private User user;


}
