package com.dev.backend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductDTO {

    private Long id;

    private String sku;

    private String name;

    private String description;

    private BigDecimal unitPrice;

    private String imageUrl;

    private boolean active;

    private int unitsInStock;

    private Date createdAt;

    private Date updatedAt;
}
