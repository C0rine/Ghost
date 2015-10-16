/* Corine Jacobs
   10001326
   Corine_J@MSN.com
   Minor Programmeren 2015/2016 - Universiteit van Amsterdam */


/* The highscores class creates a highscore, sorts it based in score and allows new scores to be added */

package nl.mprog.ghost;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Highscores {

    // key = player, value = his/her score
    public HashMap<String, String> highscoremap;
    public TreeMap<String, String> sortedMap;


    public Highscores(){

        highscoremap = new HashMap<>();

    }


    // saves player with score in highscores map
    public void insertScore(Player player){

        String name = player.getName();
        Integer points = player.getPoints();

        // put method of hashmap will create new key-value pair if key does not yet exist
        if (highscoremap.values().contains(name)){
            // name is already in map

            // 'put' will overwrite value if key is already present so this automatically
            // prevents duplicates
            highscoremap.put(name, Integer.toString((Integer.parseInt(highscoremap.get(name)) + points)));
        }
        else{
            // name is not yet in map
            highscoremap.put(name, Integer.toString(points));
        }
    }


    public HashMap getUnsortedMap(){

        return highscoremap;

    }


    // returns correctly ordered integer array of scores
    public Map getSortedMap(){

        sortHighscoresmap();
        return sortedMap;

    }


    // returns a map that is the original hashmap but sorted
    public void sortHighscoresmap(){

        sortedMap = SortByValue(highscoremap);

    }


    // sorts a hashmap by value to a treemap
    public static TreeMap<String, String> SortByValue(HashMap<String, String> map) {
        ValueComparator vc =  new ValueComparator(map);
        TreeMap<String,String> sortedMap = new TreeMap<String,String>(vc);
        sortedMap.putAll(map);
        return sortedMap;
    }


    // clears the hashmap with the highscores
    public void clearScores(){

        highscoremap.clear();
        sortedMap.clear();

    }

}

