package com.example.mapmarq_draft1.ui.saved_locations;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class SavedLocationsViewModel extends ViewModel {
    private static final String TAG = "SavedLocationsViewModel";
    private final MutableLiveData<List<String>> savedLocations = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<String>> getSavedLocations() {
        return savedLocations;
    }

    // Adds location to the list.
    public void addLocation(String building, String room) {
        List<String> currentList = savedLocations.getValue();
        if (currentList == null) {
            currentList = new ArrayList<>();
        }

        // Creates the new location string and adds it.
        String newLocation = building + " - " + room;
        if (!currentList.contains(newLocation)) {
            currentList.add(newLocation);
            savedLocations.setValue(currentList);
            Log.d(TAG, "Added location: " + newLocation);
        } else {
            Log.d(TAG, "Location " + newLocation + " already exists.");
        }
    }

    public void removeLocation(String locationToRemove) {
        List<String> currentList = savedLocations.getValue();
        if (currentList != null && !currentList.isEmpty()) {
            if (currentList.remove(locationToRemove)) {
                savedLocations.setValue(currentList);
                Log.d(TAG, "Removed location: " + locationToRemove);
            }
        } else {
            Log.d(TAG, "Attempted to remove location, but list is empty.");
        }
    }
}

