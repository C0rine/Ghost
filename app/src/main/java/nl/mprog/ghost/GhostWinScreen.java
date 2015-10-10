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
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;


public class GhostWinScreen extends BaseActivity {

    private TextView winnertext;

    private Player winningplayer;
    private Player losingplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost_win_screen);

        winnertext = (TextView) findViewById(R.id.playerwin_text);

        // open extras from intent
        Bundle recvIntent = getIntent().getExtras();
        // retrieve winner
        Gson gson1 = new Gson();
        String jsonwin = recvIntent.getString("WINNER");
        // retreive loser
        Gson gson2 = new Gson();
        String jsonlose = recvIntent.getString("LOSER");

        // convert Json back to objects
        winningplayer = gson1.fromJson(jsonwin, Player.class);
        losingplayer = gson2.fromJson(jsonlose, Player.class);

        Toast.makeText(this, "winner: " + winningplayer.getName(), Toast.LENGTH_LONG).show();
        Toast.makeText(this, "loser: " + losingplayer.getName(), Toast.LENGTH_LONG).show();


        // display the name of the winner
        winnertext.setText(winningplayer.getName() + " wins!");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost_win_screen, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // Gets inherited from BaseActivity

        return super.onOptionsItemSelected(item);
    }


    // after finishing playing a game, open highscores in new activity
    public void continueToHighscore(View view) {

        Intent continueToHighscores = new Intent (this, GhostHighscores.class);

        // send both Player objects on to the highscore activity
        Gson gson1 = new Gson();
        String jsonplayer1 = gson1.toJson(losingplayer);
        continueToHighscores.putExtra("LOSER", jsonplayer1);

        Gson gson2 = new Gson();
        String jsonplayer2 = gson2.toJson(winningplayer);
        continueToHighscores.putExtra("WINNER", jsonplayer2);

        // add a sign telling GhostHighscores.java that we open the highscores
        // wanting to add a new player
        continueToHighscores.putExtra("SIGN", 2);

        startActivityForResult(continueToHighscores, 1);

    }


    // Restrict users from using back button. We always want them to go to the highscores first
    // and not let them go back into the gamemode or bypass having their score inserted into the
    // highscoreslist.
    public void onBackPressed(){

        Toast.makeText(this, R.string.noreturnallowed_toasttext,
                Toast.LENGTH_LONG).show();

    }
}
