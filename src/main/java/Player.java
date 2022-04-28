import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Player {
//    TODO prob should not hardcode this
//    Setting Beach since that is our starting location
//    Variables
    String playerLocation = "Beach";
    ArrayList<String> inventory = new ArrayList<>();
    String playerMap = Files.readString(Path.of("asciiArt/playerMap"));


//    Constructor
    public Player() throws IOException {
//        starting player off with map for right now
        inventory.add("map");
    }

//    Business Methods
    public void readMap() {
        if (inventory.contains("map")){
            System.out.println(playerMap.replace(playerLocation+"[ ]", playerLocation+"[X]"));
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


    public String getPlayerLocation() {
        return playerLocation;
    }

    public void setPlayerLocation(String playerLocation) {
        this.playerLocation = playerLocation;
    }



}
