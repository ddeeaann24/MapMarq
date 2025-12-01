package com.example.mapmarq_draft1.ui.saved_locations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import android.app.AlertDialog;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.mapmarq_draft1.databinding.FragmentSavedLocationsBinding;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mapmarq_draft1.R;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SavedLocationsFragment extends Fragment {

    private FragmentSavedLocationsBinding binding;
    private SavedLocationsViewModel savedLocationsViewModel;
    private SavedLocationsAdapter savedLocationsAdapter;

    private final String[] laluRooms = {"Select a room",
            "108", "114", "130", "136", "140", "154", "160", "166",
            "172", "175", "180", "184", "188", "192", "202"};
    private final String[] cudahyRooms = {"Select a room",
            "101", "108", "114", "118", "120", "126", "128", "131",
            "137", "143", "145", "151"};
    private final String[] buildings = {"Lalumiere", "Cudahy"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        savedLocationsViewModel = new ViewModelProvider(this).get(SavedLocationsViewModel.class);
        binding = FragmentSavedLocationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.recyclerViewSavedLocations;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        savedLocationsAdapter = new SavedLocationsAdapter();

        // Set the new adapter on the RecyclerView
        recyclerView.setAdapter(savedLocationsAdapter);

        savedLocationsViewModel.getSavedLocations().observe(getViewLifecycleOwner(), locations -> {
            savedLocationsAdapter.setLocations(locations);
        });

        Button addButton = binding.buttonAdd;
        addButton.setOnClickListener(v -> showBuildingSelectionDialog());

        Button removeButton = binding.buttonRemove;
        removeButton.setOnClickListener(v -> showRemoveLocationDialog());

        return root;
    }

    private void showRemoveLocationDialog() {
        List<String> currentLocations = savedLocationsViewModel.getSavedLocations().getValue();

        if (currentLocations == null || currentLocations.isEmpty()) {
            Toast.makeText(getContext(), "No locations to remove.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert List to a CharSequence array for the dialog
        final CharSequence[] items = currentLocations.toArray(new CharSequence[0]);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Select a Location to Remove");
        builder.setItems(items, (dialog, which) -> {
            // 'which' is the index of the item that was tapped
            String locationToRemove = (String) items[which];
            savedLocationsViewModel.removeLocation(locationToRemove);
        });
        builder.create().show();
    }

    private void showBuildingSelectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Select a Building")
                .setItems(buildings, (dialog, which) -> {
                    String selectedBuilding = buildings[which];
                    showNumberSelectionDialog(selectedBuilding);
                });
        builder.create().show();
    }

    private void showNumberSelectionDialog(final String building) {
        final String[] rooms = building.equals("Lalumiere") ? laluRooms : cudahyRooms;
        List<String> roomList = new ArrayList<>(Arrays.asList(rooms));
        roomList.remove("Select a room");

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Select a Room Number")
                .setItems(roomList.toArray(new String[0]), (dialog, which) -> {
                    String selectedRoom = roomList.get(which);
                    savedLocationsViewModel.addLocation(building, selectedRoom);
                });
        builder.create().show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

