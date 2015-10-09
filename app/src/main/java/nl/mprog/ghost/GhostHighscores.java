// Corine Jacobs
// 10001326
// Corine_J@MSN.com
// Minor Programmeren 2015/2016 - Universiteit van Amsterdam


package nl.mprog.ghost;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class GhostHighscores extends BaseActivity {

    public Highscores highscore;
    public Player player1;
    public Player player2;
    public Player player3;
    public Player player4;
    public Player player5;

    public ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost_highscores);

        // code below is just to try

        player1 = new Player("B", 10);
        player2 = new Player("A", 1);
        player3 = new Player("C", 100);
        player4 = new Player("Corine", 55);
        player5 = new Player("Thomas", 75);

        highscore = new Highscores();

        highscore.insertScore(player1);
        highscore.insertScore(player2);
        highscore.insertScore(player3);
        highscore.insertScore(player4);
        highscore.insertScore(player5);


        // get the listview
        lv = (ListView) findViewById(R.id.highscores_listView);

        // create adapter for listview
        ListAdapter theAdapter = new HighscoresAdapter(highscore.getSortedMap());

        // set adapter on listview
        lv.setAdapter(theAdapter);

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
