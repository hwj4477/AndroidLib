package com.hwj4477.androidlib.views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * @author hwj4477
 * @since 15. 1. 12..
 *
 * ListAlertDialog
 */

public class ListAlertDialog extends AlertDialog {

    private Context context;

    private ListAlertDialogListener listener = null;

    private String strTitle;
    private ArrayList<String> arrayMenu;

    /**
     *
     * Constructor
     *
     * @param context
     * @param strTitle
     * @param arrayMenu
     *
     */
    public ListAlertDialog(Context context, String strTitle, ArrayList<String> arrayMenu) {
        super(context);

        this.context = context;

        this.strTitle = strTitle;
        this.arrayMenu = arrayMenu;
    }

    public void setListAlertDialogListener(ListAlertDialogListener listener)
    {
        this.listener = listener;
    }

    private AlertDialog.Builder buildListAlertDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(strTitle);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_selectable_list_item);

        for(String strMenu : arrayMenu)
        {
            arrayAdapter.add(strMenu);
        }

        builder.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.setAdapter(arrayAdapter,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(listener != null)
                            listener.onClickItem(which);
                    }
                });

        return builder;
    }

    @Override
    public void show() {

        try
        {
            AlertDialog.Builder builder = buildListAlertDialog();

            builder.show();
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }

    }

    public interface ListAlertDialogListener
    {
        public void onClickItem(int which);
    }
}
