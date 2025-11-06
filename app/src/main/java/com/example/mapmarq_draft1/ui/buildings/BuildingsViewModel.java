package com.example.mapmarq_draft1.ui.buildings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BuildingsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BuildingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is buildings fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}