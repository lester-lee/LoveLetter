/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loveletter;

import java.util.ArrayList;

/**
 *
 * @author Lesterr
 */
public class Player {
    private Card hand;
    private Card newCard;
    private ArrayList<Card> playedCards;
    private int number;
    public static final int HAND = 0;
    public static final int NEWCARD = 1;
    public static final int GETINFO = 2;
    
    public Player(int i, Card c){
        hand = c;
        number = i+1;
        playedCards = new ArrayList<Card>();
    }
    
    public void draw(Card c){
        newCard = c;
    }
    
    public void printOptions(){
        System.out.println("(0) " + hand + " (1) " + newCard + " (2) Get info about cards");
    }
    
    public Card play(int action){
        switch(action){
            case HAND:
                playedCards.add(hand);
                Card temp = hand;
                hand = newCard;
                return temp;
            case NEWCARD:
                playedCards.add(newCard);
                return newCard;
            case GETINFO:
                System.out.println(hand + ": " + hand.info() + "\n" + newCard + ": " + newCard.info());
            default:
                return null;
        }
    }
    
    public String toString(){
        String res = "Player #" + number + ": ";
        for (Card c : playedCards){
            res += c + " ";
        }
        return res;
    }
    
}
