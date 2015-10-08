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
    public Highscores(){

        highscoremap = new HashMap<>();

    }


    // saves player with score in highscores
    public void insertScore(Player player){

        String name = player.getName();
        Integer points = player.getPoints();

        // put method of hashmap will create new key-value pair if key does not yet exist
        if (highscoremap.get(name) != null){
            // name is already in map
            // 'put' will overwrite value if key is already present so this automatically
            // prevents duplicates
            highscoremap.put(name, highscoremap.get(name) + points);
        }
        else{
            // name is not yet in map
            highscoremap.put(name, points);
        }

        saveHighScores();

    }


    // retrieves highscores
    public HashMap getHighScores(){

        return highscoremap;

    }


    // save highscores to local memory
    public void saveHighScores(){

    }

    // clears the hashmap with the highscores
    public void clearHighScores(){

        highscoremap.clear();

    }

}
