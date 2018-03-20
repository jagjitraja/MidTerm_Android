package com.example.t00533766.mid_term;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

/**
 * Created by T00533766 on 3/19/2018.
 */

public class NumbersViewModel extends ViewModel {

    private LiveData<ArrayList<NumbersData>> listLiveData;

    public LiveData<ArrayList<NumbersData>> getListLiveData(){
        return listLiveData;
    }

}
