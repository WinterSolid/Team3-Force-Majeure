
/*
 *
 */

import java.util.Scanner;

public class TextParser {
    // INSTANCE VARIABLES
    static Scanner scanner = new Scanner(System.in);

    //  Get user input
    public static String textInputMainMenu(){
        String userInput = "";
        while (2 != userInput.split(" ").length){
            System.out.print(">:");
            userInput = scanner.nextLine().toLowerCase().stripLeading().stripTrailing();
            if (userInput.equals("1")){
                System.out.println("staring game");
            }
            else if (userInput.equals("2")){
                System.out.println("Exiting the game");
                System.exit(0);
            }
            else{
                System.out.println(userInput + " Not valid command please put 1 or 2");
            }
        }
        return userInput;
    }
    public static String gameScannerInput(){
        String userInput = "";
        while (2 != userInput.split(" ").length){
            System.out.print(">:");
            userInput = scanner.nextLine().toLowerCase().stripLeading().stripTrailing();
            if (userInput.equals("help")){
//                Loop through all the commands that user can use which we will not hard code
                System.out.println("Printing all the commands you can use");
            }
            else if (userInput.equals("quit")){
//                add exiting the game
                System.out.println("Exiting the game");
                System.exit(0);
            }
        }
        return userInput;
    }

    //  Parse through user input
    public static void gameScannerOutput(String gameInput){
        // Add list of verbs here to cycle through
        if (gameInput.contains("go")) {
//          Going to replace below print statement with changing the players location
            System.out.println("Going " + gameInput.split(" ")[1]);
        }
        else{
//            If its not a correct answer just tell user
            System.out.println(gameInput + " not valid response");
        }
    }

}
