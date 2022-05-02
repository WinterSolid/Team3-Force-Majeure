import java.util.HashMap;
import java.util.List;

public class Room {
    public String name;
    public String description;
    public HashMap<String, String> directions;
    public HashMap<String, String> looking;
    public List<String> npcNames;

    String getName() {
        return this.name;
    }

    String getDescription() {
        return this.description;
    }

    String getRoomNameFromDirections(String direction) {
        return directions.get(direction);
    }

    String getLookingDirection(String lookings){
        return looking.get(lookings);
    }

    List<String> getNpcNames() {
        return npcNames;
    }
}
