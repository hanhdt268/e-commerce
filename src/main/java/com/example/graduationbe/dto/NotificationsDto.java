package com.example.graduationbe.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class NotificationsDto implements Serializable {
    private Long idNo;
    private int count;
    private Date date;

    private ProductDto product;

    public NotificationsDto() {
    }

    public NotificationsDto(int count, Date date, ProductDto product) {
        this.count = count;
        this.date = date;
        this.product = product;
    }
}
