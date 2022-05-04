import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Game {
    Audio audio = Audio.getInstance();
//      Variables
    boolean gameRunning = true;
//    constructor
    public Game() {
    }
//    Business methods
    void runGame() {
        audio.play("start");
        Map<String, Room> roomMap = Data.roomMap;
        Map<String, NPC> npcMap = Data.npcMap;
        Map<String, Endings> endingsMap = Data.endingMap;
        // get starting room ("Water")
        Room startRoom = roomMap.get("WaterWreckage");
        // init Player
        Player player = new Player();
        // set player's current room to start room
        player.setCurRoom(startRoom);

        MainMenu menu = new MainMenu();
        try {
            menu.showMainMenu();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

//        Start Game
        while (gameRunning) {
            // get current room
            Room curRoom = player.getCurRoom();
            // play room audio
            audio.play(curRoom.getName().toLowerCase());
            // display room info
            curRoom.displayRoomInfo();
//            prints story element for doctors office
            if (player.curRoom.getName().equals("DoctorsOffice") && (
                    !player.inventory.getInventory().contains("larson") &&
                    !player.inventory.getInventory().contains("karma"))){
                System.out.println(player.getCurRoom().getStory());
            }
//            Give player a description of current area
//            *CODE HERE*
//            prompt User
            String response = TextParser.gameScannerInput();
          
            if ("mute".equals(response)) {
                audio.toggleMute();
            } else {
                TextParser.gameScannerOutput(response, player, roomMap, npcMap, endingsMap);
            }

        }
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }
}
