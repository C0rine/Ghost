// Corine Jacobs
// 10001326
// Corine_J@MSN.com
// Minor Programmeren 2015/2016 - Universiteit van Amsterdam

package nl.mprog.ghost;

import java.util.HashMap;

public class Highscores {

    // key = player, value = score
    public HashMap<String, Integer> highscoremap;

    // constructor
    public Highscores{

        highscoremap = new HashMap<>();

    }


    // saves player with score in highscores
    public void insertScore(Player player){

        String name = player.getName();
        Integer points = player.getPoints();

        highscoremap.put(name, points);

        saveHighScores();

    }


    // retrieves highscores
    public HashMap getHighScores(){

        return highscoremap;

    }


    // save highscores to local memory
    public void saveHighScores(){

    }

}
