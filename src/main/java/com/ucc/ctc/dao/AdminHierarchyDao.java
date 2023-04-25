package com.ucc.ctc.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.ucc.ctc.models.entity.AdminHierarchyEntity;
import java.util.List;

@Dao
public interface AdminHierarchyDao {
    //Insert new Admin Hierarchy into table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(AdminHierarchyEntity adminHierarchy);
    //update single Admin Hierarchy in table
    @Update
    void update(AdminHierarchyEntity adminHierarchy);
    //Delete
    @Delete
    void delete(AdminHierarchyEntity adminHierarchy);
    //Delete all admin Hierarchy from table
    @Query("DELETE FROM admin_hierarchy")
    void deleteAllAdminHierarchy();
    //Get all the list of Council from the table 
    @Query( "SELECT * FROM `admin_hierarchy` WHERE LENGTH(node_id) - LENGTH(REPLACE(node_id, '.', ''))=4" )
    LiveData<List<AdminHierarchyEntity>> getAllCouncil();
    @Query("SELECT * FROM admin_hierarchy WHERE node_id =:nodeId")
    LiveData<List<AdminHierarchyEntity>> getDivisionById(String nodeId);
    @Query("SELECT * FROM admin_hierarchy WHERE  LENGTH(node_id) - LENGTH(REPLACE(node_id, '.', ''))=5 AND council like:nodeId")
    LiveData<List<AdminHierarchyEntity>> getWardById(String nodeId);
    @Query("SELECT * FROM admin_hierarchy WHERE  LENGTH(node_id) - LENGTH(REPLACE(node_id, '.', ''))=6 AND  ward like:nodeId")
    LiveData<List<AdminHierarchyEntity>> getVillageById(String nodeId);

}
