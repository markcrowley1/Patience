package game.cards;

import java.util.ArrayList;

import game.cards.enums.*;

public class Pile{
    private ArrayList<Card> cards;
    private ArrayList<Card> visibleCards;
    private boolean foundationPile;
    private Suits requiredSuit;

    /*Constructor to create empty random pile */
    public Pile(){
        cards = new ArrayList<>();
        visibleCards = new ArrayList<>();
        foundationPile = false;
    }

    /*Constructor to create empty foundation pile*/
    public Pile(Suits suit){
        cards = new ArrayList<>();
        visibleCards = new ArrayList<>();
        foundationPile = true;
        requiredSuit = suit;
    }

    /*Add card faced down to a pile. */
    public void addCard(Card card){
        cards.add(card);
    }

    /*Add card face up to pile */
    public void addVisibleCard(Card card){
        cards.add(card);
        visibleCards.add(card);
    }

    /*Remove the top card of the pile. */
    public Card removeCard(){
        Card card = cards.remove(cards.size() - 1);
        visibleCards.remove(visibleCards.size() - 1);
        return card;
    }

    /*Turn up the card on top of the pile. */
    public void flipCard(){
        if(cards.size() > 0){
            visibleCards.add(cards.get(cards.size() - 1));
        }
    }

    /*Check if pile is foundation pile */
    public boolean isFoundationPile(){
        return foundationPile;
    }

    public Suits getRequiredSuit(){
        return requiredSuit;
    }

    public int getPileSize(){
        return cards.size();
    }

    public int getVisibleCardCount(){
        return visibleCards.size();
    }

    public ArrayList<Card> getVisibleCards(){
        return visibleCards;
    }

    public Card getVisibleCard(int index){
        return visibleCards.get(index);
    }

    public Card getTopCard(){
        return cards.get(cards.size() - 1);
    }
}