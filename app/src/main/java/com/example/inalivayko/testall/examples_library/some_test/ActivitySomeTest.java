package com.example.inalivayko.testall.examples_library.some_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.inalivayko.testall.R;

public class ActivitySomeTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_some_test);
    }

    public void onClickSomeTest(View view){
        TextView tv = (TextView) findViewById(R.id.textView5);
        tv.setText("@@@");

        checkSymbolsInString("qweqweqwe(qweqweqweqweqweqwe)","(", ")");
    }

    public void checkSymbolsInString(String chStr, String symbol1, String symbol2) {
        // Варианта три:
        // 1. Сравнить количество двух элементов в строке. Если есть отличия, значит теги не закрываются;
        // 2. Не может быть подряд двух одинаковых тегов. После открывающего тега должен быть закрывающий. ()()()();
        // 3. Внешние теги должны закрываться. (((())))
        // 4. Комбинация случаев 2 и 3. (()((()())))()
        TextView tv = (TextView) findViewById(R.id.textView5);
        StringBuilder chStrSB = new StringBuilder(chStr);
        chStrSB.indexOf(symbol1, 0);
        tv.setText(String.valueOf(chStrSB.indexOf("V")));
    }
}
