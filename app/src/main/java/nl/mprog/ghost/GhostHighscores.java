/* Corine Jacobs
   10001326
   Corine_J@MSN.com
   Minor Programmeren 2015/2016 - Universiteit van Amsterdam */


/* Highscores Activity */

package nl.mprog.ghost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;


public class GhostHighscores extends BaseActivity {

    private Highscores highscore;
    private Player winningplayer;
    private Player losingplayer;

    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost_highscores);

        // open extras from intent
        Bundle recvIntent = getIntent().getExtras();
        // retrieve winner
        Gson gson1 = new Gson();
        String jsonwin = recvIntent.getString("WINNER");
        // retrieve loser
        Gson gson2 = new Gson();
        String jsonlose = recvIntent.getString("LOSER");

        // convert from String back to object
        winningplayer = gson1.fromJson(jsonwin, Player.class);
        losingplayer = gson2.fromJson(jsonlose, Player.class);

        // get highscores from sharedpreferences
        SharedPreferences prefs = this.getSharedPreferences("settings", this.MODE_PRIVATE);
        String sPHighscores = prefs.getString("HIGH", "EMPTY");

        // check if there is data in savedInstanceState
        if (savedInstanceState != null){

            // use Gson to get highscores
            Gson gsonis = new Gson();
            String json = savedInstanceState.getString("HIGH", "");
            highscore = gsonis.fromJson(json, Highscores.class);

        }
        // check if there is a highscore in sharedpreferences
        else if(!sPHighscores.equals("EMPTY")){

            // use Gson to get highscores
            Gson gsonsp = new Gson();
            String json = sPHighscores;
            highscore = gsonsp.fromJson(json, Highscores.class);

        }
        // if there was nothing saved, a new instance of highscores needs to be made
        else {
            highscore = new Highscores();
        }

        // put the players in the highscore
        highscore.insertScore(winningplayer);
        highscore.insertScore(losingplayer);

        // get the listview
        lv = (ListView) findViewById(R.id.highscores_listView);
        // create adapter for listview
        ListAdapter theAdapter = new HighscoresAdapter(highscore.getSortedMap());
        // set adapter on listview
        lv.setAdapter(theAdapter);

    }

    // saves data when operating system tries to kill the app/activity
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        // save highscores in outState using Gson
        Gson gson = new Gson();
        String jsonhighscore = gson.toJson(highscore);
        outState.putString("HIGH", jsonhighscore);

        super.onSaveInstanceState(outState);
    }

    // saves data when user kills application
    private void saveHighscores(){

        SharedPreferences prefs = this.getSharedPreferences("settings", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // saving highscores in shared preferences using Gson

        Gson gson = new Gson();
        String jsonhighscore = gson.toJson(highscore);
        editor.putString("HIGH", jsonhighscore);

        editor.commit();

    }

    @Override
    protected void onStop() {

        saveHighscores();

        super.onStop();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost_highscores, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        // gets inherited from BaseActivity.java

        return super.onOptionsItemSelected(item);
    }



    // start new game in new activity
    public void newGameStart(View view) {

        Intent newGameStart = new Intent(this, GhostPlayerInput.class);

        startActivityForResult(newGameStart, 1);

    }


    // go back to MainActivty
    public void backToHome(View view) {

        Intent backToHome = new Intent(this, GhostMainActivity.class);

        startActivityForResult(backToHome, 1);
    }


    // when user presses android back button make game restart
    @Override
    public void onBackPressed() {

        saveHighscores();

        Intent backToHome = new Intent(this, GhostMainActivity.class);

        startActivityForResult(backToHome, 1);
    }
}