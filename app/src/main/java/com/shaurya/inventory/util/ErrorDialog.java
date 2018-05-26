package com.shaurya.inventory.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by ishan.jaiswal on 5/23/2018.
 */

public class ErrorDialog {

    public static void createErrorDialog(Context context, String errorMessage){
        AlertDialog.Builder alertBox = new AlertDialog.Builder(context);
        alertBox.setTitle("Inventory");
        alertBox.setMessage(errorMessage);
        alertBox.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        alertBox.show();
    }
}
