package com.example.inalivayko.testall.cashe;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.inalivayko.testall.R;

public class ActivityCashe extends AppCompatActivity {

    private ListView lv;
    private CasheCursorAdapter listAdapter;

//    private Cursor cursor;
//    private SQLiteDatabase db;
//    private CasheCursorAdapter listAdapter;

    private final String[] queryFields = new String[] {
            CasheDatabaseHelper.TablePurchases.COLUMN_ID.name,
            CasheDatabaseHelper.TablePurchases.COLUMN_DATE.name,
            CasheDatabaseHelper.TablePurchases.COLUMN_NOMENCLATURE.name,
            CasheDatabaseHelper.TablePurchases.COLUMN_QUANTITY.name,
            CasheDatabaseHelper.TablePurchases.COLUMN_PRICE.name,
            CasheDatabaseHelper.TablePurchases.COLUMN_AMOUNT.name};
    private final String queryOrder = CasheDatabaseHelper.TablePurchases.COLUMN_DATE.name+" DESC, "+CasheDatabaseHelper.TablePurchases.COLUMN_ID.name+" ASC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashe);

        lv = (ListView) findViewById(R.id.listView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                        long id) {
                    Intent intent = new Intent(ActivityCashe.this, ActivityPurchase.class);
                    intent.putExtra(ActivityPurchase.EXTRA_PURCHASE_ID, id);
                    startActivity(intent);
                }
        });

//        CasheDatabaseHelper dbh = new CasheDatabaseHelper(this);
//        db = dbh.getWritableDatabase();
//        cursor = db.query(CasheDatabaseHelper.TablePurchases.TABLE_NAME,
//                queryFields, null, null, null, null, queryOrder);
//        listAdapter = new CasheCursorAdapter(this, cursor, 1);
//        lv.setAdapter(listAdapter);
        new SetListViewAdapter().execute();
    }

    public void onRestart() {
        super.onRestart();

//        Cursor newCursor = db.query(CasheDatabaseHelper.TablePurchases.TABLE_NAME,
//                queryFields, null, null, null, null, queryOrder);
//        listAdapter.changeCursor(newCursor);
//        cursor = newCursor;
        new SetListViewAdapter().execute();
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

//        cursor.close();
//        db.close();
    }

    private class SetListViewAdapter extends AsyncTask<Void, Void, Cursor> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Cursor doInBackground(Void... values) {

            CasheDatabaseHelper dbh = new CasheDatabaseHelper(ActivityCashe.this);
            SQLiteDatabase db = dbh.getWritableDatabase();

            //db = asDbh[0].getWritableDatabase();

            Cursor newCursor = db.query(CasheDatabaseHelper.TablePurchases.TABLE_NAME,
                    queryFields, null, null, null, null, queryOrder);
            return newCursor;
        }

        protected void onProgressUpdate(Void... values) {
            //Код, передающий информацию о ходе выполнения задачи
        }

        protected void onPostExecute(Cursor newCursor) {
            super.onPostExecute(newCursor);

            //cursor = newCursor;
            listAdapter = new CasheCursorAdapter(ActivityCashe.this, newCursor, 1);
            lv.setAdapter(listAdapter);
        }
    }
}
