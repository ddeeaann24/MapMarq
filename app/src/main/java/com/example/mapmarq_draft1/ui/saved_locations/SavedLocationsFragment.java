package com.example.mapmarq_draft1.ui.saved_locations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mapmarq_draft1.R;

public class SavedLocationsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_saved_locations, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] savedLocations = {"Marquette University", "Home", "Work", "Favorite Coffee Shop"};

        RecyclerView recyclerView = view.findViewById(R.id.saved_locations_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        SavedLocationsAdapter adapter = new SavedLocationsAdapter(savedLocations);
        recyclerView.setAdapter(adapter);
    }
}
