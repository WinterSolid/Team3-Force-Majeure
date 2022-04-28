import java.io.IOException;
import java.util.Map;

public class Game {

    void runGame() throws IOException {
        // get roomMap
        GameData gameData = new GameData();
        Map<String, Room> roomMap = gameData.loadGame();
        // get starting room ("Water")
        Room startRoom = roomMap.get("Water");
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


    }

}
