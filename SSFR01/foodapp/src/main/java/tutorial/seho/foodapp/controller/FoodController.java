package tutorial.seho.foodapp.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import tutorial.seho.foodapp.model.Food;
import tutorial.seho.foodapp.repository.FoodRepo;
import tutorial.seho.foodapp.service.FoodService;

@Controller
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/food")
    public String getFoodMain(Model model){
        // List <Food> egFoods = new LinkedList<>();
        // RedisUtil.addEgFoodnoDB(egFoods,5);
        model.addAttribute("foodInput",new Food());
        model.addAttribute("foodList", foodService.getAllFood());
        return "food";
    }

    @PostMapping("/food")
    public String addFoodMain(@Valid @ModelAttribute("foodInput") Food foodInput, BindingResult binding, Model model, @RequestBody String strUrl){
        // model.addAttribute("foodInput",foodInput); not need if method parameter has name, then the binding will done
        System.out.println(strUrl); //as long as you want to access the request body you can use @requestbody
        if (binding.hasErrors()) {
            System.out.println("Error Binded");
            model.addAttribute("foodList", foodService.getAllFood());
            return "food";
        }

        if(foodInput.getName().equals("chicken")
        && foodInput.getPrice().doubleValue() > 5.0){
            System.out.println("Global Error Binded");
            ObjectError err = new ObjectError ("globalError", "Chicken is overpriced!");
            binding.addError(err);
            model.addAttribute("foodList", foodService.getAllFood());
            return "food";
        }


        model.addAttribute("successMessage", String.format("Added %s at $ %.2f",foodInput.getName(),foodInput.getPrice()));
        foodService.addFood(foodInput);
        model.addAttribute("foodList", foodService.getAllFood());
        // return "redirect:food"; //request a redirect to reset fields
        return "food";
    }

    @PostMapping("/delete")
    public String addFoodMain(@RequestBody String deleteString, Model model){
        System.out.println(deleteString);
        deleteString = '&' + deleteString;
        String[] deleteArray = deleteString.split("&");
        System.out.println(Arrays.deepToString(deleteArray));
        for (String foodRem : deleteArray){
            foodRem = foodRem.replace("del=", "");
            System.out.println(foodRem);
            foodService.delFood(foodRem);
        }
        model.addAttribute("deleteMessage","Deleted Selected Items");
        model.addAttribute("foodInput",new Food());
        model.addAttribute("foodList", foodService.getAllFood());
        return "food";
    }
    
}


