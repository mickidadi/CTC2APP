package com.ucc.ctc.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ucc.ctc.models.entity.PatientVisitEntity;

import java.util.List;
@Dao
public interface PatientVisitDao {
    //Insert new patient in table
    @Insert
    void insert(PatientVisitEntity visit);
    //Update single patient in table
    @Update
    void update(PatientVisitEntity visit);

    //Delete single patient from table
    @Delete
    void delete(PatientVisitEntity visit);
    //Delete all patient from table
    @Query("DELETE FROM tblPatients")
    void deleteAllPlayer();
    //Get all the list of client info from table
     @Query("SELECT * FROM tblVisits WHERE PatientId = :patientId")
    LiveData<List<PatientVisitEntity>> getClientVisitInfoByClientId(String patientId);
}
