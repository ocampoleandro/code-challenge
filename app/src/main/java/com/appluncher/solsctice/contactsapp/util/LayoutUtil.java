package com.appluncher.solsctice.contactsapp.util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by leandro on 12/09/2015.
 */
public final class LayoutUtil {

    private static LayoutUtil layoutUtil;
    private ProgressDialog progress;

    public static LayoutUtil getInstance(){
        if(layoutUtil==null){
            layoutUtil = new LayoutUtil();
        }
        return layoutUtil;
    }

    public void blockScreen(Context context){
        progress = new ProgressDialog(context);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
    }

    public void unblockScreen(){
        progress.dismiss();
    }
}
