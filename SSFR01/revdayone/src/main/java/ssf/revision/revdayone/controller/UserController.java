package ssf.revision.revdayone.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ssf.revision.revdayone.model.User;

@Controller
public class UserController {

    @GetMapping("/register")
    public String showForm(Model model) {
        User user = new User();
        model.addAttribute("userObj", user);
        List<String> listProfession = Arrays.asList("Developer", "Tester", "Architect");
        model.addAttribute("listProfession", listProfession);
        return "register_form";
    }

    @PostMapping("/register2")
    //use model attribute to pass the associated attributes into the method
    public String submitForm(@ModelAttribute("userObj") User user) {
        System.out.println(user);
        return "register_success";
        //the template name is different from the resource name
    }
     
}
