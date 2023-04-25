package com.ucc.ctc.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdminHierarchyDivisionRootRemote {
    @SerializedName("adminHierarchyDivisionInfo")
    @Expose
    private List<AdminHierarchyDivisionRemote> adminHierarchyDivisionInfoList=null;
        public List<AdminHierarchyDivisionRemote> getAdminHierarchyDivisionInfoList() {
            return adminHierarchyDivisionInfoList;
        }
        public void setItems(List<AdminHierarchyDivisionRemote> adminHierarchyDivisionInfoList) {
            this.adminHierarchyDivisionInfoList = adminHierarchyDivisionInfoList;
        }
}
