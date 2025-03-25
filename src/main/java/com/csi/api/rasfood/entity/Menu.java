package com.csi.api.rasfood.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "menu")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Boolean status;
    private BigDecimal price;
    @Column(name = "register_date")
    private LocalDateTime createdAt = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.LAZY)
    private MenuCategory menuCategory;
    @Lob
    private byte[] image;

    public Menu() {}

    public Menu(String name, String description, Boolean status, BigDecimal price, LocalDateTime createdAt, MenuCategory category) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.price = price;
        this.createdAt = createdAt;
        this.menuCategory = category;
    }

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public MenuCategory getCategory() {
        return menuCategory;
    }

    public void setCategory(MenuCategory category) {
        this.menuCategory = category;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", category=" + menuCategory +
                '}';
    }
}
