package com.example.inalivayko.testall.examples_library.date_calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.inalivayko.testall.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityDateCalendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_date_calendar);


    }

    public void onClickTest(View view){
        // Создадим объект Date
        Date date = new Date();
        long millis = date.getTime();
        String toastMessage;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        //toastMessage = String.valueOf(millis);
        toastMessage = dateFormat.format(date);
        Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
    }
}
