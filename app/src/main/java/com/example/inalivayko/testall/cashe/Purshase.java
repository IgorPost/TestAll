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
public class Purshase {

    private long ID;
    private String nomenclature;
    private Double number;
    private Double price;
    private Double amount;
    private Context context;
    private Activity activity;

    static interface intPurshase {
        void setAmount();
    };
    private intPurshase listener;

    public Purshase(long ID, Context context, Activity activity) {

        this.ID = ID;
        this.context = context;
        this.listener = (intPurshase) activity;
        this.activity = activity;

        SQLiteOpenHelper dbh = new CasheDatabaseHelper(context);
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cursor = db.query(CasheDatabaseHelper.TablePurchases.TABLE_NAME, new String[] {"_id", "NOMENCLATURE", "QUANTITY", "PRICE", "AMOUNT"}, "_id = ?", new String[] {String.valueOf(ID)}, null, null, null);
        if (cursor.moveToNext()) {

            setNomenclature(cursor.getString(cursor.getColumnIndexOrThrow("NOMENCLATURE")));
            setNumber(cursor.getDouble(cursor.getColumnIndexOrThrow("QUANTITY")));
            setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow("PRICE")));
            setAmount(cursor.getDouble(cursor.getColumnIndexOrThrow("AMOUNT")));

            // String purshaseData = "Информация о покупке: "+strNomenclature;
            // textPurshaseID.setText(purshaseData);
        }
        else {
            // textPurshaseID.setText("Нет данных для выборки...");
        }
        db.close();

        // new UpdateDrinkTask().execute(ID);

    }

    // Сохранить информацию о покупке асинхронно
    private class SavePurchaseTask extends AsyncTask<Long, Void, Integer> {

        ContentValues savedValues;

        protected void onPreExecute() {
            super.onPreExecute();
            // Готовим значения для записи
            savedValues = new ContentValues();
            savedValues.put("NOMENCLATURE", getNomenclature());
            savedValues.put("NUMBER", String.valueOf(getNumber()));
            savedValues.put("PRICE", String.valueOf(getPrice()));
            savedValues.put("AMOUNT", String.valueOf(getAmount()));
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
            Toast.makeText(activity, "Updated "+String.valueOf(rez)+" rows.", Toast.LENGTH_LONG).show();
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

    public String getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
    }

    public long getID() {
        return ID;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
        this.amount = this.number*this.price;
        listener.setAmount();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
