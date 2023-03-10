package sg.edu.nus.iss.day12_workshop.repository;

import java.util.*;
import sg.edu.nus.iss.day12_workshop.model.*;

public class CountryRepo {

    List<Country> countryList;

    public List<Country> getAllCountries() {

        countryList = new ArrayList<Country>();
        Country cty = new Country("SG", "Singapore", 6000000);
        countryList.add(cty);
        cty = new Country("MY", "Malaysia", 33000000);
        countryList.add(cty);
        cty = new Country("CN", "China", 1430000000);
        countryList.add(cty);
        cty = new Country("HK", "Hong Kong", 7000000);
        countryList.add(cty);

        return countryList;
    }

    public Boolean createCountry(Country cty) {

        if (countryList == null) {
            countryList = new ArrayList<Country>();
        }

        countryList.add(cty);
        return true;
    }
}
