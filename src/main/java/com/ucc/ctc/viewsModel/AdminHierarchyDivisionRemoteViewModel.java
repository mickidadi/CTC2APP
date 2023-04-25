package com.ucc.ctc.viewsModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ucc.ctc.models.AdminHierarchyDivisionRootRemote;
import com.ucc.ctc.models.AdminHierarchyExtendedRootRemote;
import com.ucc.ctc.repository.AdminHierarchyDivisionRemoteRepository;
import com.ucc.ctc.repository.AdminHierarchyExtendedRemoteRepository;

public class AdminHierarchyDivisionRemoteViewModel extends ViewModel {
    private LiveData<AdminHierarchyDivisionRootRemote> adminHierarchyDivisionRootRemoteLiveData;
    private AdminHierarchyDivisionRemoteRepository repository;

    public AdminHierarchyDivisionRemoteViewModel() {

        repository = AdminHierarchyDivisionRemoteRepository.getInstance();
    }
   public void getRemoteAdminHierarchyExtended() {
       adminHierarchyDivisionRootRemoteLiveData = repository.getAdminHierarchyRemote();
    }
    public LiveData<AdminHierarchyDivisionRootRemote> getAdminHierarchyDivisionRemoteLiveDataLiveData() {
        return adminHierarchyDivisionRootRemoteLiveData;
    }
}
