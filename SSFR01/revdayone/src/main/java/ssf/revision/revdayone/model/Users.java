package ssf.revision.revdayone.model;

import java.util.ArrayList;

public class Users {
    private ArrayList<String> usersList;

    public ArrayList<String> getUsersList() {
        return usersList;
    }

    public void addUsers(String userInput) {
        usersList.add(userInput);
    }

    public Users() {
        usersList = new ArrayList<>();
        usersList.add("user1");
        usersList.add("user2");
        usersList.add("user3");
        usersList.add("user4");
    }

    public Users(ArrayList<String> usersList) {
        this.usersList = usersList;
    }

    @Override
    public String toString() {
        String userstring = "usersList=";
        for (int i = 0; i < usersList.size(); i++) {
         userstring += "," + usersList.get(i);
        }
        return "User [" + userstring + "]";
    }

    
    

}
