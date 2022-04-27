import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MainMenu {



    void showMainMenu() throws IOException {
        boolean runGame = true;
        welcome();

        while (runGame) {
            TextParser.textInputMainMenu();
//          TODO GAME FUNCTION GOES HERE
        }
    }

    private void welcome() throws IOException {
        String banner = Files.readString(Path.of("asciiArt/mainMenu"));
        System.out.println(banner);
    }


}
