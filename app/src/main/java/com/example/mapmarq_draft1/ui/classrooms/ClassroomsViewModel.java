package com.example.mapmarq_draft1.ui.classrooms;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ClassroomsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ClassroomsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is classrooms fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}