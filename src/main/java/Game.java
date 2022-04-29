import java.io.IOException;
import java.util.Map;

public class Game {

    void runGame() throws Exception {
        // get roomMap
        GameData gameData = new GameData();
        Map<String, Room> roomMap = gameData.loadGame();
        // get starting room ("Water")
        Room startRoom = roomMap.get("Water Wreckage");
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
//        TODO add game plot
        System.out.println("This is our game plot. FIX ME!!!");

        boolean gameRunning = true;
        while (gameRunning) {
//            Player Location
            System.out.println("Current Room=" + player.getCurRoom().getName());
//            Show user where they can go Compass
//            Give player a description of current area
//            prompt User
            String response = TextParser.gameScannerInput();
            TextParser.gameScannerOutput(response, player, roomMap);
        }
    }
}
