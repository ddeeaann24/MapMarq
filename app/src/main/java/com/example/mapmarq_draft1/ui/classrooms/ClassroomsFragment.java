package com.example.mapmarq_draft1.ui.classrooms;

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

public class ClassroomsFragment extends Fragment {

    private ClassroomsViewModel classroomsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        classroomsViewModel =
                new ViewModelProvider(this).get(ClassroomsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_classrooms, container, false);
        final TextView textView = root.findViewById(R.id.text_classrooms);
        classroomsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}