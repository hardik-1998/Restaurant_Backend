package com.hardik.RestaurantBackend.service;

import com.hardik.RestaurantBackend.exception.FoodItemNotFoundException;
import com.hardik.RestaurantBackend.models.FoodItems;
import com.hardik.RestaurantBackend.repositories.Food_Item_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FoodItemService {

    @Autowired
    private Food_Item_Repository foodItemRepository;

    public FoodItems addFoodItem(FoodItems foodItems) {
        return foodItemRepository.save(foodItems);
    }

    public List<FoodItems> getAllFoodItems(){
        return foodItemRepository.findAll();
    }

    public FoodItems updateFoodItem(FoodItems foodItems){
        return foodItemRepository.save(foodItems);
    }

    public FoodItems findFoodItemById(Long id){
        return foodItemRepository.findFoodItemById(id).
                orElseThrow(()-> new FoodItemNotFoundException("FoodItem by id "+id+ "was not found"));
    }

    public void deleteFoodItem(Long id){
        foodItemRepository.deleteFoodItemById(id);
    }


}
