package tutorial.seho.foodapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tutorial.seho.foodapp.model.Food;
import tutorial.seho.foodapp.repository.FoodRepo;

@Service
public class FoodService {

    @Autowired private FoodRepo foodRepo;
    
    public List<Food> getAllFood(){
        return foodRepo.getAllFoodDB();
    }
    
    public void addFood(Food foodInput){
        foodRepo.addFoodDB(foodInput);
    }

    public void delFood(String foodRem){
        foodRepo.delFoodDB(foodRem);
    }

}
