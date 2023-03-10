package sg.edu.nus.iss.day12_workshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import sg.edu.nus.iss.day12_workshop.service.CountryService;
import java.util.*;
import sg.edu.nus.iss.day12_workshop.model.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping(path = {"/countries"})
public class CountryController {
    
    CountryService ctySvc;

    public CountryController() {
        ctySvc = new CountryService();
    }

    // @GetMapping(produces = {"application/xml"})
    @GetMapping
    public @ResponseBody List<Country> getAllCountries() {
        return ctySvc.getAllCountries();
    }

    @PostMapping()
    public @ResponseBody Boolean createCountry(@RequestBody Country cty) {
        return ctySvc.createCountry(cty);
    }

    @RequestMapping(path="/list", method=RequestMethod.GET)
    public String displayCountryList(Model model) {
        List<Country> ctyList = ctySvc.getAllCountries();
        model.addAttribute("countriesList", ctyList);

        return "countryList";
    }

}
