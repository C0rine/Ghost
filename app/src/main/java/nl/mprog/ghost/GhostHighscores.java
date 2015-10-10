// Corine Jacobs
// 10001326
// Corine_J@MSN.com
// Minor Programmeren 2015/2016 - Universiteit van Amsterdam

package nl.mprog.ghost;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;


public class GhostHighscores extends BaseActivity {

    public Highscores highscore;

    private Player winningplayer;
    private Player losingplayer;

    public ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost_highscores);

        // open extras from intent
        Bundle recvIntent = getIntent().getExtras();
        // retrieve winner
        Gson gson1 = new Gson();
        String jsonwin = recvIntent.getString("WINNER");
        // retreive loser
        Gson gson2 = new Gson();
        String jsonlose = recvIntent.getString("LOSER");

        winningplayer = gson1.fromJson(jsonwin, Player.class);
        losingplayer = gson2.fromJson(jsonlose, Player.class);

        Toast.makeText(this, losingplayer.getName(), Toast.LENGTH_LONG).show();

        String sPHighscores = getPreferences(Context.MODE_PRIVATE).getString("HIGHS", "EMPTY");

        // check if there is data in savedInstanceState
        if (savedInstanceState != null){
            // there is something saved in savedInstanceState
            // load this in
            Gson gsonis = new Gson();
            String json = savedInstanceState.getString("HIGH", "");
            highscore = gsonis.fromJson(json, Highscores.class);

            /* add new players to see if this works..
            player5 = new Player("Thomas", 75);
            highscore.insertScore(player5); */

            Toast.makeText(this, "savesInstanceState", Toast.LENGTH_LONG).show();

        }
        // check if there is anything stored in sharedpreference
        else if(!sPHighscores.equals("EMPTY")){

            // there were shared preferences
            // load this in
            Gson gsonsp = new Gson();
            String json = sPHighscores;
            highscore = gsonsp.fromJson(json, Highscores.class);

            Toast.makeText(this, "Shared Preferences + Corine", Toast.LENGTH_LONG).show();

        }
        // if there was nothing saved, a new instance of highscores needs to be made
        else {
            // there were no sharedprefs.
            highscore = new Highscores();

            Toast.makeText(this, "None", Toast.LENGTH_LONG).show();
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

        Gson gson = new Gson();
        String jsonhighscore = gson.toJson(highscore);
        outState.putString("HIGH", jsonhighscore);

        super.onSaveInstanceState(outState);
    }

    // saves data when user kills application
    private void saveHighscores(){

        SharedPreferences.Editor sPEditor = getPreferences(Context.MODE_PRIVATE).edit();

        Gson gson = new Gson();
        String jsonhighscore = gson.toJson(highscore);
        sPEditor.putString("HIGHS", jsonhighscore);

        sPEditor.commit();

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

        // gets inherited from baseactivity

        return super.onOptionsItemSelected(item);
    }



    // start new game in new activity starting again with user input
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

        Intent backToHome = new Intent(this, GhostMainActivity.class);

        startActivityForResult(backToHome, 1);
    }
}
