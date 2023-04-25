package com.ucc.ctc.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ucc.ctc.models.entity.UserEntity;
@Dao
public interface UserLoginDao {
    @Query("SELECT * FROM tblUsers LIMIT 1")
    LiveData<UserEntity> getUserAll();

    @Query("SELECT * FROM tblUsers WHERE LoginName = :username OR Password = :password LIMIT 1")
    LiveData<UserEntity> getUser(String username, String password);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserEntity user);
}
