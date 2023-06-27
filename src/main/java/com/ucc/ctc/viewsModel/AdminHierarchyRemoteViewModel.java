package com.ucc.ctc.viewsModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ucc.ctc.models.AdminHierarchyRemote;
import com.ucc.ctc.models.AdminHierarchyRootRemote;
import com.ucc.ctc.models.UserProfile;
import com.ucc.ctc.repository.AdminHierarchyRemoteRepository;
import com.ucc.ctc.repository.UserRemoteRepository;

import java.util.List;

public class AdminHierarchyRemoteViewModel extends ViewModel {
    private LiveData<AdminHierarchyRootRemote> adminHierarchyRootRemoteLiveData;
    private AdminHierarchyRemoteRepository repository;

    public AdminHierarchyRemoteViewModel() {

        repository = AdminHierarchyRemoteRepository.getInstance();
    }
   public void getRemoteAdminHierarchy(String url) {

        adminHierarchyRootRemoteLiveData = repository.getAdminHierarchyRemote(url);
    }
    public LiveData<AdminHierarchyRootRemote> getAdminHierarchyRemoteLiveDataLiveData() {
        return adminHierarchyRootRemoteLiveData;
    }
}
