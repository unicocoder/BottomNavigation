package com.unicocoder.bottomnavigation.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchViewModel extends ViewModel {

    private final MutableLiveData<Integer> liveDataCounter = new MutableLiveData();

    public LiveData<Integer> getLiveDataCounter(){
        return liveDataCounter;
    }

    public void setLiveDataCounter(Integer counter){
        liveDataCounter.setValue(counter);
    }
}