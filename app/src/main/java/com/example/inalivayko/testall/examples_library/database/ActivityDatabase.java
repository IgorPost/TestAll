package com.example.inalivayko.testall.examples_library.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.inalivayko.testall.R;
import com.example.inalivayko.testall.cashe.CasheDatabaseHelper;

import java.util.GregorianCalendar;

public class ActivityDatabase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
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

        GregorianCalendar gregorianCalendar = new GregorianCalendar(2016, 6, 15);
        long date = gregorianCalendar.getTimeInMillis();

        insertPurshase(db, date, "Банаы", 1, 2855, 2855);
        insertPurshase(db, date, "Яблоки", 2, 1433, 2866);
        insertPurshase(db, date, "Морковь", 1, 555, 555);
        insertPurshase(db, date, "Картофель", 4, 641, 2564);
        insertPurshase(db, date, "Капуста", 1, 742, 742);
        insertPurshase(db, date, "Селедка", 1, 8100, 8100);
        insertPurshase(db, date, "Скумбрия", 1, 12000, 12000);
        insertPurshase(db, date, "Курица целая", 1, 9000, 9000);
        insertPurshase(db, date, "Утка целая", 1, 16000, 16000);
        insertPurshase(db, date, "Говядина", 1, 8500, 8500);
        insertPurshase(db, date, "Свинина", 1, 7800, 7800);
        insertPurshase(db, date, "Филе индюшки", 1, 7500, 7500);
        insertPurshase(db, date, "Бедро индюшки", 1, 7400, 7400);
        insertPurshase(db, date, "Филе курицы", 1, 6900, 6900);
        insertPurshase(db, date, "Печень телячья", 1, 6700, 6700);

        //db.insert(CasheDatabaseHelper.DatabaseTablePurchases.TABLE_NAME, null,)
        db.close();
        Toast toast = Toast.makeText(this, "Добавлены строки втаблицу \"PURSHASES\"", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onClickDelRecords(View view){
        SQLiteOpenHelper dbh = new CasheDatabaseHelper(this);
        SQLiteDatabase db = dbh.getReadableDatabase();
        db.delete(CasheDatabaseHelper.TablePurchases.TABLE_NAME, null, null);
        db.close();
    }

    public void onClickCreateTable(View view) {
        CasheDatabaseHelper.createTable(this, CasheDatabaseHelper.TablePurchases.CREATING_STRING);
    }

    public void onClickDeleteTable(View view) {
        Boolean rez = CasheDatabaseHelper.deleteTable(this, CasheDatabaseHelper.TablePurchases.TABLE_NAME);
        Toast.makeText(this, String.valueOf(rez), Toast.LENGTH_SHORT).show();
    }

    public void onClickRenameTable(View view) {
        Boolean rez = CasheDatabaseHelper.renameTable(this, "PURSHASES", CasheDatabaseHelper.TablePurchases.TABLE_NAME);
        Toast.makeText(this, String.valueOf(rez), Toast.LENGTH_SHORT).show();
    }

    public void onClickAddTableColumn(View view) {
        Boolean rez = CasheDatabaseHelper.addTableColumn(this, CasheDatabaseHelper.TablePurchases.TABLE_NAME, CasheDatabaseHelper.TablePurchases.COLUMN_DATE.name, CasheDatabaseHelper.TablePurchases.COLUMN_DATE.type);
        Toast.makeText(this, String.valueOf(rez), Toast.LENGTH_SHORT).show();
    }

    public void onClickDeleteTableColumn(View view) {

    }

    public void onClickRenameTableColumn(View view) {

    }

    private static void insertPurshase(SQLiteDatabase db, long date,String nomenclature,
                                       int number, int price, int amount) {
        ContentValues insertValues = new ContentValues();
        insertValues.put(CasheDatabaseHelper.TablePurchases.COLUMN_DATE.name, date);
        insertValues.put(CasheDatabaseHelper.TablePurchases.COLUMN_NOMENCLATURE.name, nomenclature);
        insertValues.put(CasheDatabaseHelper.TablePurchases.COLUMN_QUANTITY.name, number);
        insertValues.put(CasheDatabaseHelper.TablePurchases.COLUMN_PRICE.name, price);
        insertValues.put(CasheDatabaseHelper.TablePurchases.COLUMN_AMOUNT.name, amount);
        db.insert(CasheDatabaseHelper.TablePurchases.TABLE_NAME, null, insertValues);
    }
}
