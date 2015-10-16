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

public class GhostMainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost_main);

        Toast.makeText(this, "nospinner", Toast.LENGTH_SHORT).show();
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

        // Gets inherited from BaseActivity

        return super.onOptionsItemSelected(item);
    }

    // start a new game in a new activity (starting with userinput)
    public void newGameStart(View view) {

        Intent newGameStart = new Intent(this, GhostPlayerInput.class);

        startActivityForResult(newGameStart, 1);

    }

    // view highscores in a new activity
    public void viewHighscores(View view) {

       /* Intent viewHighscores = new Intent(this, GhostHighscores.class);

        // add a sign telling GhostHighscores.java that we open the highscores without
        // wanting to add a new player
        viewHighscores.putExtra("SIGN", 1);

        startActivityForResult(viewHighscores, 1); */
    }
}
