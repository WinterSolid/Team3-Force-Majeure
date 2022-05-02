import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Game {
//    String gameIntro = Files.readString(Path.of("asciiArt/intro"));

    public Game() {
    }

    void runGame() throws Exception {
        // get roomMap
//        GameData gameData = new GameData();
        Map<String, Room> roomMap = Data.roomMap;
        Map<String, NPC> npcMap = Data.npcMap;
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
//        System.out.println(gameIntro);

        boolean gameRunning = true;
        while (gameRunning) {
            Room curRoom = player.getCurRoom();
//            Player Location
            System.out.println("Current Room=" + curRoom.getName());
            // print description
            System.out.println("Description=" + curRoom.getDescription());
            if (curRoom.getNpcs() != null) {
                System.out.println("NPCs=" + List.of(curRoom.getNpcs()));
            }

//            Give player a description of current area
//            *CODE HERE*
//            prompt User
            String response = TextParser.gameScannerInput();
            TextParser.gameScannerOutput(response, player, roomMap, npcMap);
        }
    }
}
