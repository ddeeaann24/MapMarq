package com.example.mapmarq_draft1.ui.saved_locations;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SavedLocationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SavedLocationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is saved_locations fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
