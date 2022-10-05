
import game.GameState;
import game.cards.Pile;

import console.ConsoleDisplay;
import console.ConsoleReader;
import console.datamaps.Command;

/**
 * This class contains main method for Patience game.
 * @version 1.0 4/10/22
 * @author Mark Crowley
 */

public class Patience{
    public static void main(String[] args){
        //Ranks rank = Ranks.QUEEN;
        //System.out.print(rank.ordinal());

        // Create gamestate object to run and keep track of game 
        GameState gameState = new GameState();

        // Create object to display game state to console
        ConsoleDisplay display = new ConsoleDisplay();
        ConsoleReader reader = new ConsoleReader();
        Command input_type;

        // Variables used to map user input to card piles
        char labelA;
        char labelB;

        // Variables store information about move
        int validCardIndex;
        int cardCount;

        while(gameState.isGameOver() == false){
            // Display the current gamestate
            display.displayTable(gameState);

            // Prompt user for command
            reader.readUserInput();
            input_type = reader.getInputType();

            // Switch statement based on user command
            switch(input_type){
                case QUIT:
                    gameState.setGameOver(true);
                    break;
                case DRAW:
                    gameState.drawCard();
                    gameState.incrementMovesMade();
                    break;
                case MOVE:   
                    // Get labels from console
                    labelA = reader.getLabelA();
                    labelB = reader.getLabelB();

                    // Get piles referred to in user input
                    Pile pileA = gameState.getPileFromLabel(labelA);
                    Pile pileB = gameState.getPileFromLabel(labelB);

                    // Check if move is allowed
                    validCardIndex = gameState.isValidMove(labelA, labelB, pileA, pileB);

                    // Make move if possible
                    if(validCardIndex != -1){
                        System.out.print("Valid move\n");
                        cardCount = gameState.makeMove(pileA, pileB, validCardIndex);
                        gameState.incrementScore(labelA, labelB, cardCount);
                        gameState.incrementMovesMade();
                    }
                    else{
                        display.printInvalidMove();
                    }

                    // Check if user won the game with last move
                    if(gameState.isGameWon() == true){
                        gameState.setGameOver(true);
                    }

                    break;
                case ERROR:
                    display.printInvalidCommand();
                    break;
            }
        }
        reader.closeScanner();
    }
}