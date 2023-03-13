package sg.edu.nus.iss.workshop16.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Pieces implements Serializable{
    private DecodingBoard decoding_board;
    private Pegs pegs;
    private Rulebook rulebook;

    public DecodingBoard getDecoding_board() {
        return decoding_board;
    }
    public void setDecoding_board(DecodingBoard decoding_board) {
        this.decoding_board = decoding_board;
    }
    public Pegs getPegs() {
        return pegs;
    }
    public void setPegs(Pegs pegs) {
        this.pegs = pegs;
    }
    public Rulebook getRulebook() {
        return rulebook;
    }
    public void setRulebook(Rulebook rulebook) {
        this.rulebook = rulebook;
    }

    public JsonObjectBuilder toJSON(){
        return Json.createObjectBuilder()
            .add("decoding_board", this.getDecoding_board()
                .toJSON())
            .add("pegs", this.getPegs().toJSON())
            .add("rulebook", this.getRulebook().toJSON());  
            //toJSON provides the JSON object builder to the add method to be added (i.e. nested object builders for nested objects)
            //method to get the instance of the child object within the parent object hence this.getXXX()
    }

    public static Pieces createJson(JsonObject o){
        Pieces p = new Pieces();

        //getJsonObject returns nested Json object from parent JSON object
        JsonObject decodingBoard = o.getJsonObject("decoding_board");
        JsonObject pegs = o.getJsonObject("pegs");
        JsonObject rb = o.getJsonObject("rulebook");

        //pass objects to their respective object converters
        // after they have been converted, passed the returned objects into the pieces object p to return a "populated" pieces object
        p.decoding_board = DecodingBoard.createJson(decodingBoard);
        p.pegs = Pegs.createJson(pegs);
        p.rulebook = Rulebook.createJson(rb);
        return p;
    }

}
