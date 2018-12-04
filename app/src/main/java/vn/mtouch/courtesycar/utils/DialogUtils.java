package vn.mtouch.courtesycar.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Copyright (C) 2017, VNG Corporation.
 *
 * @author thuannv
 * @since 09/08/2017
 */

public final class DialogUtils {

    private DialogUtils() {
    }

    public static Dialog showProgressDialog(Context context, CharSequence text) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage(text);
        dialog.show();
        return dialog;
    }

    public interface OnDateTimeChange {
        void onDateTimeChange(long time);
    }

    public static void showDateTimePicker(final Context context, final OnDateTimeChange listener) {
        final Calendar currentDate = Calendar.getInstance();
        final Calendar returnDateCalendar = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if(!view.isShown()) {
                    return;
                }
                returnDateCalendar.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        returnDateCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        returnDateCalendar.set(Calendar.MINUTE, minute);
                        listener.onDateTimeChange(returnDateCalendar.getTimeInMillis());
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    public static Dialog showConfirmDeleteConversationDialog(@NonNull final Context context,
                                                             final DialogInterface.OnClickListener onPositiveClick,
                                                             final DialogInterface.OnClickListener onNegativeClick) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);

        builder.setMessage("Xóa mặt hàng đã kiểm kê này ?")
                .setCancelable(true)
                .setPositiveButton("Ok", onPositiveClick)
                .setNegativeButton("Đóng", onNegativeClick)
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        if (onNegativeClick != null) {
                            onNegativeClick.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                    }
                });

        android.support.v7.app.AlertDialog dialog = builder.create();
        dialog.show();

        return dialog;
    }

    public interface OnDialogAlertListener {
        void onClickOk();
    }
    public static Dialog showDialogAlert(@NonNull final Context context, String message, OnDialogAlertListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setTitle("Thông báo")
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(listener != null) {
                            listener.onClickOk();
                        }
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();

        return dialog;
    }


    public static Dialog showDialogConfirm(@NonNull final Context context,
                                           String message,
                                           final DialogInterface.OnClickListener onPositiveClick,
                                           final DialogInterface.OnClickListener onNegativeClick) {
        String title = "Thông báo";
        String positiveButtonText = "Ok";
        String negativeButtonText = "Đóng";

        return showDialogConfirm(context, title, message, positiveButtonText, negativeButtonText, onPositiveClick, onNegativeClick);
    }

    public static Dialog showDialogConfirm(@NonNull final Context context,
                                           final String title,
                                           final String message,
                                           final String positiveButtonText,
                                           final String negativeButtonText,
                                           final DialogInterface.OnClickListener onPositiveClick,
                                           final DialogInterface.OnClickListener onNegativeClick) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }

        if (!TextUtils.isEmpty(message)) {
            builder.setMessage(message);
        }

        builder.setCancelable(true)
                .setPositiveButton(positiveButtonText,
                        (dialog, which) -> {
                            if (onPositiveClick != null) {
                                onPositiveClick.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                            }
                        })
                .setNegativeButton(negativeButtonText,
                        (dialog, which) -> {
                            dialog.dismiss();
                            if (onNegativeClick != null) {
                                onNegativeClick.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                            }
                        });

        AlertDialog dialog = builder.create();
        dialog.show();

        return dialog;
    }

    public static Dialog showDialogOnlyPositive(Context context, String title, String message, boolean touchOutSide, final DialogInterface.OnClickListener listener) {
        if (context == null)
            return null;
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(touchOutSide)
                .setPositiveButton("context.getText(R.string.dialog_positive_button)", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (listener != null) {
                            listener.onClick(dialog, id);
                        }
                        dialog.dismiss();
                    }
                }).show();
    }

    public static void dismiss(Dialog dialog) {
        if (isDialogShowing(dialog)) {
            dialog.dismiss();
        }
    }

    public static boolean isDialogShowing(Dialog dialog) {
        return dialog != null && dialog.isShowing();
    }

    public static boolean isDialogFragmentShowing(Fragment dialogFragment) {
        return dialogFragment != null && dialogFragment.isAdded();
    }

    public static void dismiss(FragmentActivity activity, DialogFragment dialogFragment) {
        if (activity == null || dialogFragment == null) {
            return;
        }

        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        if (fragmentManager == null) {
            return;
        }

        if (!fragmentManager.isStateSaved() && isDialogFragmentShowing(dialogFragment)) {
            dialogFragment.dismiss();
        }
    }

    public static void showDialogFragment(FragmentActivity activity, DialogFragment dialogFragment, String tag) {
        if (activity == null || dialogFragment == null) {
            return;
        }

        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        if (fragmentManager != null && !fragmentManager.isStateSaved()) {
            dialogFragment.show(fragmentManager, tag);
        }
    }
}
