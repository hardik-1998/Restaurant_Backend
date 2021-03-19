package com.hardik.RestaurantBackend.apis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hardik.RestaurantBackend.messages.Response;
import com.hardik.RestaurantBackend.models.FoodItems;
import com.hardik.RestaurantBackend.repositories.Food_Item_Repository;
import com.hardik.RestaurantBackend.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/fooditem")
public class FoodItemsApi {

    @Autowired
    private FoodItemService foodItemService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<FoodItems>> getAllFoodItem(){
        List<FoodItems> foodItems = foodItemService.getAllFoodItems();
        return new ResponseEntity<>(foodItems,HttpStatus.OK);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<FoodItems> getFoodItemById(@PathVariable ("id") Long id) {
        FoodItems foodItem = foodItemService.findFoodItemById(id);
        return new ResponseEntity<>(foodItem,HttpStatus.OK);
    }



    @PostMapping(value = "/add")
    public ResponseEntity<Response> addfooditem(@RequestParam("file") MultipartFile file,
                                                @RequestParam("fooditemdata") String fooditemdata) throws JsonProcessingException, IOException {
        FoodItems foodItems = new ObjectMapper().readValue(fooditemdata,FoodItems.class);
        foodItems.setImage(file.getBytes());
        foodItems.setCreateDate(new Date());
        FoodItems dbFoodItem = foodItemService.addFoodItem(foodItems);
        if (dbFoodItem!=null){
            return new ResponseEntity<Response>(new Response("FoodItem saved successfully to the database!!"),HttpStatus.CREATED);
        }else {
            return new ResponseEntity<Response>(new Response("FoodItem is not saved!!"),HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(value = "/update")
    public ResponseEntity<Response> updatefooditem(@RequestParam("file") MultipartFile file,
                                                @RequestParam("fooditemdata") String fooditemdata) throws JsonProcessingException, IOException {
        FoodItems foodItems = new ObjectMapper().readValue(fooditemdata,FoodItems.class);
        foodItems.setImage(file.getBytes());
        foodItems.setUpdateDate(new Date());
        FoodItems dbFoodItem = foodItemService.updateFoodItem(foodItems);
        if (dbFoodItem!=null){
            return new ResponseEntity<Response>(new Response("FoodItem updated successfully to the database!!"),HttpStatus.OK);
        }else {
            return new ResponseEntity<Response>(new Response("FoodItem is not updated to database!!"),HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteFoodItem(@PathVariable ("id") Long id){
        foodItemService.deleteFoodItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
