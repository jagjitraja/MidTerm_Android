package com.example.t00533766.mid_term;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;

/**
 * Created by T00533766 on 3/19/2018.
 */

@Dao
public interface NumbersDao {

    @Insert
    public  void insertData(NumbersData numbersData);

    @Query("SELECT * FROM NumbersData")
    public  LiveData<ArrayList<NumbersData>> getAllData();

    @Delete
    public  void deleteData(NumbersData numbersData);

    @Update
    public  void updateData(NumbersData numbersData);


}
