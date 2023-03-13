package sg.edu.nus.iss.workshop16.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Pegs implements Serializable{
    private int total_count;
    private List<Type> types;

    public int getTotal_count() {
        return total_count;
    }
    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }
    public List<Type> getTypes() {
        return types;
    }
    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public JsonObjectBuilder toJSON(){
        //create an array builder i.e. initialize the array
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        // create list of json object builders
        List<JsonObjectBuilder> listOfTypes = this.getTypes()
            .stream()
            .map(t -> t.toJSON())
            .toList();

        // add all the object builders into the JSON object array (array builder) i.e. populate the array
        for(JsonObjectBuilder x: listOfTypes)
            arrBuilder.add(x);

        return Json.createObjectBuilder()
            .add("total_count", this.getTotal_count())
            //add array of objectbuilders to the main object
            .add("types", arrBuilder);
    }

    public static Pegs createJson(JsonObject o){
        // create peg object to be returned
        Pegs pp = new Pegs();

        // create a list of peg types to eventually populate
        List<Type> result = new LinkedList<Type>();
        
        JsonNumber totalCnt = o.getJsonNumber("total_count");
        pp.setTotal_count(totalCnt.intValue());

        //get jsonarray returns the json array from the json object
        JsonArray types = o.getJsonArray("types");
        
        for(int i=0; i < types.size(); i++){
            //getJson object from array 
            JsonObject x = types.getJsonObject(i);
            //create type object from json object
            Type t = Type.createJson(x);
            //add type object to type list
            result.add(t);
        }
        //set the placeholder list to the peg object's array field
        pp.setTypes(result);
        
        return pp;
    }
    
}
