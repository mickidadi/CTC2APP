package com.ucc.ctc.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ucc.ctc.models.entity.ClientBiometricEntity;

import java.util.List;
@Dao
public interface ClientBiometricDao {
    //Insert new client Biometric into table
    @Insert
    void insert(ClientBiometricEntity client);
    //update single Client Biometric in table
    @Update
    void update(ClientBiometricEntity client);
    //Delete
    @Delete
    void delete(ClientBiometricEntity client);
    //Delete all client Biometric from table
    @Query( "DELETE FROM tblClientBiometry" )
    void deleteAllClientBiometric();
    //Get all the list of client from the table  by descending order
    @Query( "SELECT * FROM tblClientBiometry WHERE ClientId=:clientId order by id DESC")
    LiveData<List<ClientBiometricEntity>> getAllClientBiometric(String clientId);
    //Get all the list of client from the table  by descending order

}
