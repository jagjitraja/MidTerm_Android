package com.example.t00533766.mid_term;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by T00533766 on 3/19/2018.
 */

public class NumbersRepository {

        private NumbersDao numbersDao;
        private LiveData<List<NumbersData>> guestInfoLiveData;

        public NumbersRepository(Context context){
            numbersDao = NumbersRoomDatabase.getNumbersRoomDatabaseInstance(context).numbersDao();
        }

        public LiveData<List<NumbersData>> getAllGuestInfo(){
            guestInfoLiveData = new MutableLiveData<>();
            SelectAsyncTask asyncTask = new SelectAsyncTask(numbersDao);
            try {
                guestInfoLiveData = asyncTask.execute().get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            return guestInfoLiveData;
        }

        public void insertGuestInfo(NumbersData info){
            new InsertAsyncTask(numbersDao).execute(info);
        }


        public class InsertAsyncTask extends AsyncTask<NumbersData, Integer, Void> {
            private NumbersDao guestInfoDAO;
            public InsertAsyncTask(NumbersDao guestInfoDAO){
                this.guestInfoDAO = guestInfoDAO;
            }
            @Override
            protected Void doInBackground(NumbersData... numbersData) {
                guestInfoDAO.insertData(numbersData[0]);
                return null;
            }
        }


        public class SelectAsyncTask extends AsyncTask<Void, Integer, LiveData<List<NumbersData>>> {

            private final NumbersDao guestInfoDAO;

            public SelectAsyncTask(NumbersDao infoDAO){
                this.guestInfoDAO = infoDAO;
            }

            @Override
            protected LiveData<List<NumbersData>> doInBackground(Void... voids) {

                return guestInfoDAO.getAllData();
            }

        }

}
