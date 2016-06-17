package com.example.inalivayko.testall.cashe;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by i.nalivayko on 17.05.2016.
 */
public class Purchase {

    private final long ID;
    private int version;

    private long date;
    private String nomenclature;
    private int quantity;
    private long price;
    private long amount;

    private Context context;

    interface intPurchase {
        void setAmount();
    };
    private intPurchase listener;

    public Purchase(long ID, Activity activity) {

        this.ID = ID;
        this.context = activity;
        this.listener = (intPurchase) activity;

        SQLiteOpenHelper dbh = new CasheDatabaseHelper(context);
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cursor = db.query(CasheDatabaseHelper.TablePurchases.TABLE_NAME, new String[] {CasheDatabaseHelper.TablePurchases.COLUMN_ID.name, CasheDatabaseHelper.TablePurchases.COLUMN_DATE.name, CasheDatabaseHelper.TablePurchases.COLUMN_NOMENCLATURE.name, CasheDatabaseHelper.TablePurchases.COLUMN_QUANTITY.name, CasheDatabaseHelper.TablePurchases.COLUMN_PRICE.name, CasheDatabaseHelper.TablePurchases.COLUMN_AMOUNT.name}, "_id = ?", new String[] {String.valueOf(ID)}, null, null, null);
        if (cursor.moveToNext()) {
            this.date = cursor.getLong(cursor.getColumnIndexOrThrow(CasheDatabaseHelper.TablePurchases.COLUMN_DATE.name));
            this.nomenclature = cursor.getString(cursor.getColumnIndexOrThrow(CasheDatabaseHelper.TablePurchases.COLUMN_NOMENCLATURE.name));
            this.quantity = cursor.getInt(cursor.getColumnIndexOrThrow(CasheDatabaseHelper.TablePurchases.COLUMN_QUANTITY.name));
            this.price = cursor.getLong(cursor.getColumnIndexOrThrow(CasheDatabaseHelper.TablePurchases.COLUMN_PRICE.name));
            this.amount = cursor.getLong(cursor.getColumnIndexOrThrow(CasheDatabaseHelper.TablePurchases.COLUMN_AMOUNT.name));
        }
        else {
            Toast.makeText(context, "Нет данных для выборки...", Toast.LENGTH_LONG).show();
        }
        cursor.close();
        db.close();
    }

    // Сохранить информацию о покупке асинхронно
    private class SavePurchaseTask extends AsyncTask<Long, Void, Integer> {

        ContentValues savedValues;

        protected void onPreExecute() {
            super.onPreExecute();
            // Готовим значения для записи
            savedValues = new ContentValues();
            savedValues.put(CasheDatabaseHelper.TablePurchases.COLUMN_DATE.name, getDate());
            savedValues.put(CasheDatabaseHelper.TablePurchases.COLUMN_NOMENCLATURE.name, getNomenclature());
            savedValues.put(CasheDatabaseHelper.TablePurchases.COLUMN_QUANTITY.name, String.valueOf(getQuantity()));
            savedValues.put(CasheDatabaseHelper.TablePurchases.COLUMN_PRICE.name, String.valueOf(getPrice()));
            savedValues.put(CasheDatabaseHelper.TablePurchases.COLUMN_AMOUNT.name, String.valueOf(getAmount()));
        }

        protected Integer doInBackground(Long... purshaseID) {
            // Пишем в БД подготовленные значения
            SQLiteOpenHelper dbh = new CasheDatabaseHelper(context);
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
            Toast.makeText(context, "Updated "+String.valueOf(rez)+" rows.", Toast.LENGTH_LONG).show();
        }
    }

    public Boolean save() {

        new SavePurchaseTask().execute(this.ID);
        //Toast.makeText(activity, "Saved", Toast.LENGTH_LONG).show(); //String.valueOf(rez[0])

//        SQLiteOpenHelper CasheDatabaseHelper = new CasheDatabaseHelper(context);
//        SQLiteDatabase db = CasheDatabaseHelper.getReadableDatabase();
//        ContentValues insertValues = new ContentValues();
//        insertValues.put("NOMENCLATURE", this.getNomenclature());
//        insertValues.put("NUMBER", this.getNumber());
//        insertValues.put("PRICE", this.getPrice());
//        insertValues.put("AMOUNT", this.getAmount());
//        db.update("PURSHASES", insertValues, "_id = ?", new String[] {String.valueOf(this.ID)});
//        db.close();

        return true;
    }

    @Override
    public String toString() {
        return "Purshase{" +
                "ID=" + ID +
                ", nomenclature='" + nomenclature + '\'' +
                '}';
    }

    public static Cursor getList(Context context) {
        SQLiteOpenHelper dbh = new CasheDatabaseHelper(context);
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cursor = db.query(CasheDatabaseHelper.TablePurchases.TABLE_NAME,
                new String[] {CasheDatabaseHelper.TablePurchases.COLUMN_ID.name,
                        CasheDatabaseHelper.TablePurchases.COLUMN_DATE.name,
                        CasheDatabaseHelper.TablePurchases.COLUMN_NOMENCLATURE.name,
                        CasheDatabaseHelper.TablePurchases.COLUMN_QUANTITY.name,
                        CasheDatabaseHelper.TablePurchases.COLUMN_PRICE.name,
                        CasheDatabaseHelper.TablePurchases.COLUMN_AMOUNT.name},
                null, null, null, null,
                CasheDatabaseHelper.TablePurchases.COLUMN_DATE.name+" DESC, "+CasheDatabaseHelper.TablePurchases.COLUMN_ID.name+" ASC");
        return cursor;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //                              SETTERS AND GETTERS

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
    }

    public long getID() {
        return ID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int number) {
        this.quantity = number;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
        this.amount = this.quantity*this.price;
        listener.setAmount();
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
