package com.ucc.ctc.viewsModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.ucc.ctc.models.AdminHierarchyExtendedRootRemote;
import com.ucc.ctc.repository.AdminHierarchyExtendedRemoteRepository;

public class AdminHierarchyExtendedRemoteViewModel extends ViewModel {
    private LiveData<AdminHierarchyExtendedRootRemote> adminHierarchyExtendedRootRemoteLiveData;
    private AdminHierarchyExtendedRemoteRepository repository;

    public AdminHierarchyExtendedRemoteViewModel() {

        repository = AdminHierarchyExtendedRemoteRepository.getInstance();
    }
   public void getRemoteAdminHierarchyExtended() {
       adminHierarchyExtendedRootRemoteLiveData = repository.getAdminHierarchyRemote();
    }
    public LiveData<AdminHierarchyExtendedRootRemote> getAdminHierarchyRemoteLiveDataLiveData() {
        return adminHierarchyExtendedRootRemoteLiveData;
    }
}
