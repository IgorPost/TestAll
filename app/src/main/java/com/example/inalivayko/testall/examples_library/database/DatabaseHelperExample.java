package com.example.inalivayko.testall.examples_library.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by i.nalivayko on 01.06.2016.
 */
public class DatabaseHelperExample extends SQLiteOpenHelper {

    private static final String DB_NAME = "examples"; // Имя базы данных
    private static final int DB_VERSION = 1; // Версия базы данных

    public DatabaseHelperExample(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseHelperExample.CreateString(TableEx1.TABLE_NAME, TableEx1.COLUMNS));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static String CreateString(String tableName, TableColumn columns[]) {

        StringBuffer fields = new StringBuffer();

        for (TableColumn col: columns) {

            if (!fields.toString().equals(""))
                fields.append("; ");

            fields.append(col.name).append(" ").append(col.type);

            if (!col.attribute.equals(""))
                fields.append(" ").append(col.attribute);
        }

        return "CREATE TABLE "+tableName+" ("+fields.toString()+");";
    }
}
