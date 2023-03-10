package ssf.revision.revdayone.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ssf.revision.revdayone.model.Greeting;

@Controller
@RequestMapping("/greeting1")
public class GreetController {

    @Value("${extra.message}")
    private String extramsg;

    @GetMapping()
    public String greetingForm(Model model) {
        Greeting newgreeting = new Greeting();
        model.addAttribute("greetingObj",newgreeting);
        //thyme access the object based on the attribute name, not the object name in this class
        model.addAttribute("extramessage", extramsg);
        model.addAttribute("testListTh",newgreeting.generateList());
        return "greeting1";
    }

    @PostMapping()
    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
        model.addAttribute("greetingObj", greeting);
        System.out.println(greeting.getChicken());
        //you can return to the same template
        System.out.println(greeting.getSelectedOption());
        System.out.println(greeting.getDrink());
        return "greeting1";
    }

    @GetMapping("/{specialMsg}")
    public String greetingForm(@PathVariable("specialMsg") String special, Model model) {
        Greeting newgreeting = new Greeting();
        model.addAttribute("greetingObj",newgreeting);
        //thyme access the object based on the attribute name, not the object name in this class
        model.addAttribute("extramessage", special);
        model.addAttribute("testListTh",newgreeting.generateList());
        return "greeting1";
    }

    @GetMapping("/paramMsg")
    public String greetingForm2(@RequestParam("param") String paraMsg, Model model) {
        Greeting newgreeting = new Greeting();
        model.addAttribute("greetingObj",newgreeting);
        //thyme access the object based on the attribute name, not the object name in this class
        model.addAttribute("extramessage", paraMsg);
        model.addAttribute("testListTh",newgreeting.generateList());
        return "greeting1";
    }

}

