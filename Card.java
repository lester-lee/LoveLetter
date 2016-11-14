/*
 * This code is available under the CC BY-SA 4.0 License
 * (Creative Commons Attribution-ShareAlike 4.0 License)
 * More information can be found at:
 * https://creativecommons.org/licenses/by-sa/4.0/
 */
package loveletter;

/**
 *
 * @author Lester Lee
 * A Card simply holds the LoveLetter rank (1-8)
 * and an information String describing the card.
 */
public class Card {    
    
    int type;
    String info;
    
    public Card(int t){
        type = t;
        switch (t){
            case 1:
                info = "(5) Guess a player's hand";
                break;
            case 2:
                info = "(2) Look at a hand";
                break;
            case 3:
                info = "(2) Compare hands; lower hand is out";
                break;
            case 4:
                info = "(2) Protection until next turn";
                break;
            case 5:
                info = "(2) One player discards hand";
                break;
            case 6:
                info = "(1) Trade hands";
                break;
            case 7:
                info = "(1) Discard if caught w/ King or Prince";
                break;
            case 8:
                info = "(1) Lose if discarded";
                break;
        }
    }    
    
    public String toString(){
        switch(type){
            case 1:
                return "GUARD";
            case 2:
                return "PRIEST";
            case 3:
                return "BARON";
            case 4:
                return "HANDMAIDEN";
            case 5:
                return "PRINCE";
            case 6:
                return "KING";
            case 7:
                return "COUNTESS";
            case 8:
                return "PRINCESS";
            default:
                return "ERROR";
        }
    }
}
