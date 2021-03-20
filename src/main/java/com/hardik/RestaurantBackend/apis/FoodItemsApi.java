package com.hardik.RestaurantBackend.apis;

import java.util.Base64;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/fooditem")
public class FoodItemsApi {

    @Autowired
    private FoodItemService foodItemService;
    @Autowired
    ServletContext context;

    @GetMapping("/fooditems")
    public List<FoodItems> getAllarticles() {
        System.out.println("Get all articles...");

        List<FoodItems> foodItems = new ArrayList<>();
        foodItemService.getAllFoodItems().forEach(foodItems::add);

        return foodItems;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<String>> getAllFoodItem(){
        List<String> listfooditem = new ArrayList<String>();
        String filesPath = context.getRealPath("/Images");
        File filefolder = new File(filesPath);
        if (filefolder != null)
        {
            for (File file :filefolder.listFiles())
            {
                if(!file.isDirectory())
                {
                    String encodeBase64 = null;
                    try {
                        String extension = FilenameUtils.getExtension(file.getName());
                        FileInputStream fileInputStream = new FileInputStream(file);
                        byte[] bytes = new byte[(int)file.length()];
                        fileInputStream.read(bytes);
                        encodeBase64 = Base64.getEncoder().encodeToString(bytes);
                        listfooditem.add("data:image/"+extension+";base64,"+encodeBase64);
                        fileInputStream.close();
                    }catch (Exception e){

                    }
                }
            }
        }
        return new ResponseEntity<List<String>>(listfooditem,HttpStatus.OK);
    }


    @GetMapping(path = "/imagefooditem/{id}")
    public byte[] getImage(@PathVariable("id") Long id) throws Exception{
        FoodItems foodItem = foodItemService.findFoodItemById(id);
        return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+foodItem.getFilename()));
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<FoodItems> getFoodItemById(@PathVariable ("id") Long id) {
        FoodItems foodItem = foodItemService.findFoodItemById(id);
        return new ResponseEntity<>(foodItem,HttpStatus.OK);
    }



    @PostMapping(value = "/add")
    public ResponseEntity<Response> addfooditem(@RequestParam("file") MultipartFile file,
                                                @RequestParam("fooditemdata") String fooditemdata) throws JsonParseException, JsonMappingException, Exception {
        FoodItems foodItems = new ObjectMapper().readValue(fooditemdata,FoodItems.class);
        boolean isExit = new File(context.getRealPath("/Images/")).exists();
        if (!isExit)
        {
            new File (context.getRealPath("/Images/")).mkdir();
            System.out.println("mk dir.............");
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
        try
        {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile,file.getBytes());

        }catch(Exception e) {
            e.printStackTrace();
        }
        foodItems.setFilename(newFileName);
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
