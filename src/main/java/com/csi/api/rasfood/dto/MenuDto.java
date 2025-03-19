package com.csi.api.rasfood.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String nameCategory;

    public MenuDto(String name, String description, BigDecimal price, String nameCategory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.nameCategory = nameCategory;
    }

    public MenuDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
