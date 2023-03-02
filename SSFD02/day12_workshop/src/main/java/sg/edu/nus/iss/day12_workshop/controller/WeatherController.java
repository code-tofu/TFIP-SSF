package sg.edu.nus.iss.day12_workshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    // you must include the city for the get to work else "Required parameter 'city' is not present."\

    @GetMapping
    public String weather(@RequestParam(required = true) String city, @RequestParam(name="units", defaultValue="kilometers") String units, Model model) {
        model.addAttribute("city", city);
        model.addAttribute("units", units);

        // /passing in the path variable into the URL e.g. http://localhost:3000/weather?city=malaysiapore&units=sumthingmeters

        // The city is malaysiapore
        // The selected metric is sumthingmeters

        return "weather";
    }
 
    // http://localhost:3000/weather/Singapore?units=meters
    @GetMapping("{country}")
    public String weather2(@PathVariable(name="country", required = true) String city, @RequestParam(name="units", defaultValue="kilometers") String units, Model model) { //specifying default values
        model.addAttribute("city", city);
        model.addAttribute("units", units);

        //passing in the path variable into the URL e.g. http://localhost:3000/weather/12345?units=pesudometers, output"
        // The city is 12345
        // The selected metric is pesudometers

        return "weather";
    }

}
