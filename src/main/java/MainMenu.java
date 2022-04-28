import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
/*
 * MainMenu displays main menu for the user, first screen user sees when starting app.
 * When user finishes game it will bring him back to the main menu
 */
public class MainMenu {

    void showMainMenu() throws IOException {
        boolean runGame = true;
        welcome();
//
        while (runGame) {
            TextParser.textInputMainMenu();
//          TODO GAME FUNCTION GOES HERE
//
//      for testing verbs
//      String response = TextParser.gameScannerInput();
//      TextParser.gameScannerOutput(response, player1);
//      for testing player
//      Player player1 = new Player();
//      player1.readMap();

        }
    }

    private void welcome() throws IOException {
        String banner = Files.readString(Path.of("asciiArt/mainMenu"));
        System.out.println(banner);
    }


}
