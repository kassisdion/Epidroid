package com.pony.epidroid.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class SimpleDialogFragment extends DialogFragment {
    private static String positivBtnTxt = "oui";
    private static String negativBtnTxt = "non";
    private static String boxName;

    private static String message;
    private static PositivBtnListener positivBtnListener = null;
    private static NegativBtnListener negativBtnListener = null;

    public static void setNegativBtnTxt(String negativBtnTxt) {
        SimpleDialogFragment.negativBtnTxt = negativBtnTxt;
    }

    public static void setBoxName(String boxName) {
        SimpleDialogFragment.boxName = boxName;
    }

    public static void setPositivBtnTxt(String positivBtnTxt) {
        SimpleDialogFragment.positivBtnTxt = positivBtnTxt;
    }

    public static void setPositivBtnListener(PositivBtnListener positivBtnListener) {
        SimpleDialogFragment.positivBtnListener = positivBtnListener;
    }

    public static void setNegativBtnListener(NegativBtnListener negativBtnListener) {
        SimpleDialogFragment.negativBtnListener = negativBtnListener;
    }

    public static void setMessage(String message) {
        SimpleDialogFragment.message = message;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(boxName)
                .setMessage(message)
                .setPositiveButton(positivBtnTxt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (positivBtnListener != null) {
                            positivBtnListener.onResponse();
                        }
                    }
                })
                .setNegativeButton(negativBtnTxt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (negativBtnListener != null) {
                            negativBtnListener.onResponse();
                        }
                    }
                });
        return builder.create();
    }

    public interface PositivBtnListener {
        void onResponse();
    }

    public interface NegativBtnListener {
        void onResponse();
    }

}