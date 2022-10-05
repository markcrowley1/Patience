
package console.datamaps;

import java.util.HashMap;
import game.cards.enums.*;

public class RankMap{
    private HashMap<Ranks, String> map;

    public RankMap(){
        this.map = new HashMap<Ranks, String>();

        map.put(Ranks.ACE, "A");
        map.put(Ranks.TWO, "2");
        map.put(Ranks.THREE, "3");
        map.put(Ranks.FOUR, "4");
        map.put(Ranks.FIVE, "5");
        map.put(Ranks.SIX, "6");
        map.put(Ranks.SEVEN, "7");
        map.put(Ranks.EIGHT, "8");
        map.put(Ranks.NINE, "9");
        map.put(Ranks.TEN, "10");
        map.put(Ranks.JACK, "J");
        map.put(Ranks.QUEEN, "Q");
        map.put(Ranks.KING, "K");
    }

    public String getSymbol(Ranks rank){
        String symbol = map.get(rank);
        return symbol;
    }
}