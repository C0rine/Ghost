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

public class GhostMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost_main, menu);
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

    // start a new game in a new activity (starting with userinput)
    public void newGameStart(View view) {

        Intent newGameStart = new Intent(this, GhostPlayerInput.class);

        startActivityForResult(newGameStart, 1);

    }

    // view highscores in a new activity
    public void viewHighscores(View view) {

        Intent viewHighscores = new Intent(this, GhostHighscores.class);

        startActivityForResult(viewHighscores, 1);
    }
}
