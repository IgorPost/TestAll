package com.example.inalivayko.testall.examples_library.database;

/**
 * Created by i.nalivayko on 01.06.2016.
 */
public class TableEx1 {

    public static final String TABLE_NAME = "ex1";

    public static final TableColumn COLUMN_ID = new TableColumn("_id", "INTEGER", "PRIMARY KEY AUTOINCREMENT");
    public static final TableColumn COLUMN_DATE = new TableColumn("DATE", "INTEGER", "");
    public static final TableColumn COLUMN_NOMENCLATURE = new TableColumn("NAME", "TEXT", "");
    public static final TableColumn COLUMN_QUANTITY = new TableColumn("QUANTITY", "INTEGER", "");
    public static final TableColumn COLUMN_PRICE = new TableColumn("PRICE", "INTEGER", "");
    public static final TableColumn COLUMN_AMOUNT = new TableColumn("AMOUNT", "INTEGER", "");

    public static final TableColumn[] COLUMNS = {COLUMN_ID, COLUMN_DATE, COLUMN_NOMENCLATURE, COLUMN_QUANTITY, COLUMN_PRICE, COLUMN_AMOUNT};
}
