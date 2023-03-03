package sg.edu.nus.iss.app.workshop13.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import sg.edu.nus.iss.app.workshop13.model.Contact;

@Component("contacts")
public class Contacts {
    
    public void saveContact(Contact ctc, Model model, ApplicationArguments appArgs,
        String defaultDataDir){
        String datafilename = ctc.getId();
        PrintWriter printWriter = null;


    //prints contact list to file via printwriter with directory appArgs .data.dir and file name using contact ID
        try {
            //filewriter-printwritter based on .data.dir from input
            FileWriter fileWriter =new FileWriter(getDataDir(appArgs, defaultDataDir)
                + "/" + datafilename);
            printWriter = new PrintWriter(fileWriter);
            //print each field to file
            printWriter.println(ctc.getName());
            printWriter.println(ctc.getEmail());
            printWriter.println(ctc.getPhoneNumber());
            printWriter.println(ctc.getDateOfBirth());
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //adds contact information to the model as a contact Object
        model.addAttribute("contact", new Contact(ctc.getId(), 
                ctc.getName(), ctc.getEmail(), ctc.getPhoneNumber(), ctc.getDateOfBirth()));
    }

    public void getContactById(Model model, String contactId, ApplicationArguments appArgs,
        String defaultDataDir){

        //have to create a new contact instance as a placeholder object to read the file data into     
        Contact ctc = new Contact();

        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            //read the file data from txt to the placeholder object
            Path filePath=  new File(getDataDir(appArgs, defaultDataDir)
                                                + "/" + contactId).toPath(); 
            Charset charset= Charset.forName("UTF-8");
            List<String> stringValues = Files.readAllLines(filePath, charset);
            ctc.setId(contactId);
            ctc.setName(stringValues.get(0));
            ctc.setEmail(stringValues.get(1));
            ctc.setPhoneNumber(stringValues.get(2));

            //Localdate class has datetime formater.
            LocalDate dob = LocalDate.parse(stringValues.get(3), formatter);
            ctc.setDateOfBirth(dob); // localdate class
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        //add placeholder contact to model to display
        model.addAttribute("contact", ctc);
    }

    public void getAllContacts(Model model, ApplicationArguments appArgs, 
        String defaultDataDir){

        //returns lists of contacts based on the directory of files. use set to filter out duplicate IDs?
        Set<String> dataFiles = listFiles(getDataDir(appArgs, defaultDataDir));

        //adds list of contacts to model to be displayed
        model.addAttribute("contacts", dataFiles);
    }

    //calls listFiles methods which returns a stream of the files and also filters out directories
    private Set<String> listFiles(String dir){
        return Stream.of(new File(dir).listFiles())
            .filter(file -> !file.isDirectory())
            .map(File::getName)
            .collect(Collectors.toSet());
    }

    //gets the datadirectory from the command line arguments during the maven build, if not use default data directory, for filewriting
    private String getDataDir(ApplicationArguments appArgs, String defaultDataDir){
        String dataDirResult = "";

        List<String> optValues = null;
        String[] optValuesArr = null;
        //gets arguments from CLI into opsNames Set 
        Set<String> opsNames = appArgs.getOptionNames();
        //convert to array to be able to extract first argument name  at index 0
        String[] opsNamesArr = opsNames.toArray(new String[opsNames.size()]);

        if(opsNamesArr.length > 0){
            //List of String containing argument values. get the value corresponding to the first argument names that was extracted from getOptionanames
            // has to be a list because there may be more than one value per argument name
            optValues = appArgs.getOptionValues(opsNamesArr[0]);
            //convert to string array in order to extract first argument value at index 0
            optValuesArr = optValues.toArray(new String[optValues.size()]);
            dataDirResult = optValuesArr[0];
        }else{
            dataDirResult = defaultDataDir;
        }
        return dataDirResult;
    }

    
}
