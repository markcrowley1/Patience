package game;

import java.util.HashMap;
import game.cards.enums.*;

public class CharToSuit{
    HashMap<Character, Suits> map;

    public CharToSuit(){
        map = new HashMap<Character, Suits>();

        map.put('h',Suits.HEART);
        map.put('d',Suits.DIAMOND);
        map.put('c',Suits.CLUB);
        map.put('s',Suits.SPADE);
    }

    public Suits getSuit(char character){
        Suits suit = map.get(character);
        return suit;
    }
}