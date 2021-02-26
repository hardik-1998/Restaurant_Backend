package com.hardik.RestaurantBackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "food_items")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})

public class FoodItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;


    @Column(name = "Food_Item_Name")
    private String foodItemName;

    @Column(name = "Category")
    private String category;

    @Column(name = "Price")
    private double price;

    @Column(name = "Available")
    private String available;

    @Column(name = "Description")
    private String description;

    @Column(name = "Food_Item_Image")
    private byte[] foodItemImage;


    @JsonFormat(shape =JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "Date_Create")
    private Date createDate;

    @JsonFormat(shape =JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @Column(name = "Date_Update")
    private Date updateDate;

    public String getFoodItemName() {
        return foodItemName;
    }

    public void setFoodItemName(String foodItemName) {
        this.foodItemName = foodItemName;
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

    public byte[] getFoodItemImage() {
        return foodItemImage;
    }

    public void setFoodItemImage(byte[] foodItemImage) {
        this.foodItemImage = foodItemImage;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "FoodItems{" +
                "id=" + id +
                ", foodItemName='" + foodItemName + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", available='" + available + '\'' +
                ", description='" + description + '\'' +
                ", foodItemImage=" + Arrays.toString(foodItemImage) +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
