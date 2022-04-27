import java.io.IOException;

public class Game {

    void runGame(){
//        MainMenu menu = new MainMenu();
//        try {
//            menu.showMainMenu();
//        } catch (
//                IOException e) {
//            e.printStackTrace();
//        }

        GameData gameData = new GameData();
        gameData.loadGame();
    }

}
