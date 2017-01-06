/*
 * This code is available under the CC BY-SA 4.0 License
 * (Creative Commons Attribution-ShareAlike 4.0 License)
 * More information can be found at:
 * https://creativecommons.org/licenses/by-sa/4.0/
 */
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Lester Lee
 * LoveLetter will automatically play a number of games.
 */
public class LoveLetter {

public static void pr(Object line) {
        System.out.println(line);
}

public static void main(String[] args) {
        // command line argument expected in format: #games playertypes
        // ex: 10 h h for 10 games of HumanPlayer vs HumanPlayer
        int numGames = 1;
        String tablePlayers = "h s";
        ArrayList<String> players = new ArrayList<String>();
        String[] validPlayers = {"h","s","r"};
        if (args.length > 0) {
                numGames = Integer.parseInt(args[0]);
                for (int i=0; i<args.length; i++) {
                        if (Arrays.asList(validPlayers).indexOf(args[i])>-1) {
                                players.add(args[i]);
                        }
                }

                if (players.size()>=2) {
                        tablePlayers = "";
                        for (int i=0; i<players.size(); i++) {
                                tablePlayers += players.get(i) + " ";
                        }
                        tablePlayers = tablePlayers.substring(0,tablePlayers.length()-1);
                }else{
                        System.out.println("You must include at least 2 players!");
                        System.exit(0);
                }

        }

        for (int i = 0; i < numGames; i++) {
                Table t = new Table(tablePlayers);
                while (!t.gameOver) {
                        t.takeTurn();
                }
                t.endGame();
        }
}

}
