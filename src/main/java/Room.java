import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Room {
    public String name;
    public String description;
    public String npc;
    public String item;
//    public HashMap<String, String> item;
    public HashMap<String, String> directions;
    public HashMap<String, String> looking;
    public List<String> npcs;


    String getName() {
        return this.name;
    }

    String getDescription() {
        return this.description;
    }
    String getItem() {
        return this.item;
    }
    void removeItem(String item){
        this.item = "none";
    }

    String getRoomNameFromDirections(String direction) {
        return directions.get(direction);
    }

    String getLookingDirection(String direction) {
        return looking.get(direction);
    }

    List<String> getNpcs() {
        return this.npcs;
    }

}
