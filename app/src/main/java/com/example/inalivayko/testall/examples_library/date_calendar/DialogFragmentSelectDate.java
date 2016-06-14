package com.example.inalivayko.testall.examples_library.date_calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

public class DialogFragmentSelectDate extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public interface onDateSetFromDialogFragmentListener {
        void dateSelectedFromDialogFragmentListener(int year, int month, int day);
    };

    private onDateSetFromDialogFragmentListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

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