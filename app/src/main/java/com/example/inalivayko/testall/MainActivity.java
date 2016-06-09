package com.example.inalivayko.testall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.inalivayko.testall.cashe.ActivityCashe;
import com.example.inalivayko.testall.crm.ActivityCRM;
import com.example.inalivayko.testall.examples_library.ActivityExamplesLibrary;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickCRM(View view){
        Intent intent = new Intent(this, ActivityCRM.class);
        startActivity(intent);
    }

    public void onClickLib(View view){
        Intent intent = new Intent(this, ActivityExamplesLibrary.class);
        startActivity(intent);
    }

    public void onClickCashe(View view){
        // showToast("Нажата кнопка \"Cashe\"");
        Intent intent = new Intent(this, ActivityCashe.class);
        startActivity(intent);
    }
}
