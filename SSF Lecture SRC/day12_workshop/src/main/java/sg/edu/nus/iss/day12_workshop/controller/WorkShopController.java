package sg.edu.nus.iss.day12_workshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping(path="/workshop")
public class WorkShopController {
    
    @GetMapping()
    public String workshop() {

        return "workshop";
    }

    @PostMapping("/processNumber")
    public String processNumber(@RequestParam(name="inputNumber", defaultValue="1") Integer unit, Model model) {

        List<Integer> lstIntegers = new ArrayList<Integer>();

        Integer loopValue = unit;
        Integer currentValue = 1;

        while(currentValue <= loopValue) {
            int result = (int) ((Math.random() * loopValue)) + 1;

            if (!lstIntegers.contains(Integer.valueOf(result))) {
                lstIntegers.add(Integer.valueOf(result));
                currentValue++;
            }
        }

        model.addAttribute("numbers", lstIntegers);

        return "workshopresult";
    }
}
