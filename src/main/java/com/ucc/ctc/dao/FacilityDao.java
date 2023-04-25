package com.ucc.ctc.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ucc.ctc.models.entity.FacilityEntity;

import java.util.List;
@Dao
public interface FacilityDao {
    //Insert new Facility into table

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(FacilityEntity facility);
    //update single Facility in table
    @Update
    void update(FacilityEntity facility);
    //Delete
    @Delete
    void delete(FacilityEntity facility);
    //Delete all Facility from table
    @Query( "DELETE FROM tblFacilities" )
    void deleteAllFacility();
    //Get all the list of Facility from the table  by descending order
    @Query( "SELECT * FROM tblFacilities" )
    LiveData<List<FacilityEntity>> getAllFacility();
    //Get all the list of Facility from the table  by descending order
    @Query( "SELECT group_concat(FacilityId||' '||FacilityName||' '||FacilityType) as name  FROM tblFacilities group by id")
    LiveData<List<String>> getAllFacilityName();
    @Query("SELECT * FROM tblFacilities WHERE FacilityId =:hfrId")
    LiveData<FacilityEntity> getFacilityById(String hfrId);
}
