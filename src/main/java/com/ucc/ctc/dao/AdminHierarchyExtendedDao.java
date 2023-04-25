package com.ucc.ctc.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ucc.ctc.models.entity.AdminHierarchyDivisionEntity;
import com.ucc.ctc.models.entity.AdminHierarchyEntity;
import com.ucc.ctc.models.entity.AdminHierarchyExtendedEntity;

import java.util.List;

@Dao
public interface AdminHierarchyExtendedDao {
    //Insert new Admin Hierarchy into table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(AdminHierarchyExtendedEntity adminHierarchy);
    //update single Admin Hierarchy in table
    @Update
    void update(AdminHierarchyExtendedEntity adminHierarchy);
    //Delete
    @Delete
    void delete(AdminHierarchyExtendedEntity adminHierarchy);
    //Delete all admin Hierarchy from table
    @Query("DELETE FROM admin_hierarchy_extended")
    void deleteAllAdminHierarchy();
    //Get all the list of Council from the table 
    @Query( "SELECT * FROM admin_hierarchy_extended" )
    LiveData<List<AdminHierarchyExtendedEntity>> getAllCouncil();
    @Query("SELECT * FROM admin_hierarchy_extended WHERE node_id =:nodeId")
    LiveData<List<AdminHierarchyExtendedEntity>> getDivisionById(String nodeId);
    @Query("SELECT * FROM admin_hierarchy_extended WHERE node_id =:nodeId")
    LiveData<List<AdminHierarchyExtendedEntity>> getWardById(String nodeId);
    @Query("SELECT * FROM admin_hierarchy_extended WHERE node_id =:nodeId")
    LiveData<List<AdminHierarchyExtendedEntity>> getVillageById(String nodeId);

}
