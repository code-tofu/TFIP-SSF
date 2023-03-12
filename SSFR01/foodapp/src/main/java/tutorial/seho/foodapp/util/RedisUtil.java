package tutorial.seho.foodapp.util;

import java.util.LinkedList;
import java.util.List;
import java.math.BigDecimal;


import tutorial.seho.foodapp.model.Food;


public class RedisUtil {


    public static List <Food> parseDBList(List<String> dbQuery){
        List<Food> foodList = new LinkedList<>();
        for(String item : dbQuery){
            String[] itemStr = item.split("-");
            foodList.add(new Food(itemStr[0],BigDecimal.valueOf(Double.parseDouble(itemStr[1]))));
        }
        return foodList;
    }

    public static String foodString(Food foodInput){
        String foodstr = foodInput.getName() + "-" + foodInput.getPrice(); //Java autocasts
        return foodstr;
    }


    public static void addEgFoodnoDB(List<Food> foodList, int qty){
        for(int i = 0; i < qty; i++){
            Food newFood = new Food("Dish"+Integer.toString(i), BigDecimal.valueOf((double)(i*i)));
            foodList.add(newFood);
        }  
    }
}
