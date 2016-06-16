package com.example.inalivayko.testall.cashe;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.inalivayko.testall.examples_library.database.TableColumn;

/**
 * Created by i.nalivayko on 16.05.2016.
 */
public class CasheDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "cashe"; // Имя базы данных
    private static final int DB_VERSION = 1; // Версия базы данных

    public CasheDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TablePurchases.CREATING_STRING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @NonNull
    public static Boolean recreateTable(Context context) {

        // String sql = "DROP TABLE "+TablePurchases.TABLE_NAME+";";
        String sql = TablePurchases.CREATING_STRING;
        CasheDatabaseHelper dbh = new CasheDatabaseHelper(context);
        SQLiteDatabase db = dbh.getWritableDatabase();
        try{
            db.execSQL(sql);
        } catch(SQLiteException e) {
            return false;
        }

        return true;
    }

    @NonNull
    public static Boolean createTable(Context context, String createString) {
        CasheDatabaseHelper dbh = new CasheDatabaseHelper(context);
        SQLiteDatabase db = dbh.getWritableDatabase();
        try{
            db.execSQL(createString);
        } catch(SQLiteException e) {
            return false;
        }
        return true;
    }

    @NonNull
    public static Boolean deleteTable(Context context, String tableName) {
        String sql = "DROP TABLE IF EXISTS "+tableName+";";
        CasheDatabaseHelper dbh = new CasheDatabaseHelper(context);
        SQLiteDatabase db = dbh.getWritableDatabase();
        try{
            db.execSQL(sql);
        } catch(SQLiteException e) {
            return false;
        }return true;
    }

    @NonNull
    public static Boolean renameTable(Context context, String fromName, String toName) {
        String sql = "ALTER TABLE "+fromName+" RENAME TO "+toName+";";
        CasheDatabaseHelper dbh = new CasheDatabaseHelper(context);
        SQLiteDatabase db = dbh.getWritableDatabase();
        try{
            db.execSQL(sql);
        } catch(SQLiteException e) {
            return false;
        }
        return true;
    }

    @NonNull
    public static Boolean addTableColumn(Context context, String tableName, String columnName, String columnType) {
        String sql = "ALTER TABLE "+tableName+" ADD COLUMN "+columnName+" "+columnType+";"; // ALTER TABLE tbl_info ADD COLUMN weight INTEGER;
        CasheDatabaseHelper dbh = new CasheDatabaseHelper(context);
        SQLiteDatabase db = dbh.getWritableDatabase();
        try{
            db.execSQL(sql);
        } catch(SQLiteException e) {
            return false;
        }
        return true;
    }

    public static Boolean deleteTableColumn(Context context, String tableName, String columnName) {

        // Нужно:
        // - создать новую таблицу нужной структуры;
        // - скопировать данные из старой таблицы в новую;
        // - удалить старую таблицу;
        // - переименовать существующую таблицу.

//        String sql = "ALTER TABLE "+tableName+" ADD COLUMN "+columnName+" "+columnType+";"; // ALTER TABLE tbl_info ADD COLUMN weight INTEGER;
//        CasheDatabaseHelper dbh = new CasheDatabaseHelper(context);
//        SQLiteDatabase db = dbh.getWritableDatabase();
//        try{
//            db.execSQL(sql);
//        } catch(SQLiteException e) {
//            return false;
//        }
        return true;
    }

    public static Boolean renameTableColumn(Context context, String tableName, String fromColumnName, String toColumnName) {

        // Нужно:
        // - переименовать существующую таблицу;
        // - создать новую таблицу нужной структуры;
        // - скопировать данные из старой таблицы в новую;
        // - удалить старую таблицу;

//        String sql = "ALTER TABLE "+tableName+" ADD COLUMN "+columnName+" "+columnType+";"; // ALTER TABLE tbl_info ADD COLUMN weight INTEGER;
//        CasheDatabaseHelper dbh = new CasheDatabaseHelper(context);
//        SQLiteDatabase db = dbh.getWritableDatabase();
//        try{
//            db.execSQL(sql);
//        } catch(SQLiteException e) {
//            return false;
//        }
        return true;
    }

    public static class TablePurchases {
        public static final String TABLE_NAME = "PURCHASES";
        public static final TableColumn COLUMN_ID = new TableColumn("_id", "INTEGER", "PRIMARY KEY AUTOINCREMENT");
        public static final TableColumn COLUMN_DATE = new TableColumn("DATE", "INTEGER", "");
        public static final TableColumn COLUMN_NOMENCLATURE = new TableColumn("NOMENCLATURE", "TEXT", "");
        public static final TableColumn COLUMN_QUANTITY = new TableColumn("QUANTITY", "INTEGER", "");
        public static final TableColumn COLUMN_PRICE = new TableColumn("PRICE", "INTEGER", "");
        public static final TableColumn COLUMN_AMOUNT = new TableColumn("AMOUNT", "INTEGER", "");
        public static final String CREATING_STRING = "CREATE TABLE "+TABLE_NAME+" ("+
                COLUMN_ID.name+" "+COLUMN_ID.type+" "+COLUMN_ID.attribute+", "+
                COLUMN_DATE.name+" "+COLUMN_DATE.type+", "+
                COLUMN_NOMENCLATURE.name+" "+COLUMN_NOMENCLATURE.type+", "+
                COLUMN_QUANTITY.name+" "+COLUMN_QUANTITY.type+", "+
                COLUMN_PRICE.name+" "+COLUMN_PRICE.type+", "+
                COLUMN_AMOUNT.name+" "+COLUMN_AMOUNT.type+");";
        }
}
