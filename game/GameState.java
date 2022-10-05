
package game;

import java.util.Collections;
import java.util.ArrayList;
import game.cards.Card;
import game.cards.Deck;
import game.cards.Pile;
import game.cards.enums.*;

public class GameState{
    // Data structures for cards
    private Deck deck;
    private ArrayList<Pile> foundationPiles;
    private ArrayList<Pile> randomPiles;
    private Pile drawnPile;

    // Data for game progression
    private int movesMade = 0;
    private int score = 0;
    private boolean gameOver = false;

    public GameState(){
        // Create and shuffle deck of cards
        deck = new Deck();
        deck.populateDeck();
        deck.shuffleDeck();

        // Arraylists are created for random piles,
        // foundation piles and a pile is created to represent drawn cards.
        foundationPiles = new ArrayList<Pile>();
        randomPiles = new ArrayList<Pile>();
        drawnPile = new Pile();

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
    }

    public void drawCard(){
        Card card = deck.drawCard();
        drawnPile.addVisibleCard(card);
    }

    public Pile getPileFromLabel(char label){
        // If label is a digit the pile is 
        // part of the card lanes
        // Otherwise it is a foundation pile.
        Pile pile;
        if(Character.isDigit(label)){
            int index = label - '0' - 1;
            pile = randomPiles.get(index);
        }
        else if(label == 'p'){
            pile = drawnPile;
        }
        else{
            CharToSuit map = new CharToSuit();
            Suits suit = map.getSuit(label);
            int index = suit.ordinal();

            pile = foundationPiles.get(index);
        }

        return pile;
    }

    public int isValidMove(char labelA, char labelB, Pile pileA, Pile pileB){
        int validMove = -1;

        // Invalid move if origin pile is empty
        if(pileA.getVisibleCardCount() == 0){
            return validMove;
        }

        // Cannot move cards already placed in foundation piles
        if(pileA.isFoundationPile() == true){
            return validMove;
        }

        // Cannot move card to pile of cards drawn from deck
        if(labelB == 'p'){
            return validMove;
        }

        // Unique rules apply if destination piles are empty
        if(pileB.getPileSize() == 0){
            Card card = pileA.getTopCard();
            Ranks rank = card.getRank();
            Suits suit = card.getSuit();
            if(pileB.isFoundationPile() == true){
                
                // Card placed on empty foundation pile must be ace of matching suit
                if(suit == pileB.getRequiredSuit() && rank == Ranks.ACE){
                    validMove = pileA.getVisibleCardCount() - 1;
                }
            }
            else if(rank == Ranks.KING){
                // Move all visible cards if moving from one lane to another
                validMove = 0;
            }

            return validMove;
        }

        // Get information of card on top of pile B (destination)
        Card cardB = pileB.getTopCard();
        Colors colorB = cardB.getColor();
        Ranks rankB = cardB.getRank();
        Suits suitB = cardB.getSuit();

        // Get information on cards from pile A (origin)
        Ranks rankA;
        Colors colorA;
        Suits suitA;

        // Only valid to move top card from uncovered pile
        if(labelA == 'p'){
            Card cardA = pileA.getTopCard();
            colorA = cardA.getColor();
            rankA = cardA.getRank();

            if(pileB.isFoundationPile() == true){
                suitA = pileB.getRequiredSuit();
                if(suitA == suitB && rankA.ordinal() - rankB.ordinal() == 1){
                    validMove = pileA.getVisibleCardCount() - 1;
                }
            }
            else if(colorA != colorB && rankB.ordinal() - rankA.ordinal() == 1){
                validMove = pileA.getVisibleCardCount() - 1;
            }

            return validMove;
        }

        int i = 0;
        // Loop through visible cards
        // Break if valid move found
        for(Card card: pileA.getVisibleCards()){
            rankA = card.getRank();
            colorA = card.getColor();

            if(pileB.isFoundationPile() == true){
                suitA = pileB.getRequiredSuit();
                if(suitA == suitB && rankA.ordinal() - rankB.ordinal() == 1){
                    validMove = i;
                }
            }
            else if(colorA != colorB && rankB.ordinal() - rankA.ordinal() == 1){
                validMove = i;
            }

            // Increment
            i++;
        }

        return validMove;
    }

    public int makeMove(Pile pileA, Pile pileB, int index){
        int visibleCardCount = pileA.getVisibleCardCount();
        ArrayList<Card> tempArrayList = new ArrayList<Card>();
    
        // Remove cards from pile A
        for(int i = visibleCardCount - 1; i > index - 1; i--){
            Card card = pileA.removeCard();
            tempArrayList.add(card);
        }

        // Reverse order of cards only if moving between lanes
        if(pileB.isFoundationPile() != true){
            Collections.reverse(tempArrayList);
        }

        // Find the number of cards to be moved
        int cardCount = tempArrayList.size();

        // Add cards to pile B
        for(Card card: tempArrayList){
            pileB.addVisibleCard(card);
        }

        // Turn up next card in pile if no visible cards are left
        if(index == 0){
            pileA.flipCard();
        }
        
        return cardCount;
    }

    public boolean isGameWon(){
        boolean gameWon = false;

        int foundationPileCardCount = 0;

        for(Pile pile: foundationPiles){
            foundationPileCardCount = foundationPileCardCount + pile.getPileSize();
        }

        if(foundationPileCardCount == 52){
            gameWon = true;
        }

        return gameWon;
    }

    public ArrayList<Pile> getFoundationPiles(){
        return this.foundationPiles;
    }

    public ArrayList<Pile> getRandomPiles(){
        return this.randomPiles;
    }

    public Pile getDrawnPile(){
        return this.drawnPile;
    }

    public Deck getDeck(){
        return deck;
    }

    public int getMovesMade(){
        return this.movesMade;
    }

    public int getScore(){
        return this.score;
    }

    public boolean isGameOver(){
        return this.gameOver;
    }

    public void incrementMovesMade(){
        this.movesMade++;
    }

    public void incrementScore(char labelA, char labelB, int cardCount){
        int increment;
        int value;

        // Moving cards between lanes
        if(Character.isDigit(labelA) && Character.isDigit(labelB)){
            value = 5;
        }
        // Moving card from uncovered pile to card lane
        else if(labelA == 'q' && Character.isDigit(labelB)){
            value = 0;
        }
        // Moving card from uncovered pile to foundation pile
        else if(labelA == 'q'){
            value = 10;
        }
        // Moving card from card lanes to foundation pile
        else{
            value = 20;
        }

        increment = value * cardCount;
        this.score = score + increment;
    }

    public void setGameOver(boolean bool){
        this.gameOver = bool;
    }
}