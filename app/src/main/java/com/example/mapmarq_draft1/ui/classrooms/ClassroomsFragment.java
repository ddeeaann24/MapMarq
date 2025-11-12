package com.example.mapmarq_draft1.ui.classrooms;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mapmarq_draft1.R;

import java.util.HashMap;

public class ClassroomsFragment extends Fragment {

    private ImageView floorPlanImage;
    private TextView roomNumberText;
    private Spinner buildingSpinner;

    private final HashMap<Integer, PointF> laluCoordinates = new HashMap<>();
    private final HashMap<Integer, PointF> cudahyCoordinates = new HashMap<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_classrooms, container, false);

        buildingSpinner = root.findViewById(R.id.building_spinner);
        floorPlanImage = root.findViewById(R.id.floor_plan_image);
        roomNumberText = root.findViewById(R.id.room_number_text);

        initializeCoordinates();

        // The setOnTouchListener for dragging has been removed as requested.

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.buildings, R.layout.custom_spinner_item);
        adapter.setDropDownViewResource(R.layout.custom_spinner_item);
        buildingSpinner.setAdapter(adapter);

        buildingSpinner.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                showBuildingSelectionDialog();
            }
            return true;
        });

        return root;
    }

    private void initializeCoordinates() {
        laluCoordinates.put(108, new PointF(874.50726f, 831.60315f));
        laluCoordinates.put(114, new PointF(863.4985f, 634.8004f));
        laluCoordinates.put(130, new PointF(615.5151f, 585.87463f));
        laluCoordinates.put(136, new PointF(306.52484f, 581.8463f));
        laluCoordinates.put(140, new PointF(62.529724f, 600.8893f));
        laluCoordinates.put(154, new PointF(58.50824f, 832.8473f));
        laluCoordinates.put(160, new PointF(58.50824f, 968.85803f));
        laluCoordinates.put(166, new PointF(58.50824f, 1086.8512f));
        laluCoordinates.put(172, new PointF(58.50824f, 1314.8541f));
        laluCoordinates.put(180, new PointF(390.50433f, 1352.8668f));
        laluCoordinates.put(184, new PointF(527.51605f, 1352.8668f));
        laluCoordinates.put(188, new PointF(661.49457f, 1352.8668f));
        laluCoordinates.put(192, new PointF(849.79926f, 1356.8951f));
        laluCoordinates.put(186, new PointF(868.7836f, 1132.9205f));
        laluCoordinates.put(175, new PointF(353.79926f, 972.9596f));

        laluCoordinates.put(202, new PointF(861.97656f, 1041.8701f));
        laluCoordinates.put(204, new PointF(865.9971f, 954.8584f));
        laluCoordinates.put(206, new PointF(865.9971f, 878.90625f));
        laluCoordinates.put(210, new PointF(865.9971f, 756.958f));
        laluCoordinates.put(216, new PointF(838.9707f, 555.9082f));
        laluCoordinates.put(222, new PointF(643.98535f, 551.8799f));
        laluCoordinates.put(232, new PointF(288.98438f, 551.8799f));
        laluCoordinates.put(254, new PointF(207.97168f, 783.8379f));
        laluCoordinates.put(262, new PointF(207.97168f, 1152.832f));
        laluCoordinates.put(272, new PointF(207.97168f, 1365.8203f));
        laluCoordinates.put(280, new PointF(402.00098f, 1392.7734f));
        laluCoordinates.put(288, new PointF(669.001f, 1392.7734f));
        laluCoordinates.put(292, new PointF(863.9863f, 1373.8037f));
        laluCoordinates.put(296, new PointF(863.9863f, 1149.8291f));

        cudahyCoordinates.put(101, new PointF(796.9629f, 673.5537f));
        cudahyCoordinates.put(108, new PointF(605.9658f, 597.5283f));
        cudahyCoordinates.put(114, new PointF(391.9629f, 597.5283f));
        cudahyCoordinates.put(118, new PointF(204.9541f, 608.51465f));
        cudahyCoordinates.put(120, new PointF(204.9541f, 813.51953f));
        cudahyCoordinates.put(126, new PointF(197.9336f, 1155.4873f));
        cudahyCoordinates.put(128, new PointF(201.92188f, 1341.4492f));
        cudahyCoordinates.put(131, new PointF(362.92676f, 1360.419f));
        cudahyCoordinates.put(137, new PointF(511.90137f, 1356.4639f));
        cudahyCoordinates.put(143, new PointF(648.9121f, 1352.4355f));
        cudahyCoordinates.put(145, new PointF(792.91016f, 1345.4775f));
        cudahyCoordinates.put(151, new PointF(792.91016f, 1166.4736f));
    }

    private void showBuildingSelectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.BiggerDialog);
        builder.setTitle("Select a building");
        builder.setItems(R.array.buildings, (dialog, which) -> {
            buildingSpinner.setSelection(which);
            if (which == 0) { // Lalumiere
                showNumberSelectionDialog(R.array.lalumiere_rooms, "Lalumiere");
            } else if (which == 1) { // Cudahy
                showNumberSelectionDialog(R.array.cudahy_rooms, "Cudahy");
            }
        });
        builder.show();
    }

    private void showNumberSelectionDialog(int itemsId, String building) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.BiggerDialog);
        builder.setTitle("Select a room");
        builder.setItems(itemsId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String[] rooms = getResources().getStringArray(itemsId);
                int selectedRoom = Integer.parseInt(rooms[which]);

                if (building.equals("Lalumiere")) {
                    if (selectedRoom >= 200) {
                        floorPlanImage.setImageResource(R.drawable.lalu_ground);
                    } else {
                        floorPlanImage.setImageResource(R.drawable.lalu_basement);
                    }
                } else { // Cudahy
                    floorPlanImage.setImageResource(R.drawable.cudahy_ground);
                }
                roomNumberText.setText(String.valueOf(selectedRoom));
                floorPlanImage.setVisibility(View.VISIBLE);
                roomNumberText.setVisibility(View.VISIBLE);


                // --- Position the box using the stored coordinates ---
                PointF coords = null;
                if (building.equals("Lalumiere")) {
                    coords = laluCoordinates.get(selectedRoom);
                } else { // Cudahy
                    coords = cudahyCoordinates.get(selectedRoom);
                }

                final PointF finalCoords = coords;
                roomNumberText.post(() -> {
                    if (finalCoords != null) {
                        // If coordinates are found, use them.
                        roomNumberText.setX(finalCoords.x);
                        roomNumberText.setY(finalCoords.y);
                    } else {
                        // Otherwise, hide the box and log a warning.
                        roomNumberText.setVisibility(View.GONE);
                        Log.w("ClassroomsFragment", "No coordinates found for room " + selectedRoom + ". The red box will be hidden.");
                    }
                });
            }
        });
        builder.show();
    }
}
