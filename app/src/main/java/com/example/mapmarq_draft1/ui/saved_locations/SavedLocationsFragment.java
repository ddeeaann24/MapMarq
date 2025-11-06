package com.example.mapmarq_draft1.ui.saved_locations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mapmarq_draft1.R;

public class SavedLocationsFragment extends Fragment {

    private SavedLocationsViewModel savedLocationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        savedLocationsViewModel =
                new ViewModelProvider(this).get(SavedLocationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_saved_locations, container, false);
        final TextView textView = root.findViewById(R.id.text_saved_locations);
        savedLocationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}