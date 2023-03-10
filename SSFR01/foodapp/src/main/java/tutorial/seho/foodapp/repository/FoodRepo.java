package tutorial.seho.foodapp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import tutorial.seho.foodapp.model.Food;
import tutorial.seho.foodapp.util.RedisUtil;

@Repository
public class FoodRepo {

    @Autowired
    RedisTemplate <String,String> foodDatabase;

    private static final String FOOD_KEY = "myFoodList";

    public List<Food> getAllFoodDB(){
        List <String> dbQuery = foodDatabase.opsForList().range(FOOD_KEY, 0, -1);
        // System.out.println(dbQuery);
        List<Food> allFoodList = RedisUtil.parseDBList(dbQuery);
        System.out.println(allFoodList);
        return allFoodList;
    }
        
    public void addFoodDB(Food foodInput){
        foodDatabase.opsForList().rightPush(FOOD_KEY, RedisUtil.foodString(foodInput));
    }

    public void delFoodDB(String foodRem){
        foodDatabase.opsForList().remove(FOOD_KEY,0, foodRem);
    }

    
}
