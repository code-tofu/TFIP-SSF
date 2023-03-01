package sg.edu.nus.iss.app.workshop12.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.app.workshop12.exception.RandNoException;
import sg.edu.nus.iss.app.workshop12.model.Generate;

@Controller
@RequestMapping(path="/rand")
public class GenerateRandController {
    
    //hence URL is http://localhost:8080/rand/show
    // when show is accessed, the generate g object is created and added to the model
    @GetMapping(path="/show")
    public String showRandForm(Model m){
        Generate g = new Generate();
        m.addAttribute("generateObj", g);
        return "generate";
        //generate is returned to thyme, so that it can load generate.html, or get the generate resource which calls the randomizerNum method.
    }


    //for method using query string http://localhost:8080/rand/generate?numberVal=20
    @GetMapping(path="/generate")
    public String generate(@Valid @RequestParam Integer numberVal, Model m){
        this.randomizerNum(m, numberVal.intValue());
        // intValue unboxes the generated random number from randomizerNum
        return "result";
    }

    // for method using path variable http://localhost:8080/rand/generate/20
    @GetMapping(path="/generate/{numberVal}")
    public String generateRandByPathVar(@PathVariable Integer numberVal, Model m){
        this.randomizerNum(m, numberVal.intValue());
        return "result";
    }

    private void randomizerNum(Model m, int noOfGenerateNo){
        int maxGenNo = 31;
        String[] imgNumbers = new String[maxGenNo];

        //validate the number input from the form
        if(noOfGenerateNo < 1 || noOfGenerateNo > maxGenNo-1){
            throw new RandNoException();
        }

        //construct the file names
        for(int x = 0; x < maxGenNo; x++){
            imgNumbers[x] = "number" + x + ".jpg";
        }

        Random rand = new Random();
        Set<Integer> uniqueResult = new LinkedHashSet<Integer>();
        // Generates a set of Integers that will be used to select the images from the resource folder
        // based on requirements, images are generated in random order, hence Linked Hashset is used to not have duplicates and retain insertion order.
        // Integer is used because Collections need to be objects, rather than primitives
        while(uniqueResult.size() < noOfGenerateNo){
            Integer randNumberVal = rand.nextInt(maxGenNo);
            if(randNumberVal != null)
            //check for null in case it returns null due to nonblocking class. can syncrhonise the call instead?
                uniqueResult.add(randNumberVal);
        }

        List<String> selectedImg = new ArrayList<String>();
        //Based on the ordered set, add the paths of each image from the imgNumbers String Array into the selectedImg array
        Integer currElem = null;
        for (Iterator<Integer> iter = uniqueResult.iterator(); iter.hasNext();){
            //for (initialization; termination; increment)
            //note that there is no increment expression (3rd expression)
            //when the termination expression evaluates to false (2nd expression), the loop terminates.
            currElem = (Integer)iter.next(); //get the value of the element from the Set uniqueResult
            // iter is the implementation class of Iterator interface, hence needs to be casted to Integer
            // Iterator <Integer> is parametrized to Integer. Interface Iterator<E>, where E - the type of elements returned by this iterator, so that that interface is paramterized and knows what next() is returning
            selectedImg.add(imgNumbers[currElem.intValue()]); //select the corresponding path based on the element from the Set and add to the selectedImg array
        }
        // Also used:
        // Iterator<Integer> i = uniqueResult.iterator();
        // Integer currElem = null;
        // while(i.hasNext()){
        //     currElem = i.next();
        //     selectedImg.add(imgNumbers[currElem.intValue()]);
        // }
        
        // add the number of image generates so that result.html can access and display the value, accessed by the key "numberRandomNum"
        m.addAttribute("numberRandomNum", noOfGenerateNo);

        //add the entire array into the model, so that we can use
        m.addAttribute("randNumResult", selectedImg.toArray());
        

    }
}