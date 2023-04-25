package com.ucc.ctc.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ucc.ctc.models.entity.ClientPhysicalAddressEntity;
import com.ucc.ctc.models.entity.ClientTreatmentSupporterEntity;

import java.util.List;

@Dao
public interface ClientTreatmentSupporterDao {
    //Insert new client Treatment Supporter into table
    @Insert
    void insert(ClientTreatmentSupporterEntity clientTreatmentSupporter);
    //update single Client Treatment Supporter in table
    @Update
    void update(ClientTreatmentSupporterEntity clientTreatmentSupporter);
    //Delete
    @Delete
    void delete(ClientTreatmentSupporterEntity clientTreatmentSupporter);
    //Get the client Treatment Supporter Details from the table
    @Query( "SELECT * FROM tblClientTreatmentSupporter WHERE ClientId=:clientId limit 1")
    LiveData<ClientTreatmentSupporterEntity> getClientTreatmentSupporter(String clientId);
}