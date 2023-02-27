package com.example.foodappupdate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.example.foodappupdate.Apdapter.FoodAdapter;
import com.example.foodappupdate.Entity.Food;

import java.util.List;

public class AddEditDialogFragment extends DialogFragment {

    Food foodItem;
    private List<Food> foodList;
    private FoodAdapter foodAdapter;
    private EditText titleEditText;
    private EditText priceEditText;
    private EditText descriptionEditText;

    public AddEditDialogFragment() {
    }

    public static AddEditDialogFragment newInstance(Food food) {
        AddEditDialogFragment fragment = new AddEditDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable("foodItem", (Parcelable) food);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            foodItem = getArguments().getParcelable("foodItem");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.activity_item, null);
        titleEditText = view.findViewById(R.id.et_title);
        priceEditText = view.findViewById(R.id.et_price);
        descriptionEditText = view.findViewById(R.id.et_description);
        if (foodItem != null) {
            titleEditText.setText(foodItem.getTitle());
            priceEditText.setText(String.valueOf(foodItem.getPrice()));
            descriptionEditText.setText(foodItem.getDescription());
        }
        builder.setView(view).setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String title = titleEditText.getText().toString();
                float price = Float.parseFloat(priceEditText.getText().toString());
                String description = descriptionEditText.getText().toString();
                if (foodItem == null) {
                    foodList.add(new Food(title, description, price, R.drawable.img_pib));
                } else {
                    int index = foodList.indexOf(foodItem);
                    foodList.set(index, new Food(title, description, price, R.drawable.img_pib));
                    foodAdapter.notifyDataSetChanged();
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }
}
