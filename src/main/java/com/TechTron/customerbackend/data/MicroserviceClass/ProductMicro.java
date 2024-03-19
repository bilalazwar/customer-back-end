package com.TechTron.customerbackend.data.MicroserviceClass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties({"sellerIdFk", "categoryIdFk", "name", "description","filePath", "price"})
public class ProductMicro {

    private int productId;
    private int sellerIdFk;
    private int categoryIdFk;
    private String name;
    private String description;

    private float price;
    private String filePath;

}
