package com.example.t00533766.mid_term;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by T00533766 on 3/19/2018.
 */

@Database(entities = NumbersData.class, version = 1)
public abstract class NumbersRoomDatabase extends RoomDatabase {

    public abstract NumbersDao numbersDao();
    private static NumbersRoomDatabase numbersRoomDatabase;

    public static NumbersRoomDatabase getNumbersRoomDatabaseInstance(Context context){
        if(numbersRoomDatabase==null){
            numbersRoomDatabase = Room.databaseBuilder(context,NumbersRoomDatabase.class,
                    NumbersRoomDatabase.class.getSimpleName()).build();
        }
        return numbersRoomDatabase;
    }

}
