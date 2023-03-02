package sg.edu.nus.iss.day12_workshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;

import sg.edu.nus.iss.day12_workshop.model.Item;
import sg.edu.nus.iss.day12_workshop.service.ItemService;
import org.springframework.ui.Model;

@Controller
@RequestMapping(path = {"/shoppingCart"})
//note case sensitive
public class ItemController {
    
    @Autowired
    ItemService itmSvc;
    //item service is autowired to itemrepo which creates the list of car items

    @GetMapping(produces = "text/html")
    public String displayCart(Model model) {
        List<Item> items = itmSvc.retriveItemList();
        model.addAttribute("cartItems", items);
        return "cartList";
    }
    

    @GetMapping("{itemname}")
    public String filteredCart(@PathVariable(name="itemname") String itemname, Model model) {
        List<Item> items = itmSvc.retriveItemList();
        List<Item> foundItems = items.stream().filter(i -> i.getItemName().equalsIgnoreCase(itemname)).collect(Collectors.toList());
        model.addAttribute("cartItems", foundItems);

        //returns the stream filtered item: http://localhost:3000/shoppingCart/Hermes = Hermes	15
        return "cartList";
    }
}
