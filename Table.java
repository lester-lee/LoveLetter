/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loveletter;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Lesterr
 */
public class Table {
    private Deck curdeck;
    private ArrayList<Player> players;
    
    public Table(Deck d, ArrayList<Player> p){
        curdeck = d;
        players = p;
        for (int i = 0; i < p.size(); i++){
            p.add(new Player(i,d.draw()));
        }
    }
    
    public String toString(){
        String res = "Table:\n";
        for (Iterator<Player> it = players.iterator(); it.hasNext();) {
            Player p = it.next();
            res += ""
        }
    }
}
