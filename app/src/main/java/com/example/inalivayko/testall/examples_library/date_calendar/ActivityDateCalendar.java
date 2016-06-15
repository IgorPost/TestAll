package com.example.inalivayko.testall.examples_library.date_calendar;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.inalivayko.testall.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ActivityDateCalendar extends AppCompatActivity implements DialogFragmentSelectDate.onDateSetFromDialogFragmentListener{

    private TextView tv;
    long date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_date_calendar);

        tv = (TextView) findViewById(R.id.tvResult);
    }

    void setDateOnTextView() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String dateView = dateFormat.format(date);
        tv.setText(dateView+"\n"+String.valueOf(date));
    }

    public void onClickTest(View view){

        // НУЖНЫ СЛЕДУЮЩИЕ ПРИМЕРЫ:
        //
        // Date -> millis
        // millis -> Date
        //
        // Calendar -> millis
        // millis -> Calendar
        //
        // GregorianCalendar -> millis
        // millis -> GregorianCalendar

        Date date = new Date();
        StringBuffer testDate = new StringBuffer();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");

        addDate(date, testDate, dateFormat);
        addCalendar(date, testDate, dateFormat);
        addGregorianCalendar(date, testDate, dateFormat);

        tv.setText(testDate.toString());
    }

    private void addDate(Date date, StringBuffer testDate, SimpleDateFormat simpleDateFormat) {
        // Date -> millis
        // millis -> Date
        testDate.append("====================================").append("\n");
        testDate.append("Date:").append("\n");
        testDate.append("- millis: "+date.getTime()).append("\n");
        testDate.append("- millis-f: "+simpleDateFormat.format(date.getTime())).append("\n");
        testDate.append("- toString(): "+date.toString()).append("\n");
        testDate.append("\n");

        return ;
    }

    private void addCalendar(Date date, StringBuffer testDate, SimpleDateFormat simpleDateFormat) {
        Calendar calendar = Calendar.getInstance();
        testDate.append("====================================").append("\n");
        testDate.append("calendar:").append("\n");
        testDate.append("- millis: "+calendar.getTimeInMillis()).append("\n");
        calendar.set(Calendar.MILLISECOND, 999);
        testDate.append("- reset millis: "+calendar.getTimeInMillis()).append("\n");
        testDate.append("\n");
    }

    private void addGregorianCalendar(Date date, StringBuffer testDate, SimpleDateFormat simpleDateFormat) {
        // GregorianCalendar -> millis
        // millis -> GregorianCalendar

        GregorianCalendar gregCal = new GregorianCalendar();
        long millisL = gregCal.getTimeInMillis();
        int millisI = Integer.parseInt(String.valueOf(millisL/1000));

        testDate.append("====================================").append("\n");
        testDate.append("GregorianCalendar:").append("\n");
        testDate.append("- toString(): "+gregCal.toString()).append("\n");
        testDate.append("- millis long: "+String.valueOf(millisL)).append("\n");
        testDate.append("- millis int: "+String.valueOf(millisI)).append("\n");
        testDate.append("- millis long/1000: "+String.valueOf(millisL/1000)).append("\n");

        testDate.append("- today: ").append(simpleDateFormat.format(gregCal.getTimeInMillis())).append("\n");
        gregCal.add(Calendar.DAY_OF_MONTH, -1);
        testDate.append("- yesterday: ").append(simpleDateFormat.format(gregCal.getTimeInMillis())).append("\n");
    }

    public void onClickGetDateFromDialog(View view) {
        FragmentManager manager = getSupportFragmentManager();
        DialogFragmentSelectDate myDialogFragment = new DialogFragmentSelectDate();
        myDialogFragment.show(manager, "datePicker");
    }

    @Override
    public void dateSelectedFromDialogFragmentListener(int year, int month, int day) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month, day);
        date = gregorianCalendar.getTimeInMillis();
        setDateOnTextView();
    }
}
