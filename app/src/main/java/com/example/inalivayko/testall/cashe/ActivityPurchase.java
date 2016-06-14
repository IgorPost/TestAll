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

public class ActivityPurchase extends AppCompatActivity implements Purchase.intPurchase {

    public static final String EXTRA_PURCHASE_ID = "purchaseID";

    private Purchase purchase;

    private TextView tvID;

    private EditText etName;
    private EditText etQuantity;
    private EditText etPrice;
    private EditText etAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purshase);

        int purchaseID = (int) getIntent().getExtras().getInt(EXTRA_PURCHASE_ID);

        purchase = new Purchase(purchaseID, this, this);

        initControls();
        fillControls();
        addListeners();
    }

    public void onClickSave(View view){
        purchase.save();
    }

    public void onClickSetDate(View view) {
        Toast.makeText(getApplicationContext(), "onClickSetDate", Toast.LENGTH_SHORT).show();
    }

    public void initControls(){
        // TextView
        tvID = (TextView) findViewById(R.id.tvID);
        // EditText
        etName = (EditText) findViewById(R.id.etName);
        etQuantity = (EditText) findViewById(R.id.etQuantity);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etAmount = (EditText) findViewById(R.id.etAmount);
    }

    public void fillControls(){
        // TextView
        tvID.setText("ID: "+String.valueOf(purchase.getID()));
        // EditText
        etName.setText(purchase.getNomenclature());
        etQuantity.setText(String.valueOf(purchase.getNumber()));
        etPrice.setText(String.valueOf(purchase.getPrice()/100));
        etAmount.setText(String.valueOf(purchase.getAmount()/100));
    }

    public void addListeners(){

        etName.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                purchase.setNomenclature(charSequence.toString());
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
                purchase.setNumber(Double.valueOf(charSequence.toString()));
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
                purchase.setPrice(Double.valueOf(charSequence.toString()));
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
                purchase.setAmount(Double.valueOf(charSequence.toString()));
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
                    "Cb: "+String.valueOf(purchase.getAmount()), Toast.LENGTH_LONG).show();
        }
    }

}
