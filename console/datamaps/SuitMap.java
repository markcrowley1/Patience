
package console.datamaps;

import java.util.HashMap;
import game.cards.enums.*;

public class SuitMap{
    private HashMap<Suits, Character> map;

    public SuitMap(){
        this.map = new HashMap<Suits, Character>();

        map.put(Suits.HEART, 'H');
        map.put(Suits.DIAMOND, 'D');
        map.put(Suits.CLUB, 'C');
        map.put(Suits.SPADE, 'S');
    }

    public char getSymbol(Suits suit){
        char symbol = map.get(suit);
        return symbol;
    }
}