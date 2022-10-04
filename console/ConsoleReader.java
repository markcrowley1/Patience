
package console;

import java.util.Scanner;
import java.lang.String;

public class ConsoleReader{
    private Scanner scanner;
    private String userInput;
    private Command inputType;
    private int labelA;
    private int labelB;

    public ConsoleReader(){
        // Create scanner object for user input
        this.scanner = new Scanner(System.in);
    }

    public void readUserInput(){
        userInput = scanner.nextLine().toLowerCase();

        if(userInput.equals("q")){
            inputType = Command.QUIT;
        }
        else if(userInput.equals("d")){
            inputType = Command.DRAW;
        }
        else if(userInput.length() > 1){
            // Get first and last characters in string
            char charA = userInput.charAt(0);
            char charB = userInput.charAt(userInput.length() - 1);

            if(Character.isDigit(charA) && Character.isDigit(charB)){
                int intA = charA - '0';
                int intB = charB - '0';

                if((0 < intA && intA < 8) && (0 < intB && intB < 8) && intA != intB){
                    labelA = intA;
                    labelB = intB;
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
        else{
            inputType = Command.ERROR;
        }
    }

    public Command getInputType(){
        return inputType;
    }

    public int getLabelA(){
        return labelA;
    }

    public int getLabelB(){
        return labelB;
    }

    public void closeScanner(){
        scanner.close();
    }
}