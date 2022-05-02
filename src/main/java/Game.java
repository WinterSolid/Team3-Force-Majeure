import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Game {
//    String gameIntro = Files.readString(Path.of("asciiArt/intro"));

    //  IO exception is for helpBanner read
    public Game() throws IOException {
    }

    void runGame() throws Exception {
        // get roomMap
//        GameData gameData = new GameData();
        Map<String, Room> roomMap = Data.roomMap;
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
//            Player Location
            System.out.println("Current Room=" + player.getCurRoom().getName());
            // print description
            System.out.println("Description=" + player.getCurRoom().getDescription());
//            print items
            System.out.println("items= " + player.getCurRoom().getItem());
//            Give player a description of current area
//            *CODE HERE*
//            prompt User
            String response = TextParser.gameScannerInput();
            TextParser.gameScannerOutput(response, player, roomMap);
        }
    }
}
