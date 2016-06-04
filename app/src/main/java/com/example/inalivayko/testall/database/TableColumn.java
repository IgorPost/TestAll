package com.example.inalivayko.testall.database;

/**
 * Created by i.nalivayko on 01.06.2016.
 */
public class TableColumn {
    public String name;
    public String type;
    public String attribute;
    public TableColumn(String columnName, String columnType, String columnAttribute) {
        this.name = columnName;
        this.type = columnType;
        this.attribute = columnAttribute;
    }
}
