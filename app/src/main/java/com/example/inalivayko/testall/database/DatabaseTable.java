package com.example.inalivayko.testall.database;

/**
 * Created by i.nalivayko on 01.06.2016.
 */
abstract public class DatabaseTable {

    public final String TABLE_NAME;
    public final TableColumn[] TABLE_COLUMNS;

    public DatabaseTable(String tableName, TableColumn[] tableColumns) {
        TABLE_NAME = tableName;
        TABLE_COLUMNS = tableColumns;
    }
}
