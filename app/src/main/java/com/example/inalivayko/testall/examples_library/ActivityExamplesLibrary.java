package com.example.inalivayko.testall.examples_library;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.inalivayko.testall.R;
import com.example.inalivayko.testall.examples_library.database.ActivityDatabase;
import com.example.inalivayko.testall.examples_library.date_calendar.ActivityDateCalendar;
import com.example.inalivayko.testall.examples_library.some_test.ActivitySomeTest;

public class ActivityExamplesLibrary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examples_library);


    }

    public void onClickDateCalendar(View view){
        Intent intent = new Intent(this, ActivityDateCalendar.class);
        startActivity(intent);
    }

    public void onClickDatabase(View view){
        Intent intent = new Intent(this, ActivityDatabase.class);
        startActivity(intent);
    }

    public void onClickSomeTest(View view){
        Intent intent = new Intent(this, ActivitySomeTest.class);
        startActivity(intent);
    }
}
