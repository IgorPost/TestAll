package com.example.inalivayko.testall.examples_library.date_calendar;

import android.app.DatePickerDialog;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inalivayko.testall.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ActivityDateCalendar extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_date_calendar);

        tv = (TextView) findViewById(R.id.tvResult);
    }

    public void onClickTest(View view){

        // НУЖНЫ СЛЕДУЮЩИЕ ПРИМЕРЫ:
        //
        // Date -> millis
        // millis -> Date
        //
        // GregorianCalendar -> millis
        // millis -> GregorianCalendar


        StringBuffer testDate = new StringBuffer();

        // Date -> millis
        // millis -> Date
        Date date = new Date();
        testDate.append("date - millis: "+date.getTime()).append("\n");
        testDate.append("date - toString(): "+date.toString()).append("\n");
        testDate.append("\n");

        // GregorianCalendar -> millis
        // millis -> GregorianCalendar

        GregorianCalendar gregCal = new GregorianCalendar();
        long millisL = gregCal.getTimeInMillis();
        int millisI = Integer.parseInt(String.valueOf(millisL/1000));
        testDate.append("gregCal - millis long: "+String.valueOf(millisL)).append("\n");
        testDate.append("gregCal - millis int: "+String.valueOf(millisI)).append("\n");
        testDate.append("gregCal - millis long/1000: "+String.valueOf(millisL/1000)).append("\n");

        testDate.append("\n");

        testDate.append("gregCal - today: ").append(String.valueOf(gregCal.get(Calendar.DAY_OF_MONTH))).append("\n");
        gregCal.add(Calendar.DAY_OF_MONTH, -1);
        testDate.append("gregCal - yesterday: ").append(String.valueOf(gregCal.get(Calendar.DAY_OF_MONTH))).append("\n");

        tv.setText(testDate.toString());
    }

    public void onClickGetDateFromDialog(View view) {
        FragmentManager manager = getSupportFragmentManager();
        DialigFragmentSelectDate myDialogFragment = new DialigFragmentSelectDate();
        myDialogFragment.show(manager, "datePicker");
    }

}
