package com.ucc.ctc.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class ClientRootRemote {
    @SerializedName("ClientInfo")
    @Expose
    private List<ClientRemote> clientInfoList=null;
   // private List<VisitInfo> visitInfo = new ArrayList<VisitInfo>();
        public List<ClientRemote> getClientInfoList() {
            return clientInfoList;
        }
        public void setItems(List<ClientRemote> clientInfoList) {
            this.clientInfoList = clientInfoList;
        }
}
