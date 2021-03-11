package com.hardik.RestaurantBackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;
import org.hibernate.metamodel.model.domain.IdentifiableDomainType;

import javax.persistence.*;
import java.util.Arrays;



@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FoodItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String name;

    private String category;

    private double price;

    private String available;
    private String description;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] image;

    @JsonFormat(shape =JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    private String createDate;

    @JsonFormat(shape =JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    private String updateDate;

    public FoodItems(){}

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "FoodItems{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", available='" + available + '\'' +
                ", description='" + description + '\'' +
                ", image=" + Arrays.toString(image) +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }
}
