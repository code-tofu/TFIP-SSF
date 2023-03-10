package sg.edu.nus.iss.day12_workshop.service;

import sg.edu.nus.iss.day12_workshop.repository.CountryRepo;
import java.util.*;
import sg.edu.nus.iss.day12_workshop.model.*;

public class CountryService {

    CountryRepo ctyRepo;

    public CountryService() {
        ctyRepo = new CountryRepo();
    }

    public List<Country> getAllCountries() {
        return ctyRepo.getAllCountries();
    }

    public Boolean createCountry(Country cty) {
        Boolean result = ctyRepo.createCountry(cty);
        return result;
    }
}
