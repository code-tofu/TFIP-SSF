package tutorial.seho.foodapp;

import java.util.Collections;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodappApplication {

	public static void main(String[] args) {

		SpringApplication.run(FoodappApplication.class, args);

		/*
		// to run withn options
		SpringApplication app = new SpringApplication(FoodappApplication.class);
		String port = "8080"; //default port
		ApplicationArguments cliOption = new DefaultApplicationArguments(args); 
		if(cliOption.containsOption("port")){
			port = cliOption.getOptionValues("port").get(0); //getOptionValues returns a string array
		}

		app.setDefaultProperties(Collections.singletonMap("server.port", port));
		System.out.printf("FoodappApplication started on Port %s",port);
		app.run(args);
		*/
	}

}
