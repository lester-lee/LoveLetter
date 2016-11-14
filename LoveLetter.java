/*
 * This code is available under the CC BY-SA 4.0 License
 * (Creative Commons Attribution-ShareAlike 4.0 License)
 * More information can be found at:
 * https://creativecommons.org/licenses/by-sa/4.0/
 */
package loveletter;

import java.util.ArrayList;

/**
 *
 * @author Lester Lee
 * LoveLetter will automatically play a number of games.
 */
public class LoveLetter {

    public static void pr(Object line) {
        System.out.println(line);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            Table t = new Table("s s");
            while (!t.gameOver) {
                t.takeTurn();
            }
            t.endGame();
        }
    }

}
