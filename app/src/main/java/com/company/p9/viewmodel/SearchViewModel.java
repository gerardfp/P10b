package com.company.p9.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class SearchViewModel extends AndroidViewModel {

    public MutableLiveData<String> term = new MutableLiveData<>();

    public SearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void setTerm(String newTerm){
        term.setValue(newTerm);
    }
}
