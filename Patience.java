
import java.util.ArrayList;

import game.Card;
import game.Deck;
import game.Pile;
import game.enums.*;

import console.ConsoleDisplay;
import console.ConsoleReader;
import console.Command;

public class Patience{
    public static void main(String[] args){
        //Ranks rank = Ranks.QUEEN;
        //System.out.print(rank.ordinal());

        // Create object to display game state to console
        ConsoleDisplay display = new ConsoleDisplay();
        ConsoleReader reader = new ConsoleReader();
        Command input_type;

        // Create and shuffle deck of cards
        Deck deck = new Deck();
        deck.populateDeck();
        deck.shuffleDeck();

        // Arraylists are created for random piles,
        // foundation piles and a pile is created to represent drawn cards.
        ArrayList<Pile> foundationPiles = new ArrayList<Pile>();
        ArrayList<Pile> randomPiles = new ArrayList<Pile>();
        Pile drawnPile = new Pile();

        // Create 4 empty foundation piles
        for(Suits suit: Suits.values()){
            Pile pile = new Pile(suit);
            foundationPiles.add(pile);
        }

        // Create 7 random piles and populate them with cards from deck
        // Card on top of each card is made visible
        for(int i = 0; i < 7; i++){
            Pile pile = new Pile();
            for(int j = 0; j < i + 1; j++){
                Card card = deck.drawCard();
                pile.addCard(card);
            }
            pile.flipCard();
            randomPiles.add(pile);
        }

        // Variables to be used within game loop
        int movesMade = 0;
        int score = 0;
        int labelA;
        int labelB;
        boolean continueLoop = true;
        // Game loop
        while(continueLoop){
            // Display the current gamestate
            display.displayTable(foundationPiles, randomPiles, drawnPile, movesMade, score);
            // Prompt user for command
            System.out.print("\n-->");
            reader.readUserInput();
            System.out.print('\n');
            input_type = reader.getInputType();
            // Switch statement based on user command
            switch(input_type){
                case QUIT:
                    continueLoop = false;
                    break;
                case DRAW:
                    Card card = deck.drawCard();
                    drawnPile.addCard(card);
                    break;
                case MOVE:   
                    labelA = reader.getLabelA();
                    labelB = reader.getLabelB();

                    movesMade++;
                    System.out.print("You selected labels: " + labelA + ' ' + labelB + '\n');
                    break;
                case ERROR:
                    System.out.print("That is not a valid command.\n");
                    break;
            }
        }
        reader.closeScanner();
    }
}