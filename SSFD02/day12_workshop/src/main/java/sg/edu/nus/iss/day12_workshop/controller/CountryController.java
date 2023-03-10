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
    
    //creates a new service of countryservice, which also creates a country repo
    //countryrepo creates a new country lists and populates it with hardcoded input when GetallCountries is called
    //since there is no path specified for get and post mapping, it automatically calls the method when the root path /countries is called?

    CountryService ctySvc;

    public CountryController() {
        ctySvc = new CountryService();
    }

    // @GetMapping(produces = {"application/xml"})
    //xml output:
    // <List>
        // <item>
            // <code>SG</code>
            // <name>Singapore</name>
            // <population>6000000</population>
        // </item>
        // <item>
            // <code>MY</code>
            // <name>Malaysia</name>
            // <population>33000000</population>
        // </item>
        // <item>
            // <code>CN</code>
            // <name>China</name>
            // <population>1430000000</population>
        // </item>
        // <item>
            // <code>HK</code>
            // <name>Hong Kong</name>
            // <population>7000000</population>
        // </item>
    // </List>
    @GetMapping
    public @ResponseBody List<Country> getAllCountries() {
        //get all countries in ctysvc calls get all countries in ctyrepo which returns a List<Country>
        //responsebody returns the list as a json or xml tot he get mapping request
        return ctySvc.getAllCountries();
        //where is this returning to?
        
    }

    @PostMapping()
    public @ResponseBody Boolean createCountry(@RequestBody Country cty) {
        return ctySvc.createCountry(cty);
        //where is this returning to?
    }

    //hence the URL is /countries/list, since it is in the countries controller
    //note that this is the full version of request mapping of method GET, rathre than using getmapping
    @RequestMapping(path="/list", method=RequestMethod.GET)
    public String displayCountryList(Model model) {
        List<Country> ctyList = ctySvc.getAllCountries();
        model.addAttribute("countriesList", ctyList);

        return "countryList";
    }

}
