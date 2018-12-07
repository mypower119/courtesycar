package vn.mtouch.courtesycar.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;

import vn.mtouch.courtesycar.R;

public class ProgressDialogCustom {

    private android.app.ProgressDialog dialogLoading;

    public ProgressDialogCustom(Context context) {
        dialogLoading = new android.app.ProgressDialog(context);
        dialogLoading.setCanceledOnTouchOutside(false);
        dialogLoading.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_BACK && !event.isCanceled()) {
                    return true;
                }
                return false;
            }
        });
        dialogLoading.setTitle(context.getResources().getString(R.string.in_progress));
    }

    public void show() {
        if(dialogLoading != null && !dialogLoading.isShowing()) {
            dialogLoading.show();
        }
    }

    public void dismiss(){
        try {
            if(dialogLoading != null && dialogLoading.isShowing()) {
                dialogLoading.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
