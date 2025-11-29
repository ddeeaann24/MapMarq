package com.example.mapmarq_draft1.ui.buildings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BuildingsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BuildingsViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}