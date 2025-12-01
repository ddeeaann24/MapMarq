package com.example.mapmarq_draft1.ui.saved_locations;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mapmarq_draft1.R;

import java.util.ArrayList;
import java.util.List;

public class SavedLocationsAdapter extends RecyclerView.Adapter<SavedLocationsAdapter.LocationViewHolder> {

    private List<String> locations = new ArrayList<>();

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.saved_location_item, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        String location = locations.get(position);
        holder.bind(location);
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    // Method to update the data in the adapter
    public void setLocations(List<String> newLocations) {
        this.locations = newLocations;
        notifyDataSetChanged(); // Notifies the RecyclerView that the data has changed
    }

    // The ViewHolder class
    static class LocationViewHolder extends RecyclerView.ViewHolder {
        private final TextView locationNameTextView;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            locationNameTextView = itemView.findViewById(R.id.textView_location_name);
        }

        public void bind(String locationName) {
            locationNameTextView.setText(locationName);
        }
    }
}
