package com.TechTron.customerbackend.data.dto;

import com.TechTron.customerbackend.data.MicroserviceClass.ProductMicro;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {

    private int cartItemId;
    private int cartIdFk;
    private int quantity;
    private float unitPrice;
    private float totalPrice;

    private ProductMicro productMicro;

}
