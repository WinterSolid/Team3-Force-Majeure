import java.util.HashMap;

public class Room {
    public String name;
    public String description;
    public String npc;
    public String item;
    public HashMap<String, String> directions;
    public HashMap<String, String> looking;
//    public HashMap<String, String> item;

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

    String getNpc() {
        return this.npc;
    }

    String getRoomNameFromDirections(String direction) {
        return directions.get(direction);
    }

    String getLookingDirection(String lookings){
        return looking.get(lookings);
    }

}
