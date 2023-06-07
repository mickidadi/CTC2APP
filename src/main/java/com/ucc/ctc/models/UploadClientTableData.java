package com.ucc.ctc.models;

import java.util.List;

public class UploadClientTableData {
    private String tableName;
    private List<Record> records;

    // Constructor, getters, and setters

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Record> getRecords() {
        return records;
    }
    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
