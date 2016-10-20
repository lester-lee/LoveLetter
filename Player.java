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
public class Player {
    private Card hand;
    private Card newCard;
    private int number;
    public static final int HAND = 0;
    public static final int NEWCARD = 1;
    
    public Player(int i, Card c){
        hand = c;
        number = i+1;
    }
    
    public void draw(Card c){
        newCard = c;
    }
    
    public Card play(int action){
        if (action == HAND){
            return hand;
        }else{
            hand = newCard;
            return newCard;
        }
    }
    
    public String toString(){
        
    }
    
}
