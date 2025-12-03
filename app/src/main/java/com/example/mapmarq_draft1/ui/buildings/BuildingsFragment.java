package com.example.mapmarq_draft1.ui.buildings;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mapmarq_draft1.BuildConfig;
import com.example.mapmarq_draft1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class BuildingsFragment extends Fragment {

    private MapView mapView;

    private final Map<String, GeoPoint> buildingsWithCoords = new LinkedHashMap<String, GeoPoint>() {{
        put("Cudahy Hall", new GeoPoint(43.038551, -87.928963));
        put("Gesu Church", new GeoPoint(43.038246, -87.927347));
        put("Olin Engineering Hall", new GeoPoint(43.038330, -87.933599));
        put("Marquette Hall", new GeoPoint(43.038729, -87.913206));
        put("Lalumiere Language Hall", new GeoPoint(43.036274, -87.929143));
        put("O'Brien Hall", new GeoPoint(43.039191, -87.932192));
        put("Raynor Library", new GeoPoint(43.038250, -87.929482));
        put("David A. Straz Jr Hall", new GeoPoint(43.038021, -87.923691));
        put("Wehr Natural Sciences", new GeoPoint(43.0371036, -87.9305444));
    }};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Configuration.getInstance().load(requireContext(), PreferenceManager.getDefaultSharedPreferences(requireContext()));
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);

        View root = inflater.inflate(R.layout.fragment_buildings, container, false);

        Spinner dropDownMenu = root.findViewById(R.id.spinner);

        String[] buildings = buildingsWithCoords.keySet().toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.custom_spinner_item, buildings);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDownMenu.setAdapter(adapter);

        dropDownMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
               String selection = parent.getItemAtPosition(position).toString();
               GeoPoint destination = buildingsWithCoords.get(selection);

               if (destination != null) {
                   mapView.getController().animateTo(destination);
               }
           }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        mapView = root.findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);

        GeoPoint startPoint = new GeoPoint(43.038721, -87.929846);
        mapView.getController().setZoom(20.0f);
        mapView.getController().setCenter(startPoint);

        BoundingBox boundingBox = new BoundingBox(43.043, -87.927, 43.036, -87.933);
        mapView.setScrollableAreaLimitDouble(boundingBox);
        mapView.setMinZoomLevel(17.0);
        mapView.setMaxZoomLevel(20.0);

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mapView != null) mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mapView != null) mapView.onPause();
    }
}
