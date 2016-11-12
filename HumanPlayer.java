/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loveletter;

import java.util.ArrayList; 
import java.util.Scanner;
import static loveletter.LoveLetter.pr;
/**
 *
 * @author Lesterr
 */
public class HumanPlayer extends Player{
  
    public HumanPlayer(int i, Card c){
        super(i,c);
        type="human";
    }      
    
    public int whichPlayer(int numPlayers){
        pr("Which player # would you like to use your card on? Input their #:");
        Scanner scan = new Scanner(System.in);
        int res = scan.nextInt();
        while (res < 1 || res > numPlayers){
            pr("Invalid input. Please try again:");
            res = scan.nextInt();
        }
        return --res;
    }
    
    public int guardGuess(){
       pr("What card do you think they have? Input a # from 2-8:");
       pr("(2)PRIEST (3)BARON (4)HANDMAIDEN (5)PRINCE (6)KING (7)COUNTESS (8)PRINCESS");
       Scanner scan = new Scanner(System.in);
       int res = scan.nextInt();
       while (res < 2 || res > 8){
           pr("Invalid input. Please try again:");
           res = scan.nextInt();
       }
       return res;
    }
    
    public Card play(int action, Table t){
        // check for countess
        if ((hand.getType() == 7 && (newCard.getType() == 5 || newCard.getType() == 6)) ||
            (newCard.getType() == 7 && (hand.getType() == 5 || hand.getType() == 6)))
            action = hand.getType() == 7 ? HAND : NEWCARD;
        switch(action){
            case HAND:
                playedCards.add(hand);
                Card temp = hand;
                hand = newCard;
                lastPlayed = temp;
                return temp;
            case NEWCARD:
                playedCards.add(newCard);
                lastPlayed = newCard;
                return newCard;
            case GETINFO:
                System.out.println(hand + ": " + hand.info() + "\n" + newCard + ": " + newCard.info());
            default:
                return null;
        }
    }    
}
