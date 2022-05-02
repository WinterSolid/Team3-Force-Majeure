import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Room {
    public String name;
    public String description;
    public HashMap<String, String> directions;
    public HashMap<String, String> looking;
    public List<String> npcs;

    String getName() {
        return this.name;
    }

    String getDescription() {
        return this.description;
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
