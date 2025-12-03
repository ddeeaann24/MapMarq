package com.example.mapmarq_draft1.ui.settings;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.content.res.Configuration;
import android.widget.AdapterView;
import android.content.SharedPreferences;
import android.content.Context;
import androidx.appcompat.app.AppCompatDelegate;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mapmarq_draft1.R;

import java.util.Locale;

public class SettingsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        Spinner languageSpinner = root.findViewById(R.id.spinner_language);

        // Empty adapter for now, update later with languages.
        String[] languages = {"English", "Español", "中文"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                languages
        );
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, new String[]{});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        languageSpinner.setAdapter(adapter);

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String[] codes = {"en", "es", "zh"};
                String selectedCode = codes[position];

                // Load previously saved language
                SharedPreferences prefs = requireContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
                String currentCode = prefs.getString("language", "en");

                // Only apply and recreate if different
                if (!selectedCode.equals(currentCode)) {
                    prefs.edit().putString("language", selectedCode).apply();
                    setLocale(selectedCode);
                    requireActivity().recreate();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });


        // 2. Theme button setup (add this right after the spinner)
        Button themeButton = root.findViewById(R.id.button_change_theme);

        themeButton.setOnClickListener(v -> {
            int currentMode = getResources().getConfiguration().uiMode &
                    android.content.res.Configuration.UI_MODE_NIGHT_MASK;

            if (currentMode == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        });

        return root;
    }
    private void setLocale(String langCode) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);

        requireContext().getResources().updateConfiguration(
                config,
                requireContext().getResources().getDisplayMetrics()
        );
    }
}


