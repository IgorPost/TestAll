package com.example.inalivayko.testall.examples_library.date_calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inalivayko.testall.R;

import java.util.Calendar;

public class DialigFragmentSelectDate extends DialogFragment implements DatePickerDialog.OnDateSetListener {

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

    public void onDateSet(DatePicker view, int year, int month, int day) {
        String result = String.valueOf(day)+"."+String.valueOf(month+1)+"."+String.valueOf(year);
        Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
        TextView tv = (TextView) getActivity().findViewById(R.id.tvResult);
        tv.setText(result);
    }
}
