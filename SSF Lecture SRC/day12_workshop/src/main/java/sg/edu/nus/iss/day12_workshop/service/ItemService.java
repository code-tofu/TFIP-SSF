package sg.edu.nus.iss.day12_workshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import sg.edu.nus.iss.day12_workshop.model.Item;
import sg.edu.nus.iss.day12_workshop.repository.ItemRepo;

@Service
public class ItemService {

    @Autowired
    ItemRepo itmRepo;

    // public ItemService() {
    // itmRepo = new ItemRepo();
    // }

    public List<Item> retriveItemList() {
        return itmRepo.getCartItems();
    }
}
