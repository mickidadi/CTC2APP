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

import java.util.List;

@Dao
public interface AdminHierarchyDivisionDao {
    //Insert new Admin Hierarchy Division into table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(AdminHierarchyDivisionEntity adminHierarchy);
    //update single Admin Hierarchy Division in table
    @Update
    void update(AdminHierarchyDivisionEntity adminHierarchy);
    //Delete
    @Delete
    void delete(AdminHierarchyDivisionEntity adminHierarchy);
    //Delete all admin Hierarchy Division from table
    @Query("DELETE FROM admin_hierarchy_division")
    void deleteAllAdminHierarchy();
    //Get all the list of Council from the table 
    @Query( "SELECT * FROM admin_hierarchy_division" )
    LiveData<List<AdminHierarchyDivisionEntity>> getAllDivision();
    @Query("SELECT * FROM admin_hierarchy_division WHERE districtCode =:nodeId")
    LiveData<List<AdminHierarchyDivisionEntity>> getDivisionById(int nodeId);


}
