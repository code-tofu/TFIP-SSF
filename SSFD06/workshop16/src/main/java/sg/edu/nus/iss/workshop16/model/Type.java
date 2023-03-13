package sg.edu.nus.iss.workshop16.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Type implements Serializable{
    //specifically, type of peg
    private String type;
    private int count;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }


    //converts object to JSON
    public JsonObjectBuilder toJSON(){
        return Json.createObjectBuilder()
            .add("type", this.getType())
            .add("count", this.getCount());
    }


    //create java object from json file
    public static Type createJson(JsonObject o){
        //create a new instance of java object
        Type t = new Type();

        //use get methods of JSON object.
        JsonNumber count  = o.getJsonNumber("count");
        String type = o.getString("type");
        
        //use setters to set fields of the java object
        //intValue() Returns this JSON number as an int.
        t.setCount(count.intValue());
        t.setType(type);
        return t;
    }
}
