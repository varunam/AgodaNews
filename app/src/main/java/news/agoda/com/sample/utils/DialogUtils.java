package news.agoda.com.sample.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by varun.am on 2019-07-18
 */
public class DialogUtils {
    
    public static AlertDialog createDialog(Activity activity,
                                           String title,
                                           String message,
                                           String positiveButtonText,
                                           String negativeButtonText,
                                           int iconResId,
                                           final int requestCode,
                                           final DialogButtonListener dialogButtonListener){
        return new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogButtonListener.onDialogPositiveButtonClicked(requestCode);
                    }
                })
                .setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogButtonListener.onDialogNegativeButtonClicked(requestCode);
                    }
                })
                .setCancelable(false)
                .setIcon(iconResId)
                .create();
    }
}
