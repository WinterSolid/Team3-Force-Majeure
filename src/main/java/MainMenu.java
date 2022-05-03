import java.io.*;
/*
 * MainMenu displays main menu for the user, first screen user sees when starting app.
 * When user finishes game it will bring him back to the main menu
 */
public class MainMenu {
    private final Audio audio = Audio.getInstance();

    void showMainMenu() throws IOException {
        boolean runGame = true;

        welcome();
//      keeps prompting user for valid response
        while (runGame) {
            String startGame = TextParser.textInputMainMenu();
            if (startGame.equals("game")) {
                runGame = false;
                audio.stop();
            }
        }
    }

    private void welcome() {
        System.out.println(Data.textMap.get("mainMenu"));
    }
}
