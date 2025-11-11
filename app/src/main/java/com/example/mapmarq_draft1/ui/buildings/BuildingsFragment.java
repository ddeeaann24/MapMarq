package com.example.mapmarq_draft1.ui.buildings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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

import java.util.HashMap;


public class BuildingsFragment extends Fragment {

    private MapView mapView;

    public HashMap<String, GeoPoint> buildingsWithCoords = new HashMap<String, GeoPoint>(){{
        put("Cudahy Hall", new GeoPoint(43.038551, -87.928963));
        put("Jesu Church", new GeoPoint(43.038246, -87.927347));
        put("Olin Engineering Hall", new GeoPoint(43.038330, -87.933599));
    }};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_buildings, container, false);

        Spinner dropDownMenu = root.findViewById(R.id.spinner);

        String[] buildings =  new String[] {"Cudahy Hall", "Jesu Church", "Olin Engineering Hall"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, buildings);

        dropDownMenu.setAdapter(adapter);

        dropDownMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
               String selection = parent.getItemAtPosition(position).toString();
               mapView.getController().animateTo(buildingsWithCoords.get(selection));
           }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });


        mapView = root.findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);

        GeoPoint startPoint = new GeoPoint(43.038721, -87.929846);
        mapView.getController().setZoom(18.0f);
        mapView.getController().setCenter(startPoint);

        BoundingBox boundingBox = new BoundingBox(
                43.043,
                -87.927,
                43.036,
                -87.933
        );
        mapView.setScrollableAreaLimitDouble(boundingBox);
        mapView.setMinZoomLevel(17.0);
        mapView.setMaxZoomLevel(20.0);

        MyLocationNewOverlay locationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(requireContext()), mapView);
        locationOverlay.enableMyLocation();
        locationOverlay.enableFollowLocation();
        mapView.getOverlays().add(locationOverlay);

        return root;
    }



}
