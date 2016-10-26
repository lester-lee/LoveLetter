/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loveletter;

/**
 *
 * @author Lesterr
 */
public class Card {    
    
    // card types
    public static final int PRINCESS = 8;
    public static final int COUNTESS = 7;
    public static final int KING = 6;
    public static final int PRINCE = 5;
    public static final int HANDMAIDEN = 4;
    public static final int BARON = 3;
    public static final int PRIEST = 2;
    public static final int GUARD = 1;
    
    private int type;
    private String info;
    
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
    
    public int getType(){
        return type;
    }
    
    public String info(){
        return info;
    }
    
    public String toString(){
        switch(type){
            case 1:
                return "GUARD";
            case 2:
                return "BARON";
            case 3:
                return "PRIEST";
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
