package com.example.mapmarq_draft1.ui.buildings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mapmarq_draft1.BuildConfig;
import com.example.mapmarq_draft1.R;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import androidx.preference.PreferenceManager;

public class BuildingsFragment extends Fragment {

    private MapView mapView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_buildings, container, false);

        // Initialize OSMDroid configuration
        Configuration.getInstance().load(
                requireContext(),
                PreferenceManager.getDefaultSharedPreferences(requireContext())
        );
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);

        // Initialize MapView
        mapView = root.findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);

        // Set initial location and zoom
        GeoPoint startPoint = new GeoPoint(43.038721, -87.929846);
        mapView.getController().setZoom(18.0f);
        mapView.getController().setCenter(startPoint);

        // Set bounding box and zoom limits
        BoundingBox boundingBox = new BoundingBox(
                43.043,
                -87.927,
                43.036,
                -87.933
        );
        mapView.setScrollableAreaLimitDouble(boundingBox);
        mapView.setMinZoomLevel(17.0);
        mapView.setMaxZoomLevel(20.0);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mapView != null) {
            mapView.onPause();
        }
    }
}
