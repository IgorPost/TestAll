package com.example.inalivayko.testall.examples_library;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.inalivayko.testall.R;
import com.example.inalivayko.testall.examples_library.database.ActivityDatabase;
import com.example.inalivayko.testall.examples_library.date_calendar.ActivityDateCalendar;
import com.example.inalivayko.testall.examples_library.sea_fight.ActivitySeaFight;
import com.example.inalivayko.testall.examples_library.some_test.ActivitySomeTest;

public class ActivityExamplesLibrary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examples_library);


    }

    public void onClickDateCalendar(View view){
        startActivity(ActivityDateCalendar.class);
    }

    public void onClickDatabase(View view){
        startActivity(ActivityDatabase.class);
    }

    public void onClickSomeTest(View view){
        startActivity(ActivitySomeTest.class);
    }
    public void onClickSeaFight(View view){
        startActivity(ActivitySeaFight.class);
    }

    private void startActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

}
