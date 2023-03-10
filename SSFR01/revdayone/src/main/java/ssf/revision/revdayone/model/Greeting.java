package ssf.revision.revdayone.model;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public class Greeting {

        //remember that if and only if no constructor is provide, Java will have the default no-args constructor

        private String name; //will default as null. Java assigns the memory for the var (pointer) name but the pointer will be null since it has yet to be initialized?
        private String chicken;
        private String drink;
        
        public String getDrink() {
            return drink;
        }

        public void setDrink(String drink) {
            this.drink = drink;
        }

        private List<String> testList;
        private String selectedOption;
      
        public String getName() {
            // if(name == null) return "there";
            // possible but thyme will still render there when page loads
          return name;
        }
      
        public void setName(String name) {
          this.name = name;
        }

        public String getChicken() {
            return chicken;
        }

        public void setChicken(String chicken) {
            this.chicken = chicken;
        }

        public List <String> generateList(){
            List<String> testList = new ArrayList<String>();
                testList.add("listitem1");
                testList.add("listitem2");
                testList.add("listitem3");
                testList.add("listitem4");
            return testList;
        }

        public List<String> getTestList() {
            return testList;
        }

        public void setTestList(List<String> testList) {
            this.testList = testList;
        }

        public String getSelectedOption() {
            return selectedOption;
        }

        public void setSelectedOption(String selectedOption) {
            this.selectedOption = selectedOption;
        }

        
        
}
