package game.cards;

import java.util.ArrayList;
import java.util.Collections;

import game.cards.enums.*;

public class Deck{
    private ArrayList<Card> cards;

    public Deck(){
        cards = new ArrayList<Card>();
    }

    public void populateDeck(){
        // Get all possible ranks and suits
        Ranks ranks[] = Ranks.values();
        Suits suits[] = Suits.values();
        // Loop through suits and ranks and add each card to deck
        for(Suits suit: suits){
            for(Ranks rank: ranks){
                Card card = new Card(rank, suit);
                cards.add(card);
            }
        }
    }

    public void shuffleDeck(){
        Collections.shuffle(cards);
    }

    public Card drawCard(){
        Card card = cards.remove(cards.size() - 1);
        return card;
    }

    public int getDeckSize(){
        return cards.size();
    }
}