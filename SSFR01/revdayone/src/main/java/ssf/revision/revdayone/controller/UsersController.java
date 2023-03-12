package ssf.revision.revdayone.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ssf.revision.revdayone.model.Users;

//playing around with responsebody, response entity etc
@Controller
@RequestMapping("/users")
public class UsersController {

    @GetMapping
    public String getUsers(Model model) {
        Users userlist1 = new Users();
        model.addAttribute("usersListTh",userlist1.getUsersList());
        return "users";
    }
    
    @GetMapping("/repbody")
    @ResponseBody
    public ArrayList<String> printUsersRepBody() {
        Users userlist1 = new Users();
        return userlist1.getUsersList();
    }

    @GetMapping("/newuserHello")
    public @ResponseBody String helloNew(){
        return "200 OKAY: RESPONSE BODY: HELLO NEW PERSON!";
    }

    //can i only use requestbody using a postman application?
    @PostMapping("/newuser")
    public ResponseEntity<HttpStatus> newUsersRepBody(@RequestBody String newUser) {
        Users userlist1 = new Users();
        userlist1.addUsers(newUser);
        System.out.print(userlist1);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/repentity")
    @ResponseBody
    public ResponseEntity<List<String>> printUsersRepEntity() {
        Users userlist1 = new Users();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        /**
        Create a ResponseEntity with a body, headers, and a status code. 
        Parameters: body the entity body
        Headers: the entity headers
        status: the status code
        */
        return new ResponseEntity<>(userlist1.getUsersList(),headers,HttpStatus.OK);
    }

    @GetMapping("/reqPamResBody")
    @ResponseBody //in the response body hence browser displays
    public String reqPamResBody(@RequestParam String id) {
        return "ID: " + id;
    }
    //(type=Bad Request, status=400) Required request parameter 'id' for method parameter type String is not present

    @GetMapping("/id")
    public String getID() {
        return "id";
    }


    @PostMapping("/printID")
    public String printID(@ModelAttribute("helloID") String id) {
        return "id";
    }

    @PostMapping("/mappingMVpost")
    public ResponseEntity<HttpStatus> MVmapping(@RequestBody MultiValueMap<String,String> getMVmap){
        System.out.println(getMVmap);
        System.out.println(getMVmap.get("newUser"));
        System.out.println(getMVmap.get("newEmail"));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/mappingMVreqPar")
    @ResponseBody
    public String MVmappingGet(@RequestParam MultiValueMap<String,String> getMVmap){
        System.out.println(getMVmap.get("newUser"));
        System.out.println(getMVmap.get("newEmail"));
        return "User:" + getMVmap.get("newUser") + " Email:" + getMVmap.get("newEmail");
        // http://127.0.0.1:8080/users/mappingMVreqPar?newUser=freddiemercury&newEmail=queen
        // http://127.0.0.1:8080/users/mappingMVreqPar?newUser=freddiemercury&newEmail=queen@gmail.com - does it autoconvert query content into string?
    }

    @PostMapping("/mappingMVpostURLen")
    public ResponseEntity<HttpStatus> MVmappingURL(@RequestBody MultiValueMap<String,String> getMVmap){
        System.out.println(getMVmap);
        System.out.println(getMVmap.get("newUser"));
        System.out.println(getMVmap.get("newEmail"));
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
