// Corine Jacobs
// 10001326
// Corine_J@MSN.com
// Minor Programmeren 2015/2016 - Universiteit van Amsterdam

package nl.mprog.ghost;

public class Player {

    String name;
    //String avatar;
    Integer points;

    public Player(String plname, Integer score){

        name = plname;
        points = score;

    }

    public void setName(String inputname){

        name = inputname;

    }

    public String getName(){

        return name;

    }

    public void setPoints(Integer score){

        points = score;

    }

    public Integer getPoints(){

        return points;

    }



}
