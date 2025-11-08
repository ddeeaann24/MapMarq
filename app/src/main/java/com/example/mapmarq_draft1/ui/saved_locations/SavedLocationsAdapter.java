package com.example.mapmarq_draft1.ui.saved_locations;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mapmarq_draft1.databinding.SavedLocationItemBinding;

public class SavedLocationsAdapter extends RecyclerView.Adapter<SavedLocationsAdapter.ViewHolder> {

    private final String[] localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final SavedLocationItemBinding binding;

        public ViewHolder(SavedLocationItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(String locationName) {
            binding.locationNameTextView.setText(locationName);
        }
    }

    public SavedLocationsAdapter(String[] dataSet) {
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        SavedLocationItemBinding binding = SavedLocationItemBinding.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                viewGroup,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        viewHolder.bind(localDataSet[position]);
    }

    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}
