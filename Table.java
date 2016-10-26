/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loveletter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import static loveletter.LoveLetter.pr;

/**
 *
 * @author Lesterr
 */
public class Table {
    private Deck deck;
    private ArrayList<Player> players;
    public int curPlayer;
    public boolean gameOver;
        
    public Table(Deck d, int numPlayers){
        deck = d;
        players = new ArrayList<Player>(numPlayers);
        curPlayer = 0;
        gameOver = false;
        for (int i = 0; i < numPlayers; i++){
            players.add(new Player(i, deck.draw()));
        }
    }
    
    public void takeTurn(){   
        pr(this);
        Player p = players.get(curPlayer);
        p.draw(deck.draw());
        pr("Which card would Player #" + (curPlayer + 1) + " like to play? Your options are:");
        Card c = askPlayer(p);
        
        //takeAction(playedCard);
       curPlayer++;
       if (curPlayer >= players.size()) curPlayer = 0;
    }
    
    private Card askPlayer(Player p){
        p.printOptions();
        Scanner scan = new Scanner(System.in);    
        int action = scan.nextInt();
        // action should be either 0 (hand) or 1 (drawn card)
        while (action < 0 || action > 2){
            p.printOptions();
            action = scan.nextInt();
        }
        Card playedCard = p.play(action);
        if (playedCard == null) return askPlayer(p);   // player asked for info
        return playedCard;
    }
    
    public String toString(){
        String res = "Table: Currently Player #" + (curPlayer + 1) + "'s turn \n";
        for (Player p : players){
            res += p + "\n";
        }
        return res;
    }
}
