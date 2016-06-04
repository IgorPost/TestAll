package com.example.inalivayko.testall.crm;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.inalivayko.testall.R;

public class ActivityCRM extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crm);
    }

    public void onClickReadContdcts(View view){

        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

        ContentResolver contentResolver = getContentResolver();

        Cursor cursor = contentResolver.query(CONTENT_URI, null,null, null, null);

        if (cursor.getCount() > 0){

            cursor.moveToNext();
            String contact_id = cursor.getString(cursor.getColumnIndex( _ID ));
            String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));

            String phoneNumber = "";

            int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));
            if (hasPhoneNumber > 0){
                Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[] {contact_id}, null);
                if (phoneCursor.getCount() > 0){
                    phoneCursor.moveToNext();
                    phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                }
            }
            Toast.makeText(this, "ID: "+contact_id+" "+name+" ph.: "+phoneNumber, Toast.LENGTH_LONG).show();
        }
    }
}
