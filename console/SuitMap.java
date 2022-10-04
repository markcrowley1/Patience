
package console;

import java.util.HashMap;
import game.enums.*;

public class SuitMap{
    private HashMap<Suits, Character> map;

    public SuitMap(){
        this.map = new HashMap<Suits, Character>();

        map.put(Suits.HEART, '\u2665');
        map.put(Suits.DIAMOND, '\u2666');
        map.put(Suits.CLUB, '\u2663');
        map.put(Suits.SPADE, '\u2660');
    }

    public char getSymbol(Suits suit){
        char symbol = map.get(suit);
        return symbol;
    }
}