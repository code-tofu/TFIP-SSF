package sg.edu.nus.iss.day12_workshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;

import java.util.*;

@Controller
@RequestMapping(path={"/home", "/index"})
public class HomeController {
    
    @GetMapping()
    public String home(Model model) {
        Calendar cal = Calendar.getInstance();
        
        model.addAttribute("currTime", new Date().toString());
        model.addAttribute("currHour", cal.get(Calendar.HOUR_OF_DAY));
        return "index";
    }
}
