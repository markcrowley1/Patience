package console;

import java.util.ArrayList;

import game.Card;
import game.Pile;

import console.RankMap;
import console.SuitMap;

public class ConsoleDisplay{
    RankMap rankMap;
    SuitMap suitMap;

    public ConsoleDisplay(){
        rankMap = new RankMap();
        suitMap = new SuitMap();
    }

    public void displayTable(ArrayList<Pile> foundationPiles, ArrayList<Pile> randomPiles, Pile drawnPile, int movesMade, int score){
        char suitSymbol;
        String rankSymbol;

        System.out.print("\nFoundation Piles\n");

        for(Pile pile: foundationPiles){
            ArrayList<Card> cards = pile.getVisibleCards();
            for(Card card: cards){
                rankSymbol = rankMap.getSymbol(card.getRank());
                suitSymbol = suitMap.getSymbol(card.getSuit());
                System.out.print(suitSymbol);
                System.out.print(' ' + rankSymbol + '\t');
            }
        }

        System.out.print("\nRandom Piles\n");

        // Represent facedown cards
        int faceDownCardCount;

        for(Pile pile: randomPiles){
            faceDownCardCount = pile.getPileSize() - pile.getVisibleCardCount();
            System.out.printf("P %d\t", faceDownCardCount);
        }

        System.out.print("\n");

        for(Pile pile: randomPiles){
            ArrayList<Card> cards = pile.getVisibleCards();
            for(Card card: cards){
                rankSymbol = rankMap.getSymbol(card.getRank());
                suitSymbol = suitMap.getSymbol(card.getSuit());
                System.out.print(suitSymbol);
                System.out.print(' ' + rankSymbol + '\t');
            }
        }
    }
}