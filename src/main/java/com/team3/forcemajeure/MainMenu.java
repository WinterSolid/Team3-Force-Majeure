package com.team3.forcemajeure;

import com.team3.forcemajeure.util.Audio;
import com.team3.forcemajeure.util.Data;
import com.team3.forcemajeure.util.TextParser;

import java.io.*;
/*
 * com.team3.forcemajeure.MainMenu displays main menu for the user, first screen user sees when starting app.
 * When user finishes game it will bring him back to the main menu
 */
public class MainMenu {
        Audio audio = Audio.getInstance();


    void executes() throws IOException {
//        primarly runs game
        boolean runGame = true;

        while (runGame) {
            showMainMenu();
            Game game = new Game();
            game.runGame();
            replay();
        }
    }
    void showMainMenu() throws IOException {
//        runs main menu
        boolean runGame = true;
        audio.play("start");

        welcome();
//      keeps prompting user for valid response
        while (runGame) {
            String startGame = TextParser.textInputMainMenu();
            if (startGame.equals("game")) {
                runGame = false;
                audio.stop();
            } else if (startGame.equals("mute")) {
                audio.toggleMute();
            }
        }
    }
    void replay (){
//        used to replay game
        boolean validInput = false;
        while (!validInput){
            System.out.println("Please enter [Start] to play again or [Quit] to exit the game: ");
            String playerInput = TextParser.textInputMainMenu();
            if (playerInput.equals("game")){
                validInput = true;
            }
        }
    }

    private void welcome() {
//        prints com.team3.forcemajeure.MainMenu Ascii
        System.out.println(Data.getTextMap().get("mainMenu"));
    }
}
