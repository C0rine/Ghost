// Corine Jacobs
// 10001326
// Corine_J@MSN.com
// Minor Programmeren 2015/2016 - Universiteit van Amsterdam

package nl.mprog.ghost;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ExitGameWarningDialog extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder exitWarningDialog = new AlertDialog.Builder(getActivity());

        // warn the user about the consequences of using the back button whilst in the InGame activity
        exitWarningDialog.setTitle(R.string.dialog_areyousure_titletext);
        exitWarningDialog.setMessage(R.string.dialogmessage_gamewillbelost_text);

        // upon confirming the user wants the exit the game, go back the userinput activity
        exitWarningDialog.setPositiveButton(R.string.positivedialog_buttontext, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent exitGame = new Intent(getActivity(), GhostPlayerInput.class);

                startActivityForResult(exitGame, 1);
            }
        });

        // upon cancelling going back, only notify the user of this choice with a toast
        exitWarningDialog.setNegativeButton(R.string.cancelbutton_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), R.string.cancelled_text, Toast.LENGTH_SHORT).show();
            }
        });

        return exitWarningDialog.create();
    }
}
