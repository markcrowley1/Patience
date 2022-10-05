
package console;

import java.util.Scanner;

import console.datamaps.Command;

import java.lang.String;

public class ConsoleReader{
    private Scanner scanner;
    private String userInput;
    private Command inputType;
    private char labelA;
    private char labelB;
    private String validLabels = "1234567dhcsp";

    public ConsoleReader(){
        // Create scanner object for user input
        this.scanner = new Scanner(System.in);
    }

    public void readUserInput(){
        System.out.print("\n-->");
        userInput = scanner.nextLine().toLowerCase();
        System.out.print('\n');

        if(userInput.equals("q")){
            inputType = Command.QUIT;
        }
        else if(userInput.equals("d")){
            inputType = Command.DRAW;
        }
        else if(userInput.length() == 3){
            // Get first and last characters in string
            char charA = userInput.charAt(0);
            char charB = userInput.charAt(userInput.length() - 1);

            if(validLabels.indexOf(charA) != -1 && validLabels.indexOf(charB) != -1){
                labelA = charA;
                labelB = charB;
                inputType = Command.MOVE;
            }
            else{
                inputType = Command.ERROR;
            }
        }
        else{
            inputType = Command.ERROR;
        }
    }

    public Command getInputType(){
        return inputType;
    }

    public char getLabelA(){
        return labelA;
    }

    public char getLabelB(){
        return labelB;
    }

    public void closeScanner(){
        scanner.close();
    }
}