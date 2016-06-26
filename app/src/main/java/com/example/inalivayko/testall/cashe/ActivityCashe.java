package com.example.inalivayko.testall.cashe;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inalivayko.testall.R;

public class ActivityCashe extends AppCompatActivity {

    private Spinner spOrderType;
    private ArrayAdapter<String> spOrderTypeAdapter;

    private TextView tvPeriod;

    private EditText etWhere;

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

    private static final String[] orderTypes = {"ASC", "DESC"};

    private int currentOrderType;

    private boolean clauseUsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashe);

        tvPeriod = (TextView) findViewById(R.id.tvPeriod);
        tvPeriod.setText("Period: ALL");

        clauseUsed = false;

        etWhere = (EditText) findViewById(R.id.etWhere);
        etWhere.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                // Прописываем то, что надо выполнить после изменения текста
                if (s.toString().length() > 2 || clauseUsed) {
                    //Toast.makeText(getBaseContext(), "AfterTextChanged: " + s.toString(), Toast.LENGTH_SHORT).show();
                    new SetListViewAdapter().execute();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

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

        // адаптер
        spOrderTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, orderTypes);
        spOrderTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spOrderType = (Spinner) findViewById(R.id.spOrderType);
        spOrderType.setAdapter(spOrderTypeAdapter);
        // заголовок
        spOrderType.setPrompt("Order");
        // выделяем элемент
        spOrderType.setSelection(0);
        // устанавливаем обработчик нажатия
        spOrderType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позицию нажатого элемента
                if (currentOrderType != position) {
                    currentOrderType = position;
                    //Toast.makeText(getBaseContext(), "Order: " + orderTypes[currentOrderType], Toast.LENGTH_SHORT).show();
                    new SetListViewAdapter().execute();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        new SetListViewAdapter().execute();
    }

    public void onRestart() {
        super.onRestart();

        new ChangeAdapterCursor().execute(db);
    }

    public void onClickSetPeriod(View view) {

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

        private String queryOrder;
        private String whereClause;

        protected void onPreExecute() {
            super.onPreExecute();

            queryOrder = CasheDatabaseHelper.TablePurchases.COLUMN_DATE.name+" "+orderTypes[currentOrderType]+", "+CasheDatabaseHelper.TablePurchases.COLUMN_ID.name+" ASC";
            if (etWhere.getText().length()<3) {
                whereClause = "";
            }
            else {
                whereClause = etWhere.getText().toString();
            }

        }

        protected AsyncTaskRezult doInBackground(Void... values) {

            CasheDatabaseHelper dbh = new CasheDatabaseHelper(ActivityCashe.this);
            SQLiteDatabase db = dbh.getWritableDatabase();

            Cursor newCursor;

            if (whereClause.isEmpty()) {
                newCursor = db.query(CasheDatabaseHelper.TablePurchases.TABLE_NAME,
                        ActivityCashe.queryFields, null, null, null, null, queryOrder);
                clauseUsed = false;
            }
            else {
                newCursor = db.query(CasheDatabaseHelper.TablePurchases.TABLE_NAME,
                        ActivityCashe.queryFields, CasheDatabaseHelper.TablePurchases.COLUMN_NOMENCLATURE.name+" LIKE ?", new String[] {"%"+whereClause+"%"}, null, null, queryOrder);
                clauseUsed = true;
            }

            //AsyncTaskRezult rezult = new AsyncTaskRezult(db, newCursor);

//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            return new AsyncTaskRezult(db, newCursor);
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

        private String queryOrder;

        protected void onPreExecute() {
            super.onPreExecute();

            queryOrder = CasheDatabaseHelper.TablePurchases.COLUMN_DATE.name+" "+orderTypes[currentOrderType]+", "+CasheDatabaseHelper.TablePurchases.COLUMN_ID.name+" ASC";
        }

        protected Cursor doInBackground(SQLiteDatabase... dbParam) {

            return dbParam[0].query(CasheDatabaseHelper.TablePurchases.TABLE_NAME,
                    ActivityCashe.queryFields, null, null, null, null, queryOrder);

//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            //return newCursor;
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
