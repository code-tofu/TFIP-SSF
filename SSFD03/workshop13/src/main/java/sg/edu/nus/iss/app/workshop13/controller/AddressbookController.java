package sg.edu.nus.iss.app.workshop13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.app.workshop13.model.Contact;
import sg.edu.nus.iss.app.workshop13.util.Contacts;

@Controller
@RequestMapping(path="/contact")
public class AddressbookController {

    @Autowired
    private Contacts ctcs;

    @Autowired
    private ApplicationArguments appArgs;

    @Value("${workshop13.default.data.dir}")
    private String defaultDataDir;
    
    @GetMapping
    public String showAddressBookForm(Model model){
        model.addAttribute("contact", new Contact());
        return "addressbook";
    }
    //contact is added to the model    

    @PostMapping
    public String saveContact(@Valid Contact contact, BindingResult binding , Model model){
        if(binding.hasErrors()){
            return "addressbook";
        }

        // In an object if any single validation failed then bindingResult.hasError() will return true,hence validation for object failed.
        //  bindingResult.hasError() will be false only if all validations in the object are satisfied.
        // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/validation/Errors.html

        ctcs.saveContact(contact, model, appArgs, defaultDataDir);
        //when the user submits the form, postmethod calls save contact in Contacts.java
        //prints contact list to file via printwriter with directory appArgs .data.dir and file name using contact ID
        //adds contact to the model
        //returns the url showing the details of the contact added. URL doesn't change? field titles are hardcoded
        return "showContact";
    }

    @GetMapping(path="{contactId}")
    public String getContactId(Model model, @PathVariable String contactId){

        //calls getcontact by ID using the contact ID path 
        ctcs.getContactById(model, contactId, appArgs, defaultDataDir);

        //creates a contact instance, reads the data from the file named by contact ID. adds contacts instance into model and displys
        return "showContact";
    }

    @GetMapping(path="/list")
    public String getAllContacts(Model model){
        ctcs.getAllContacts(model, appArgs, defaultDataDir);
        return "contacts";
    }
}
