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

    private Cursor cursor;
    private SQLiteDatabase db;

    private static final String[] queryFields = new String[] {
            CasheDatabaseHelper.TablePurchases.COLUMN_ID.name,
            CasheDatabaseHelper.TablePurchases.COLUMN_DATE.name,
            CasheDatabaseHelper.TablePurchases.COLUMN_NOMENCLATURE.name,
            CasheDatabaseHelper.TablePurchases.COLUMN_QUANTITY.name,
            CasheDatabaseHelper.TablePurchases.COLUMN_PRICE.name,
            CasheDatabaseHelper.TablePurchases.COLUMN_AMOUNT.name};
    private static final String queryOrder = CasheDatabaseHelper.TablePurchases.COLUMN_DATE.name+" DESC, "+CasheDatabaseHelper.TablePurchases.COLUMN_ID.name+" ASC";

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

        new SetListViewAdapter().execute();
    }

    public void onRestart() {
        super.onRestart();

        new ChangeAdapterCursor().execute(db);
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

    private class SetListViewAdapter extends AsyncTask<Void, Void, AsyncTaskRezult> {

        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected AsyncTaskRezult doInBackground(Void... values) {

            CasheDatabaseHelper dbh = new CasheDatabaseHelper(ActivityCashe.this);
            SQLiteDatabase db = dbh.getWritableDatabase();

            Cursor newCursor = db.query(CasheDatabaseHelper.TablePurchases.TABLE_NAME,
                    ActivityCashe.queryFields, null, null, null, null, ActivityCashe.queryOrder);

            AsyncTaskRezult rezult = new AsyncTaskRezult(db, newCursor);

            return rezult;
        }

        protected void onProgressUpdate(Void... values) {
            //Код, передающий информацию о ходе выполнения задачи
        }

        protected void onPostExecute(AsyncTaskRezult rezult) {
            super.onPostExecute(rezult);

            db = rezult.getDatabase();
            cursor = rezult.getCursor();

            listAdapter = new CasheCursorAdapter(ActivityCashe.this, cursor, 1);
            lv.setAdapter(listAdapter);
        }
    }

    private class ChangeAdapterCursor extends AsyncTask<SQLiteDatabase, Void, Cursor> {

        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected Cursor doInBackground(SQLiteDatabase... dbParam) {

            Cursor newCursor = dbParam[0].query(CasheDatabaseHelper.TablePurchases.TABLE_NAME,
                    ActivityCashe.queryFields, null, null, null, null, ActivityCashe.queryOrder);

            return newCursor;
        }

        protected void onProgressUpdate(Void... values) {
            //Код, передающий информацию о ходе выполнения задачи
        }

        protected void onPostExecute(Cursor newCursor) {
            super.onPostExecute(newCursor);

            listAdapter.changeCursor(newCursor);
        }
    }

    class AsyncTaskRezult {

        private SQLiteDatabase database;
        private Cursor cursor;

        AsyncTaskRezult(SQLiteDatabase db, Cursor c) {
            this.database = db;
            this.cursor = c;
        }

        public void setDatabase(SQLiteDatabase db) {
            this.database = db;
        }

        public SQLiteDatabase getDatabase() {
            return  database;
        }

        public void setCursor(Cursor c) {
            this.cursor = c;
        }

        public Cursor getCursor() {
            return  cursor;
        }
    }
}
