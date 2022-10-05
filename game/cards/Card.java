
package game.cards;

import game.cards.enums.*;

public class Card{
    private Ranks rank;
    private Suits suit;
    private Colors color;

    public Card(Ranks rank, Suits suit){
        // Assign rank and suit to card object
        this.rank = rank;
        this.suit = suit;
        // Assign color based on suit (relevant in patience rules)
        if(suit == Suits.DIAMOND || suit == Suits.HEART){
            this.color = Colors.RED;
        }
        else{
            this.color = Colors.BLACK;
        }
    }

    public Ranks getRank(){
        return this.rank;
    }

    public Suits getSuit(){
        return this.suit;
    }

    public Colors getColor(){
        return this.color;
    }
}