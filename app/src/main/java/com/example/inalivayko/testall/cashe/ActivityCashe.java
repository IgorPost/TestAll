package com.example.inalivayko.testall.cashe;

import android.content.ContentValues;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashe);

        ListView lv =  (ListView) findViewById(R.id.listView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                //Toast.makeText(getApplicationContext(), "Click ID: "+String.valueOf(id)+", pos.: "+String.valueOf(position),
                //        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ActivityCashe.this, ActivityPurchase.class);
                intent.putExtra(ActivityPurchase.EXTRA_PURSHASE_ID, (long)id);
                startActivity(intent);
            }
        });

        try {
//            SQLiteOpenHelper dbh = new CasheDatabaseHelper(this);
//            SQLiteDatabase db = dbh.getReadableDatabase();
//            Cursor cursor = db.query(CasheDatabaseHelper.TablePurchases.TABLE_NAME, new String[] {"_id", "NOMENCLATURE", "QUANTITY", "PRICE", "AMOUNT"}, null, null, null, null, null);
            //Cursor cursor = db.rawQuery("SELECT _id, NOMENCLATURE, NUMBER, PRICE/100 AS PRICE, AMOUNT/100 AS AMOUNT FROM PURSHASES", null);

//            CursorAdapter listAdapter = new SimpleCursorAdapter(this,
//                    R.layout.cashe_list_item,
//                    cursor,
//                    new String[]{"_id", "NOMENCLATURE", "NUMBER", "PRICE", "AMOUNT"},
//                    new int[]{R.id.tvID, R.id.tvNomenclature, R.id.tvNumber, R.id.tvPrice, R.id.tvAmount},
//                    0);

            Cursor cursor = Purchase.getList(this);
            CasheCursorAdapter listAdapter = new CasheCursorAdapter(this, cursor, 1);

            lv.setAdapter(listAdapter);

        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onClickReadRecords(View view){
        try {
            SQLiteOpenHelper dbh = new CasheDatabaseHelper(this);
            SQLiteDatabase db = dbh.getReadableDatabase();
            Cursor cursor = db.query(CasheDatabaseHelper.TablePurchases.TABLE_NAME, new String[] {"_id", "NOMENCLATURE", "QUANTITY", "PRICE", "AMOUNT"}, null, null, null, null, null);
            int recCount = cursor.getCount();
            if (recCount==0) {
                String message = "Нет строк в выборке!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(this, message, duration);
                toast.show();
            }
            else {
                String message = ""+recCount+" строк в выборке.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(this, message, duration);
                toast.show();
                //while (cursor.moveToNext()) {
                //}
            }
            cursor.close();
            db.close();
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onClickAddRecords(View view){
        SQLiteOpenHelper dbh = new CasheDatabaseHelper(this);
        SQLiteDatabase db = dbh.getReadableDatabase();
        insertPurshase(db, "Банаы", 1, 2855, 2855);
        insertPurshase(db, "Яблоки", 2, 1433, 2866);
        insertPurshase(db, "Морковь", 1, 555, 555);
        insertPurshase(db, "Картофель", 4, 641, 2564);
        insertPurshase(db, "Капуста", 1, 742, 742);
        //db.insert(CasheDatabaseHelper.DatabaseTablePurchases.TABLE_NAME, null,)
        db.close();
        Toast toast = Toast.makeText(this, "Добавлены строки втаблицу \"PURSHASES\"", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onClickDelRecords(View view){
        SQLiteOpenHelper dbh = new CasheDatabaseHelper(this);
        SQLiteDatabase db = dbh.getReadableDatabase();

        //db.delete("PURSHASES", "_id = ?", new String[] {"5"});
        db.delete(CasheDatabaseHelper.TablePurchases.TABLE_NAME, null, null);

        db.close();
    }

    public void onClickCreateTable(View view) {

    }

    public void onClickDeleteTable(View view) {

    }

    public void onClickRenameTable(View view) {
        Boolean rez = CasheDatabaseHelper.renameTable(this, "PURSHASES", CasheDatabaseHelper.TablePurchases.TABLE_NAME);
        Toast.makeText(ActivityCashe.this, String.valueOf(rez), Toast.LENGTH_SHORT).show();
    }

    public void onClickAddTableColumn(View view) {
        Boolean rez = CasheDatabaseHelper.addTableColumn(this, CasheDatabaseHelper.TablePurchases.TABLE_NAME, CasheDatabaseHelper.TablePurchases.COLUMN_DATE.name, CasheDatabaseHelper.TablePurchases.COLUMN_DATE.type);
        Toast.makeText(ActivityCashe.this, String.valueOf(rez), Toast.LENGTH_SHORT).show();
    }

    public void onClickDeleteTableColumn(View view) {

    }

    public void onClickRenameTableColumn(View view) {

    }

    private static void insertPurshase(SQLiteDatabase db, String nomenclature,
                                       int number, int price, int amount) {
        // "NOMENCLATURE", "NUMBER", "PRICE", "AMOUNT"
        ContentValues insertValues = new ContentValues();
        insertValues.put("NOMENCLATURE", nomenclature);
        insertValues.put("QUANTITY", number);
        insertValues.put("PRICE", price);
        insertValues.put("AMOUNT", amount);
        db.insert(CasheDatabaseHelper.TablePurchases.TABLE_NAME, null, insertValues);
    }
}
