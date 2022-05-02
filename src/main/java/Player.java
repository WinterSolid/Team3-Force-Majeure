import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Player {
//    TODO prob should not hardcode this
//    Setting Beach since that is our starting location
//    Variables
    Room curRoom;
    ArrayList<String> inventory = new ArrayList<>();
    String playerMap;

//    Constructor
    public Player() throws IOException, URISyntaxException {
//        starting player off with map for right now
        this.playerMap = Data.textMap.get("playerMap");
        inventory.add("map");
    }

//    Business Methods
    public void readMap() {
        String playerLocation = getCurRoom().getName();
        if (inventory.contains("map")){
            System.out.println(playerMap.replace(playerLocation+"[ ]",
                    playerLocation+"[X]"));
        }
        else{
            System.out.println("I dont see map in your inventory");
        }
    }

//  Getter and Setter
    public ArrayList<String> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }


    public Room getCurRoom() {
        return curRoom;
    }

    public void setCurRoom(Room room) {
        this.curRoom = room;
    }



}
