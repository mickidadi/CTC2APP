package com.ucc.ctc.models;

import java.util.List;

public class UploadClientPayload {
    private String session;
    private int sessionStatus;
    private List<UploadClientTableData> tableData;
    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public int getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(int sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public List<UploadClientTableData> getTableData() {
        return tableData;
    }
 public void setTableData(List<UploadClientTableData> tableData) {
        this.tableData = tableData;
    }
}
