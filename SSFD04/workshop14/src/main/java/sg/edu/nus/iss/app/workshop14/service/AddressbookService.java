package sg.edu.nus.iss.app.workshop14.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.app.workshop14.model.Contact;
import sg.edu.nus.iss.app.workshop14.repository.AddressbookRepository;
//just a placeholder since there is no business logic
//intermediary class that calls the repository methods when called by the controller
@Service
public class AddressbookService {
    @Autowired
    private AddressbookRepository adrbkRepo;

    public void save(final Contact ctc){
        adrbkRepo.save(ctc);
    }

    public Contact findById(final String contactId){
        return adrbkRepo.findById(contactId);
    }    

    public List<Contact> findAll(int startIndex){
        return adrbkRepo.findAll(startIndex);
    }
}
