package com.hardik.RestaurantBackend.repositories;

import com.hardik.RestaurantBackend.models.FoodItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface Food_Item_Repository extends JpaRepository<FoodItems, Long> {
    void deleteFoodItemById(Long id);

    Optional<FoodItems> findFoodItemById(Long id);
}
