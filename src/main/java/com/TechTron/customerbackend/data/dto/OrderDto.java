package com.TechTron.customerbackend.data.dto;

import com.TechTron.customerbackend.data.entity.Cart;
import com.TechTron.customerbackend.data.entity.Customer;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderDto {

    private int orderId;
    private int customerIdFk;
    private int cartIdFk;
    private int totalItems;
    private Date orderDate;
    private String status;
    private String deliveryMethod;
    private String paymentType;
    private float totalAmount;
}
