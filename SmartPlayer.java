/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loveletter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Lesterr
 */
public class SmartPlayer extends Player {

    // keep count of cards & who has what
    private HashMap<Integer, Integer> cardCount;
    private HashMap<Integer, Integer> playerCards;
    private int playerGuess;
    private int guardGuess;

    public SmartPlayer(int i, Card c) {
        super(i, c);
        type = "smart";
        playerGuess = -1;
        guardGuess = -1;
        playerCards = new HashMap<Integer, Integer>();
    }

    private boolean hasCard(int c) {
        return hand.getType() == c || newCard.getType() == c;
    }

    public int whichPlayer(int numPlayers) {
        if (playerGuess == -1){
            Random r = new Random();
            return r.nextInt(numPlayers);
        }else{
            int temp = playerGuess;
            playerGuess = -1;
            return temp;
        }
    }

    public int guardGuess() {
        int temp = guardGuess;
        guardGuess = -1;
        return temp;
    }

    public void addInfo(Player p, Card c){
        playerCards.put(p.number-1, c.getType());
    }
    
    private int getHighestPlayer() {
        int max = Integer.MIN_VALUE;
        int pnum = -1;
        for (int p : playerCards.keySet()) {
            if (playerCards.get(p) > max) {
                max = playerCards.get(p);
                pnum = p;
            }
        }
        return pnum;
    }
    
    private int getLowestPlayer() {
        int min = Math.min(hand.getType(),newCard.getType());
        int pnum = -1;
        for (int p : playerCards.keySet()) {
            if (playerCards.get(p) < min) {
                min = playerCards.get(p);
                pnum = p;
            }
        }
        return pnum;
    }

    private void swapCards() {
        Card temp = hand;
        hand = newCard;
        newCard = temp;
    }

    private Card playCard(int c) {
        if (hand.getType() != c) {
            swapCards();
        }
        playedCards.add(hand);
        Card temp = hand;
        hand = newCard;
        lastPlayed = temp;
        return temp;
    }
    
    private int findChanceCard(){
        Random r = new Random();
        int counter = 0;
        ArrayList<Integer> al = new ArrayList<Integer>();
        for (int c : cardCount.keySet()){
            for (int i = 0; i < cardCount.get(c); i++){
                al.add(c);
                counter++;
            }
        }
        int rand = r.nextInt(counter);
        return al.get(rand);
    }
    
    private boolean hasPrincess(){
        for (int p : playerCards.keySet()){
            if (playerCards.get(p) == 8){
                playerGuess = p;
                return true;
            }
        }
        return false;
    }
    
    private int getUnknownPlayer(int max){
        ArrayList<Integer> present = new ArrayList<Integer>(playerCards.keySet());
        ArrayList<Integer> all = new ArrayList<Integer>();
        for (int i = 0; i < max; i++)
            all.add(i);
        all.removeAll(present);
        Random r = new Random();
        return all.get(r.nextInt(all.size()));
    }
    
    private void checkCards(Table t){
        ArrayList<Integer> toRemove = new ArrayList<Integer>();
        for (int p : playerCards.keySet()){
            if (t.players.get(p).lastPlayed.getType() == playerCards.get(p))
                toRemove.add(p);
        }
        for (int i : toRemove)
            playerCards.remove(i);
    }
    
    public Card play(int action, Table t) {
         // check for countess
        if (newCard != null) {
            if ((hand.getType() == 7 && (newCard.getType() == 5 || newCard.getType() == 6)) ||
                (newCard.getType() == 7 && (hand.getType() == 5 || hand.getType() == 6))) {
                return playCard(7);
            }
        }
        
        cardCount = t.cardCount;
        checkCards(t);
        if (!playerCards.isEmpty()) {
            playerGuess = getHighestPlayer();
            guardGuess = playerCards.get(playerGuess);
        }

        // PlayerGuess default highest player if info
        // GuardGuess default -1 unless info
        
        // Try to eliminate other players if possible
        // Options sorted by highest chance to eliminate other player
            
        // Prince someone with the princess
        if (hasCard(5) && hasPrincess()){
            return playCard(5);
        }    
        // Guard person for sure
        if (hasCard(1) && guardGuess != -1) {
            return playCard(1);
        }
        // Baron someone with lower card
        if (hasCard(3) && !playerCards.isEmpty()){
            playerGuess = getLowestPlayer();
            if (playerGuess != -1) return playCard(3);
        }
        // Prince person w/ highest card
        if (hasCard(5) && !playerCards.isEmpty()){
            return playCard(5);
        }
        // Guard someone based on probability
        if (hasCard(1) && guardGuess == -1){
            guardGuess = findChanceCard();
            return playCard(1);                    
        }
        
        // Get more information        
        if (hasCard(2)){
            playerGuess = getUnknownPlayer(t.numPlayers);   
            return playCard(2);
        }
        
        // Handmaid        
        if (hasCard(4))
            return playCard(4);
        
        // Trade w/ person w/ highest card
        if (hasCard(6) && !playerCards.isEmpty())
            return playCard(6);
        
        // If none of the options above have been taken,
        // then no information & make random but informed choice
        
        // if Princess, must not play
        if (hasCard(8)){
            if (hand.getType() != 8) swapCards();
            return playCard(newCard.getType());
        }
        
        // Only play Baron if chance of winning is likely
        if (hasCard(3)){
            if (hand.getType() != 3) swapCards();
            if (newCard.getType() >= 4)
                return playCard(newCard.getType());
        }
        
        // Play the lower card
        if (hand.getType() > newCard.getType()) swapCards();
        
        return playCard(hand.getType());
        
    }

}
