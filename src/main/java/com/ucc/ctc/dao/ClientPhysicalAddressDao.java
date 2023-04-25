package com.ucc.ctc.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ucc.ctc.models.entity.ClientEntity;
import com.ucc.ctc.models.entity.ClientPhysicalAddressEntity;

import java.util.List;
@Dao
public interface ClientPhysicalAddressDao {
    //Insert new client into table
    @Insert
    void insert(ClientPhysicalAddressEntity client);
    //update single Client in table
    @Update
    void update(ClientPhysicalAddressEntity client);
    //Delete
    @Delete
    void delete(ClientPhysicalAddressEntity client);
    //Get the client physical Address Details from the table
    @Query( "SELECT * FROM tblClientPhysicalAddress WHERE ClientId=:clientId limit 1")
    LiveData<ClientPhysicalAddressEntity> getClientPhysicalAddress(String clientId);
}