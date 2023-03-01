package sg.edu.nus.iss.app.workshop11;

import java.util.Collections;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Workshop11Application {
    private static String portNumber = null;
    private static String DEFAULT_PORTNUM = "3000";
    
    public static void main(String[] args) {
    

        // java -jar target/workshop11-0.0.1-SNAPSHOT.jar --port=5055
        // java -jar target/workshop11-0.0.1-SNAPSHOT.jar --Dport=5055
        for(String argVal: args){
            
            System.out.println("argVal > " +  argVal);
            if(argVal.contains("--Dport=") || argVal.contains("--port=")){
                System.out.println("contains > ");
                System.out.println("Using CLI Port Number");
                portNumber = argVal.substring(argVal.length() - 4, argVal.length()); //assuming the 
                System.out.println("args portNumber > " +  portNumber);
                // write a test case for port number is 5, or substring afgter --port?
            }
        }

        //mvn clean spring-boot:run -Dspring-boot.run.arguments=--port=5051
        //mvn clean spring-boot:run -Dspring-boot.run.arguments=--Dport=5055
        //mvn clean spring-boot:run -Dspring-boot.run.arguments=--port=5050
        ApplicationArguments appArgs = new DefaultApplicationArguments(args);
        if (appArgs.containsOption("port")){
            System.out.println("Using App Properties Port Number");
            portNumber = appArgs.getOptionValues("port").get(0);
        }

        // export PORT=xxxx
        // mvn spring-boot:run
        // if null, try and get from env var
        if(portNumber  == null){
            System.out.println("Using Sys Env Port Number");
            portNumber = System.getenv("PORT");
            System.out.println("env portNumber > " +  portNumber);
        }

        // unset PORT
        // export PORT= or set PORT=
        // mvn spring-boot:run
        // null and blank are different test cases.
        // if  null and also env blank, use default port num
        if(portNumber == null || portNumber.isBlank()){
            System.out.println("Using Default Port Number");
            portNumber = DEFAULT_PORTNUM;
        }
        //  java.lang.NullPointerException: Cannot invoke "String.isBlank()" because "sg.edu.nus.iss.app.workshop11.Workshop11Application.portNumber" is null
        
        SpringApplication app = 
            new SpringApplication(Workshop11Application.class); //by right default pplication class will chope of the -D
        app.setDefaultProperties(
            Collections.singletonMap("server.port", portNumber));
        app.run(args);
    }

}