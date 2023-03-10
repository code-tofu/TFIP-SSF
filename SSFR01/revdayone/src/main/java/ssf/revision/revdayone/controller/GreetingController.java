package ssf.revision.revdayone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
// @RequestMapping //works for  http://127.0.0.1:8080/ (no)
@RequestMapping("") // @RequestMapping("") is equivalent to @RequestMapping("/")?
// @RequestMapping({"/greeting2","/"}) 
//does not work for http://127.0.0.1:8080/greeting2/ but works for http://127.0.0.1:8080/greeting2

public class GreetingController {

	@GetMapping
	public String greeting(@RequestParam(name="name", required=false, defaultValue="there") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting2";
	}

}
