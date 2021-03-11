package com.hardik.RestaurantBackend.apis;

import com.hardik.RestaurantBackend.models.FoodItems;
import com.hardik.RestaurantBackend.repositories.Food_Item_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fooditem")
public class FoodItemsApi {

    @Autowired
    private Food_Item_Repository foodItemRepository;

    @GetMapping
    public List<FoodItems> allItem(){
        return foodItemRepository.findAll();
    }

    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody FoodItems foodItems){
        foodItemRepository.save(foodItems);
    }

    @GetMapping("/{id}")
    public FoodItems get(@PathVariable("id") long id) {
        return foodItemRepository.getOne(id);
    }
}
