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
public class Deck {

    // card types
    public static final int PRINCESS = 8;
    public static final int COUNTESS = 7;
    public static final int KING = 6;
    public static final int PRINCE = 5;
    public static final int HANDMAIDEN = 4;
    public static final int BARON = 3;
    public static final int PRIEST = 2;
    public static final int GUARD = 1;

    private Card[] deck;
    private int index = 1;  // skip first card

    public Deck() {
        deck = new Card[16];
        setUpDeck();
        shuffle();
    }

    private void setUpDeck(){
        for (int i = 0; i < 5; i++)
            deck[i] = new Card(GUARD);
        for (int i = 5; i < 7; i++)
            deck[i] = new Card(PRIEST);
        for (int i = 7; i < 9; i++)
            deck[i] = new Card(BARON);
        for (int i = 9; i < 11; i++)
            deck[i] = new Card(HANDMAIDEN);
        for (int i = 11; i < 13; i++)
            deck[i] = new Card(PRINCE);
        deck[13] = new Card(KING);
        deck[14] = new Card(COUNTESS);
        deck[15] = new Card(PRINCESS);
    }
    
    private void shuffle() {
        for (int i = deck.length - 1; i > 0; i--) {
            int j = (int) Math.floor(Math.random() * (i + 1));
            Card temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }
    }
     
    public Card draw(){
        Card res = (index == 16) ? null : deck[index++];
        return res;
    }
    
    public Card missingCard(){
        return deck[0];
    }
    
    public String toString(){
        String res = "";
        for (int i = 0; i < 16; i++){
            res += deck[i] + " ";
        }
        return res;
    }
}
