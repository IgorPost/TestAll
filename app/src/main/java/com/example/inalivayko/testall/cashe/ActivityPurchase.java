package com.example.inalivayko.testall.cashe;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.inalivayko.testall.R;
import com.example.inalivayko.testall.examples_library.date_calendar.DialogFragmentSelectDate;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class ActivityPurchase extends AppCompatActivity implements Purchase.intPurchase, DialogFragmentSelectDate.onDateSetFromDialogFragmentListener {

    public static final String EXTRA_PURCHASE_ID = "purchaseID";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy"); // "dd.MM.yyyy HH:mm:ss"
    private static final String LOG_TAG = "ActivityPurchase";

    private Purchase purchase;

    private TextView tvID;
    private TextView tvDate;

    private EditText etName;
    private EditText etQuantity;
    private EditText etPrice;
    private EditText etAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purshase);

        initControls();

        long purchaseID = getIntent().getExtras().getLong(EXTRA_PURCHASE_ID);
        purchase = new Purchase(purchaseID, this);

        fillControls();
        addListeners();
    }

    public void onClickSave(View view){
        purchase.save();
    }

    public void onClickSetDate(View view) {

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(purchase.getDate());
        int y = gregorianCalendar.get(gregorianCalendar.YEAR);
        int m = gregorianCalendar.get(gregorianCalendar.MONTH);
        int d = gregorianCalendar.get(gregorianCalendar.DAY_OF_MONTH);

        FragmentManager manager = getSupportFragmentManager();
        DialogFragmentSelectDate myDialogFragment = DialogFragmentSelectDate.newInstance(y, m, d);
        myDialogFragment.show(manager, "datePicker");
    }

    public void initControls(){
        // TextView
        tvID = (TextView) findViewById(R.id.tvID);
        tvDate = (TextView) findViewById(R.id.tvDate);
        // EditText
        etName = (EditText) findViewById(R.id.etName);
        etQuantity = (EditText) findViewById(R.id.etQuantity);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etAmount = (EditText) findViewById(R.id.etAmount);
    }

    public void fillControls(){
        // TextView
        tvID.setText("ID: "+String.valueOf(purchase.getID()));
        tvDate.setText(dateFormat.format(purchase.getDate()));
        // EditText
        etName.setText(purchase.getNomenclature());
        etQuantity.setText(String.valueOf(purchase.getQuantity()));
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
                //purchase.setNumber(Double.valueOf(charSequence.toString()));
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
                //purchase.setPrice(Long.valueOf(charSequence.toString()));
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
                //purchase.setAmount(Double.valueOf(charSequence.toString()));
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
//        Toast.makeText(getApplicationContext(),
//                "setAmount", Toast.LENGTH_LONG).show();
//        if (etAmount != null) {
//            etAmount.setText("777");
//            Toast.makeText(getApplicationContext(),
//                    "Cb: "+String.valueOf(purchase.getAmount()), Toast.LENGTH_LONG).show();
//        }
    }

    @Override
    public void dateSelectedFromDialogFragmentListener(int year, int month, int day) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month, day);
        purchase.setDate(gregorianCalendar.getTimeInMillis());
        tvDate.setText(dateFormat.format(purchase.getDate()));
    }
}
