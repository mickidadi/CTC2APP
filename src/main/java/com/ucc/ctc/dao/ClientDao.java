package com.ucc.ctc.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ucc.ctc.models.entity.ClientEntity;

import java.util.List;
@Dao
public interface ClientDao {
    //Insert new client into table
    @Insert
    void insert(ClientEntity client);
    //update single Client in table
    @Update
    void update(ClientEntity client);
    //Delete
    @Delete
    void delete(ClientEntity client);
    //Delete all client client from table
    @Query( "DELETE FROM tblClients" )
    void deleteAllClient();
    //Get all the list of client from the table  by descending order
    @Query( "SELECT * FROM tblClients order by id DESC")
    LiveData<List<ClientEntity>> getAllClients();
    //Get all the list of client from the table  by descending order
    @Query( "SELECT group_concat(id||' '||FirstName||' '||MiddleName ||' '||LastName) as name  FROM tblClients group by id")
    LiveData<List<String>> getAllClientName();
    //Get all the list of client from the table  by descending order
    @Query( "SELECT * FROM tblClients WHERE MiddleName LIKE :name OR LastName LIKE :name OR FirstName LIKE :name OR ClientId LIKE :clientId order by id ASC")
    LiveData<List<ClientEntity>> getAllClientSearch(String clientId,String name);
}
