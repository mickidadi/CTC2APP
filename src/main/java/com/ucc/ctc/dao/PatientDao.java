package com.ucc.ctc.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ucc.ctc.models.entity.PatientEntity;

import java.util.List;

@Dao
public interface PatientDao {

    //Insert new patient in table
    @Insert
    void insert(PatientEntity patient);

    //Update single patient in table
    @Update
    void update(PatientEntity patient);

    //Delete single patient from table
    @Delete
    void delete(PatientEntity patient);

    //Delete all patient from table
    @Query("DELETE FROM tblPatients")
    void deleteAllPlayer();

    //Get all the list of patients from table by descending order
    @Query("SELECT * FROM tblPatients")
    LiveData<List<PatientEntity>> getAllPatients();

}