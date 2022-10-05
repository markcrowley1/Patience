package console;

import java.util.ArrayList;
import java.lang.Math;

import console.datamaps.RankMap;
import console.datamaps.SuitMap;

import game.GameState;
import game.cards.Card;
import game.cards.Pile;
import game.cards.Deck;

public class ConsoleDisplay{
    RankMap rankMap;
    SuitMap suitMap;

    public ConsoleDisplay(){
        rankMap = new RankMap();
        suitMap = new SuitMap();
    }

    public void displayTable(GameState gameState){
        // Get information about game state
        ArrayList<Pile> foundationPiles = gameState.getFoundationPiles();
        ArrayList<Pile> randomPiles = gameState.getRandomPiles();
        Deck deck = gameState.getDeck();
        Pile drawnPile = gameState.getDrawnPile();
        int movesMade = gameState.getMovesMade();
        int score = gameState.getScore();
        int deckCardCount = deck.getDeckSize();
        
        // Instantiate variables for string output
        char suitSymbol;
        String rankSymbol;

        // Print info on moves made and score
        System.out.print("Moves Made:    " + movesMade + '\n');
        System.out.print("Current Score: " + score + '\n');

        // Print information about foundation piles
        System.out.print("\nFoundation Piles:\n");

        for(Pile pile: foundationPiles){
            suitSymbol = suitMap.getSymbol(pile.getRequiredSuit());
            System.out.print(' ');
            System.out.print(suitSymbol);
            System.out.print(" \t");
        }

        System.out.print('\n');

        for(Pile pile: foundationPiles){
            if(pile.getVisibleCardCount() > 0){
                Card card = pile.getTopCard();
                rankSymbol = rankMap.getSymbol(card.getRank());
                suitSymbol = suitMap.getSymbol(card.getSuit());
                System.out.print(rankSymbol + ' ');
                System.out.print(suitSymbol);
            }
            System.out.print('\t');
        }

        // Print information about card lanes
        System.out.print("\n\nCard Lanes:\n");

        // Provide label for each card lane
        for(int i = 1; i < 8; i++){
            System.out.printf(" %d \t", i);
        }

        System.out.print("\n");

        // Represent number of facedown cards on console display
        int faceDownCardCount;
        // Find maximum visible cards among piles. Needed for printing
        int maxVisibleCardCount = 0;

        // Represent number of faced down cards in pile
        for(Pile pile: randomPiles){
            faceDownCardCount = pile.getPileSize() - pile.getVisibleCardCount();
            System.out.printf("P %d\t", faceDownCardCount);

            maxVisibleCardCount = Math.max(maxVisibleCardCount, pile.getVisibleCardCount());
        }

        System.out.print("\n");

        // Loop through piles and take out cards according to index to print
        for(int i = 0; i < maxVisibleCardCount; i++){
            for(int j = 0; j < 7; j++){
                Pile pile = randomPiles.get(j);
                if(pile.getVisibleCardCount() > i){
                    Card card = pile.getVisibleCard(i); 
                    rankSymbol = rankMap.getSymbol(card.getRank());
                    suitSymbol = suitMap.getSymbol(card.getSuit());
                    System.out.print(rankSymbol + ' ');
                    System.out.print(suitSymbol);
                    System.out.print('\t');
                }
                else{
                    System.out.print("   \t");
                }
            }
            System.out.print('\n');
        }

        // Print information about deck
        System.out.printf("\n\nP: \tRemaining cards: %d\n", deckCardCount);
        ArrayList<Card> cards = drawnPile.getVisibleCards();

        for(Card card: cards){
            rankSymbol = rankMap.getSymbol(card.getRank());
            suitSymbol = suitMap.getSymbol(card.getSuit());
            System.out.print(rankSymbol + ' ');
            System.out.print(suitSymbol);
            System.out.print('\n');
        }
    }

    public void printInvalidCommand(){
        System.out.print("That is not a valid command.\n");
    }

    public void printInvalidMove(){
        System.out.print("Suggested move is not allowed.\n");
    }
}