/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author Lesterr
 */
public abstract class Player {
protected Card hand;
protected Card newCard;
public Card lastPlayed;
protected ArrayList<Card> playedCards;
public int number;
public String type;
public static final int HAND = 0;
public static final int NEWCARD = 1;
public static final int GETINFO = 2;

public Player(int i, Card c){
        hand = c;
        number = i+1;
        playedCards = new ArrayList<Card>();
        // effectively null cards prevents null exception errors
        newCard = new Card(10);
        lastPlayed = new Card(10);
}

public void draw(Card c){
        newCard = c;
}

public Card hand(){
        return hand;
}

public void setHand(Card c){
        hand = c;
}

public void discard(Card newCard){
        playedCards.add(hand);
        hand = newCard;
}

public void printOptions(){
        System.out.println("(0) " + hand + " (1) " + newCard + " (2) Get info about cards");
}

public abstract int whichPlayer(int numPlayers);

public abstract int guardGuess();

public abstract Card play(int action, Table t);

public String toString(){
        String res = "Player #" + number + ": ";
        for (Card c : playedCards) {
                res += c + " ";
        }
        return res;
}
}
