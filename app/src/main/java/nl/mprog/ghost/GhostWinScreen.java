// Corine Jacobs
// 10001326
// Corine_J@MSN.com
// Minor Programmeren 2015/2016 - Universiteit van Amsterdam

package nl.mprog.ghost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class GhostWinScreen extends BaseActivity {

    private TextView winnertext;
    private String winnername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost_win_screen);

        winnertext = (TextView) findViewById(R.id.playerwin_text);

        // open extras from intent
        // retrieve winner
        Bundle recvIntent = getIntent().getExtras();
        winnername = recvIntent.getString("Winner");

        // display the name of the winner
        winnertext.setText(winnername + " wins!");


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
