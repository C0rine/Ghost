/* Corine Jacobs
   10001326
   Corine_J@MSN.com
   Minor Programmeren 2015/2016 - Universiteit van Amsterdam  */

/* Main activity. The activity that standardly gets opened first when the app is started */

package nl.mprog.ghost;

import android.content.Intent;
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

    // start GhostPlayerInput to start a new game upon clicked 'new game'-button
    public void newGameStart(View view) {

        Intent newGameStart = new Intent(this, GhostPlayerInput.class);
        startActivityForResult(newGameStart, 1);

    }
}
