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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

        // setTimeInMillis
        // getTimeInMillis

        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month, day);
//        gregorianCalendar.setLenient(false);
//        gregorianCalendar.set(Calendar.YEAR, year);
//        gregorianCalendar.set(Calendar.MONTH, month);
//        gregorianCalendar.set(Calendar.DATE, day);
//        gregorianCalendar.set(Calendar.HOUR, 0);
//        gregorianCalendar.set(Calendar.MINUTE, 0);
//        gregorianCalendar.set(Calendar.SECOND, 0);
//        gregorianCalendar.set(Calendar.MILLISECOND, 0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        String result = dateFormat.format(gregorianCalendar.getTimeInMillis());

        TextView tv = (TextView) getActivity().findViewById(R.id.tvResult);
        tv.setText(result);
    }
}
