package com.example.inalivayko.testall.cashe;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.inalivayko.testall.R;

public class ActivityCashe extends AppCompatActivity {

    //private ListView lv;
    private Cursor cursor;
    private SQLiteDatabase db;
    private CasheCursorAdapter listAdapter;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashe);

        lv = (ListView) findViewById(R.id.listView);
        //if (lv != null) {
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                        long id) {
                    Intent intent = new Intent(ActivityCashe.this, ActivityPurchase.class);
                    intent.putExtra(ActivityPurchase.EXTRA_PURCHASE_ID, id);
                    startActivity(intent);
                }
            });
        //}

        CasheDatabaseHelper dbh = new CasheDatabaseHelper(this);
        db = dbh.getWritableDatabase();
        cursor = db.query(CasheDatabaseHelper.TablePurchases.TABLE_NAME,
                new String[] {CasheDatabaseHelper.TablePurchases.COLUMN_ID.name,
                        CasheDatabaseHelper.TablePurchases.COLUMN_DATE.name,
                        CasheDatabaseHelper.TablePurchases.COLUMN_NOMENCLATURE.name,
                        CasheDatabaseHelper.TablePurchases.COLUMN_QUANTITY.name,
                        CasheDatabaseHelper.TablePurchases.COLUMN_PRICE.name,
                        CasheDatabaseHelper.TablePurchases.COLUMN_AMOUNT.name},
                null, null, null, null,
                CasheDatabaseHelper.TablePurchases.COLUMN_DATE.name+" ASC");
        CasheCursorAdapter listAdapter = new CasheCursorAdapter(this, cursor, 1);
        lv.setAdapter(listAdapter);
    }

    public void onRestart() {
        super.onRestart();

        Cursor newCursor = db.query(CasheDatabaseHelper.TablePurchases.TABLE_NAME,
                new String[] {CasheDatabaseHelper.TablePurchases.COLUMN_ID.name,
                        CasheDatabaseHelper.TablePurchases.COLUMN_DATE.name,
                        CasheDatabaseHelper.TablePurchases.COLUMN_NOMENCLATURE.name,
                        CasheDatabaseHelper.TablePurchases.COLUMN_QUANTITY.name,
                        CasheDatabaseHelper.TablePurchases.COLUMN_PRICE.name,
                        CasheDatabaseHelper.TablePurchases.COLUMN_AMOUNT.name},
                null, null, null, null, null);
        CasheCursorAdapter la = (CasheCursorAdapter) lv.getAdapter();
        la.changeCursor(newCursor);
        cursor = newCursor;
    }

    public void onClickNew(View view) {

    }

    public void onClickEdit(View view) {
//        Boolean result = CasheDatabaseHelper.recreateTable(this);
//        Toast.makeText(this, String.valueOf(result), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        cursor.close();
        db.close();
    }

}
