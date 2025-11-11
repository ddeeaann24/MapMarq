package com.example.mapmarq_draft1.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mapmarq_draft1.R;

public class SettingsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        Spinner languageSpinner = root.findViewById(R.id.spinner_language);

        // Empty adapter for now, update later with languages.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, new String[]{});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        languageSpinner.setAdapter(adapter);

        return root;
    }
}
