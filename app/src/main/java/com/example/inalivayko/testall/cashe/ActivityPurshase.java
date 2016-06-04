package com.example.inalivayko.testall.cashe;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inalivayko.testall.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ActivityPurshase extends AppCompatActivity implements Purchase.intPurchase {

    public static final String EXTRA_PURSHASE_ID = "purshaseID";

    private Purchase purshase;

    private TextView tvID;

    private EditText etDate;
    private EditText etName;
    private EditText etQuantity;
    private EditText etPrice;
    private EditText etAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purshase);

        long purshaseID = (long) getIntent().getExtras().getLong(EXTRA_PURSHASE_ID);

        purshase = new Purchase(purshaseID, this, this);

        initControls();
        fillControls();
        addListeners();
    }

    public void onClickSave(View view){
        purshase.save();
    }

    public void onClickGetDate(View view) {
        Calendar calendar = Calendar.getInstance();
        int year  = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day   = calendar.get(Calendar.DAY_OF_MONTH);
        StringBuilder sb = new StringBuilder();
        // Month is 0 based, just add 1
        sb.append(day).append("-");
        sb.append(month + 1).append("-");
        sb.append(year).append(" ");
        Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_SHORT).show();
    }

    public void initControls(){
        // TextView
        tvID = (TextView) findViewById(R.id.tvID);
        // EditText
        etDate = (EditText) findViewById(R.id.etDate);
        etName = (EditText) findViewById(R.id.etName);
        etQuantity = (EditText) findViewById(R.id.etQuantity);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etAmount = (EditText) findViewById(R.id.etAmount);
    }

    public void fillControls(){
        // TextView
        tvID.setText("ID: "+String.valueOf(purshase.getID()));
        // EditText
        etName.setText(purshase.getNomenclature());
        etQuantity.setText(String.valueOf(purshase.getNumber()));
        etPrice.setText(String.valueOf(purshase.getPrice()/100));
        etAmount.setText(String.valueOf(purshase.getAmount()/100));
    }

    public void addListeners(){

        etName.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                purshase.setNomenclature(charSequence.toString());
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        etQuantity.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                purshase.setNumber(Double.valueOf(charSequence.toString()));
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        etPrice.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                purshase.setPrice(Double.valueOf(charSequence.toString()));
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        etAmount.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                purshase.setAmount(Double.valueOf(charSequence.toString()));
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        etPrice.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    //Toast.makeText(getApplicationContext(), "etPrice got the focus", Toast.LENGTH_SHORT).show();
                }else {
                    //Toast.makeText(getApplicationContext(), "etPrice lost the focus", Toast.LENGTH_SHORT).show();
                    //purshase.setPrice(Double.valueOf(etPrice.getText().toString()));
                }
            }
        });
    }

    @Override
    public void setAmount() {
        if (etAmount != null) {
            etAmount.setText("777");
            Toast.makeText(getApplicationContext(),
                    "Cb: "+String.valueOf(purshase.getAmount()), Toast.LENGTH_LONG).show();
        }
    }

}
