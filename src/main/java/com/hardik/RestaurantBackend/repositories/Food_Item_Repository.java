package com.hardik.RestaurantBackend.repositories;

import com.hardik.RestaurantBackend.models.FoodItems;
import org.springframework.data.jpa.repository.JpaRepository;


public interface Food_Item_Repository extends JpaRepository<FoodItems, Long> {
}
