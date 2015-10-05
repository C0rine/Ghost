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
import android.widget.Toast;

public class GhostWinScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost_win_screen);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        // open settings
        if (id == R.id.settings) {
            Intent openSettings = new Intent(this, GhostSettings.class);
            startActivityForResult(openSettings, 1);
        }
        // close app
        else if (id == R.id.exit){
            System.exit(0);
            return true;
        }

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
