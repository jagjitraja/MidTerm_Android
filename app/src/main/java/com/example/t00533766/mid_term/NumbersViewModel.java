package com.example.t00533766.mid_term;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by T00533766 on 3/19/2018.
 */

public class NumbersViewModel extends ViewModel {

    private NumbersRepository repository;

    public NumbersViewModel() {
        Log.d("======================", "GuestInfoViewModel: ");
    }

    public void createReporsitory(Context context){
        this.repository = new NumbersRepository(context);
    }

    public LiveData<List<NumbersData>> getNumbersInfoLiveData(){

        return repository.getAllGuestInfo();
    }

    public void insertNumbersData(NumbersData numbersData){
        repository.insertGuestInfo(numbersData);
    }


}
