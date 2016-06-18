package com.example.inalivayko.testall.examples_library.date_calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

public class DialogFragmentSelectDate extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public static final String BUNDLE_YEAR = "bundle_year";
    public static final String BUNDLE_MONTH = "bundle_month";
    public static final String BUNDLE_DAY = "bundle_day";

    public interface onDateSetFromDialogFragmentListener {
        void dateSelectedFromDialogFragmentListener(int year, int month, int day);
    };

    private onDateSetFromDialogFragmentListener listener;

    public static DialogFragmentSelectDate newInstance(int year, int month, int day) {

        DialogFragmentSelectDate dialogFragmentSelectDate = new DialogFragmentSelectDate();

        Bundle args = new Bundle();
        args.putInt(BUNDLE_YEAR, year);
        args.putInt(BUNDLE_MONTH, month);
        args.putInt(BUNDLE_DAY, day);
        dialogFragmentSelectDate.setArguments(args);

        return dialogFragmentSelectDate;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        int year;
        int month;
        int day;

        if (getArguments() != null) {
            year = getArguments().getInt(BUNDLE_YEAR);
            month = getArguments().getInt(BUNDLE_MONTH);
            day = getArguments().getInt(BUNDLE_DAY);
        }
        else {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
        }

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listener = (onDateSetFromDialogFragmentListener) activity;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        if (listener != null) {
            listener.dateSelectedFromDialogFragmentListener(year, month, day);
        }
    }
}
