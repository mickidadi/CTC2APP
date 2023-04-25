package com.ucc.ctc.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdminHierarchyRootRemote {
    @SerializedName("adminHierarchyInfo")
    @Expose
    private List<AdminHierarchyRemote> adminHierarchyInfoList=null;
        public List<AdminHierarchyRemote> getAdminHierarchyInfoList() {
            return adminHierarchyInfoList;
        }
        public void setItems(List<AdminHierarchyRemote> adminHierarchyInfoList) {
            this.adminHierarchyInfoList = adminHierarchyInfoList;
        }
}
