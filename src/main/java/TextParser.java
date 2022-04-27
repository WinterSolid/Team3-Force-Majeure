
/*
 * TextParser class deals with all the user text input in the game
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class TextParser {
//    INSTANCE VARIABLES
    static Scanner scanner = new Scanner(System.in);

//    Main Menu text parser
    public static String textInputMainMenu(){
        String userInput = "";
        while (2 != userInput.split(" ").length){
            System.out.print(">:");
            userInput = scanner.nextLine().toLowerCase().stripLeading().stripTrailing();
            if (userInput.equals("start")){
                System.out.println("staring game");
            }
            else if (userInput.equals("quit")){
                System.out.println("Exiting the game");
                System.exit(0);
            }
            else{
                System.out.println(userInput + " Not valid command please put start or quit");
            }
        }
        return userInput;
    }

//    Game text parser
    public static String gameScannerInput() throws IOException {
        String helpBanner = Files.readString(Path.of("asciiArt/help"));
        String userInput = "";
        while (2 != userInput.split(" ").length){
            System.out.print(">:");
            userInput = scanner.nextLine().toLowerCase().stripLeading().stripTrailing();
            if (userInput.equals("help")){
                System.out.println(helpBanner);
            }
            else if (userInput.equals("quit")){
//                add exiting the game
                System.out.println("Exiting the game");
                System.exit(0);
            }
            else {
                System.out.println(userInput + " not valid response if need help please say help");
            }
        }
        return userInput;
    }

//      Parse through user input
    public static void gameScannerOutput(String gameInput){
//        variables
        String verb = gameInput.split(" ")[0];
        String noun = gameInput.split(" ")[1];
//        possible verbs user can use. If you update this please update /asciiArt/help thank.
//        TODO need to add method for moving player in here
        if (verb.contains("go")) {
                switch (noun){
                    case "north":
                        System.out.println("walking north");
                        break;
                    case "east":
                        System.out.println("walking east");
                        break;
                    case "south":
                        System.out.println("walking south");
                        break;
                    case "west":
                        System.out.println("walking west");
                        break;
                    default:
                        System.out.println("Please put in valid direction");
            }
        }
//        TODO need method to look through user Inventory
        else if(gameInput.contains("use")){
            System.out.println("USE needs a method that will search inventory");

        }
//        TODO need method to look through user Inventory Description
        else if(gameInput.contains("look")){
            System.out.println("Look needs a method that will look at invetory item description");

        }

        else{
//            If its not a correct answer just tell user
            System.out.println(gameInput + " not valid response if need help please say help");
        }
    }
}
