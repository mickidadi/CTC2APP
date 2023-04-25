package com.ucc.ctc.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdminHierarchyExtendedRootRemote {
    @SerializedName("adminHierarchyExtendedInfo")
    @Expose
    private List<AdminHierarchyExtendedRemote> adminHierarchyExtendedInfoList=null;
        public List<AdminHierarchyExtendedRemote> getAdminHierarchyInfoList() {
            return adminHierarchyExtendedInfoList;
        }
        public void setItems(List<AdminHierarchyExtendedRemote> adminHierarchyExtendedInfoList) {
            this.adminHierarchyExtendedInfoList = adminHierarchyExtendedInfoList;
        }
}
