package com.example.inalivayko.testall.examples_library.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.inalivayko.testall.R;
import com.example.inalivayko.testall.cashe.CasheDatabaseHelper;

import java.util.Calendar;
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

        db.execSQL("DELETE FROM "+CasheDatabaseHelper.TablePurchases.TABLE_NAME+";");

        GregorianCalendar gregorianCalendar = new GregorianCalendar();

        insertPurshase(db, gregorianCalendar, 2007, 1, 4, "карандаши", 20000, 3900, 3900);
        insertPurshase(db, gregorianCalendar, 2007, 1, 4, "икра лососевая", 1000, 4000, 4000);
        insertPurshase(db, gregorianCalendar, 2007, 5, 5, "серьги", 4460, 64224, 64224);
        insertPurshase(db, gregorianCalendar, 2007, 6, 26, "доставка товара", 1000, 3999, 3999);
        insertPurshase(db, gregorianCalendar, 2007, 6, 26, "ноутбук", 1000, 377811, 377811);
        insertPurshase(db, gregorianCalendar, 2007, 7, 5, "мышь компьютерная", 1000, 11900, 11900);
        insertPurshase(db, gregorianCalendar, 2007, 9, 16, "продукты питания (одной суммой)", 0, 5190, 5190);
        insertPurshase(db, gregorianCalendar, 2007, 9, 25, "весы напольные", 1000, 43700, 43700);
        insertPurshase(db, gregorianCalendar, 2007, 10, 10, "туалетная вода", 50000, 28809, 28809);
        insertPurshase(db, gregorianCalendar, 2007, 10, 18, "другие лекарства", 0, 850, 850);
        insertPurshase(db, gregorianCalendar, 2007, 10, 18, "другие лекарства", 1000, 455, 455);
        insertPurshase(db, gregorianCalendar, 2007, 10, 20, "продукты питания (одной суммой)", 0, 10614, 10614);
        insertPurshase(db, gregorianCalendar, 2007, 10, 22, "бусы", 1000, 6500, 6500);
        insertPurshase(db, gregorianCalendar, 2007, 10, 22, "шапка", 1000, 8000, 8000);
        insertPurshase(db, gregorianCalendar, 2007, 10, 22, "кофта женская", 1000, 17000, 17000);
        insertPurshase(db, gregorianCalendar, 2007, 10, 22, "другие блюда", 0, 4142, 4142);
        insertPurshase(db, gregorianCalendar, 2007, 10, 22, "пиво разливное", 0, 850, 850);
        insertPurshase(db, gregorianCalendar, 2007, 10, 22, "билет", 2000, 7600, 7600);
        insertPurshase(db, gregorianCalendar, 2007, 10, 26, "продукты питания (одной суммой)", 0, 862, 862);
        insertPurshase(db, gregorianCalendar, 2007, 10, 28, "крем для лица день/ночь", 0, 1884, 1884);
        insertPurshase(db, gregorianCalendar, 2007, 10, 28, "кондиционер для волос", 0, 1308, 1308);
        insertPurshase(db, gregorianCalendar, 2007, 10, 28, "продукты питания (одной суммой)", 0, 5930, 5930);
        insertPurshase(db, gregorianCalendar, 2007, 11, 1, "продукты питания (одной суммой)", 0, 45000, 45000);
        insertPurshase(db, gregorianCalendar, 2007, 11, 1, "лампочка", 2000, 358, 358);
        insertPurshase(db, gregorianCalendar, 2007, 11, 1, "колготы капроновые (20, 40...Den)", 1000, 1803, 1803);
        insertPurshase(db, gregorianCalendar, 2007, 11, 1, "продукты питания (одной суммой)", 0, 14059, 14059);
        insertPurshase(db, gregorianCalendar, 2007, 11, 2, "продукты питания (одной суммой)", 0, 1410, 1410);
        insertPurshase(db, gregorianCalendar, 2007, 11, 3, "другие блюда", 0, 70000, 70000);
        insertPurshase(db, gregorianCalendar, 2007, 11, 3, "шампанское белое", 0, 50000, 50000);
        insertPurshase(db, gregorianCalendar, 2007, 11, 9, "продукты питания (одной суммой)", 0, 892, 892);
        insertPurshase(db, gregorianCalendar, 2007, 11, 9, "продукты питания (одной суммой)", 0, 8285, 8285);
        insertPurshase(db, gregorianCalendar, 2007, 11, 10, "благотворительность", 1000, 2600, 2600);
        insertPurshase(db, gregorianCalendar, 2007, 11, 12, "кошелек кожаный", 1000, 24000, 24000);
        insertPurshase(db, gregorianCalendar, 2007, 11, 17, "другие блюда", 0, 1270, 1270);
        insertPurshase(db, gregorianCalendar, 2007, 11, 22, "вазон для цветов", 3000, 4848, 4848);
        insertPurshase(db, gregorianCalendar, 2007, 11, 22, "продукты питания (одной суммой)", 0, 4771, 4771);
        insertPurshase(db, gregorianCalendar, 2007, 11, 24, "продукты питания (одной суммой)", 0, 12339, 12339);
        insertPurshase(db, gregorianCalendar, 2007, 11, 26, "прокладки", 12000, 730, 730);
        insertPurshase(db, gregorianCalendar, 2007, 11, 29, "батончик шоколадный с шоколадной начинкой", 1000, 475, 475);
        insertPurshase(db, gregorianCalendar, 2007, 12, 10, "благотворительность", 1000, 3000, 3000);
        insertPurshase(db, gregorianCalendar, 2007, 12, 10, "яйца куриные", 10000, 600, 600);
        insertPurshase(db, gregorianCalendar, 2007, 12, 11, "сырок в шоколаде", 2000, 330, 330);
        insertPurshase(db, gregorianCalendar, 2007, 12, 11, "продукты питания (одной суммой)", 0, 608, 608);
        insertPurshase(db, gregorianCalendar, 2007, 12, 14, "продукты питания (одной суммой)", 0, 4046, 4046);
        insertPurshase(db, gregorianCalendar, 2007, 12, 15, "продукты питания (одной суммой)", 0, 3452, 3452);
        insertPurshase(db, gregorianCalendar, 2007, 12, 15, "трусы", 2000, 3500, 3500);
        insertPurshase(db, gregorianCalendar, 2007, 12, 18, "конфеты коробка", 611000, 2249, 2249);
        insertPurshase(db, gregorianCalendar, 2007, 12, 18, "ананас", 1000, 2449, 2449);
        insertPurshase(db, gregorianCalendar, 2007, 12, 23, "продукты питания (одной суммой)", 0, 8374, 8374);
        insertPurshase(db, gregorianCalendar, 2007, 12, 29, "MP3-Flash player Canyon", 1000, 23970, 23970);

        //db.insert(CasheDatabaseHelper.DatabaseTablePurchases.TABLE_NAME, null,)
        db.close();
        Toast toast = Toast.makeText(this, "Обновлены данные в таблице \"PURSHASES\"", Toast.LENGTH_SHORT);
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

    private static void insertPurshase(SQLiteDatabase db, GregorianCalendar gregorianCalendar,
                                       int year, int month, int day,
                                       String nomenclature,
                                       int number, int price, int amount) {

        gregorianCalendar.set(Calendar.YEAR, year);
        gregorianCalendar.set(Calendar.MONTH, month-1);
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, day);
        long date = gregorianCalendar.getTimeInMillis();

        ContentValues insertValues = new ContentValues();
        insertValues.put(CasheDatabaseHelper.TablePurchases.COLUMN_DATE.name, date);
        insertValues.put(CasheDatabaseHelper.TablePurchases.COLUMN_NOMENCLATURE.name, nomenclature);
        insertValues.put(CasheDatabaseHelper.TablePurchases.COLUMN_QUANTITY.name, number);
        insertValues.put(CasheDatabaseHelper.TablePurchases.COLUMN_PRICE.name, price);
        insertValues.put(CasheDatabaseHelper.TablePurchases.COLUMN_AMOUNT.name, amount);
        db.insert(CasheDatabaseHelper.TablePurchases.TABLE_NAME, null, insertValues);
    }

    private class AddRecordsAsyncTask extends AsyncTask<Long, Void, Integer> {

        ContentValues savedValues;

        protected void onPreExecute() {
            super.onPreExecute();
            // Готовим значения для записи
            savedValues = new ContentValues();
//            savedValues.put(CasheDatabaseHelper.TablePurchases.COLUMN_DATE.name, getDate());
//            savedValues.put(CasheDatabaseHelper.TablePurchases.COLUMN_NOMENCLATURE.name, getNomenclature());
//            savedValues.put(CasheDatabaseHelper.TablePurchases.COLUMN_QUANTITY.name, String.valueOf(getQuantity()));
//            savedValues.put(CasheDatabaseHelper.TablePurchases.COLUMN_PRICE.name, String.valueOf(getPrice()));
//            savedValues.put(CasheDatabaseHelper.TablePurchases.COLUMN_AMOUNT.name, String.valueOf(getAmount()));
        }

        protected Integer doInBackground(Long... purshaseID) {
            // Пишем в БД подготовленные значения
            SQLiteOpenHelper dbh = new CasheDatabaseHelper(ActivityDatabase.this);
            SQLiteDatabase db = dbh.getReadableDatabase();
            Integer rez = db.update(CasheDatabaseHelper.TablePurchases.TABLE_NAME, savedValues, "_id = ?", new String[] {Long.toString(purshaseID[0])});
            db.close();
            return rez;
        }

        protected void onProgressUpdate(Void... values) {
            //Код, передающий информацию о ходе выполнения задачи
        }

        protected void onPostExecute(Integer rez) {
            super.onPostExecute(rez);
            //Код, выполняемый при завершении задачи
            Toast.makeText(ActivityDatabase.this, "Records was added: "+String.valueOf(rez), Toast.LENGTH_LONG).show();
        }
    }
}
