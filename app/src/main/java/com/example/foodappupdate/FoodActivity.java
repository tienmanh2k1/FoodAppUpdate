package com.example.foodappupdate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.example.foodappupdate.Apdapter.FoodAdapter;
import com.example.foodappupdate.Entity.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {

    private List<Food> foodList;
    private FoodAdapter foodAdapter;
    private int longClickPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);


//        buttonDialog = findViewById(R.id.btn_addFood);
//        buttonDialog.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showAddDialog();
//            }
//        });


        RecyclerView foodRecyclerView = findViewById(R.id.foodRecyclerView);
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        foodList = new ArrayList<>();
        foodAdapter = new FoodAdapter(foodList);
        foodRecyclerView.setAdapter(foodAdapter);

        Button button = findViewById(R.id.btn_addFood);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddEditDialog(null);
            }
        });

        registerForContextMenu(foodRecyclerView);
        foodRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(FoodActivity.this, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent event) {
                    View child = foodRecyclerView.findChildViewUnder(event.getX(), event.getY());
                    if (child != null) {
                        longClickPosition = foodRecyclerView.getChildAdapterPosition(child);
                        registerForContextMenu(foodRecyclerView);
                        openContextMenu(foodRecyclerView);
                        unregisterForContextMenu(foodRecyclerView);
                    }
                }
            });

            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);
                    showAddEditDialog(foodList.get(position));
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


        for (int i = 0; i < 5; i++) {
            Food food = new Food("Food" + i, "abcbcbcbcbc" + i, 100 + 1, R.drawable.img_pib);
            foodList.add(food);
        }


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_delete) {
            foodList.remove(longClickPosition);
            foodAdapter.notifyDataSetChanged();
            return true;
        }
        return super.onContextItemSelected(item);
    }


//    private void showAddDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        View view = getLayoutInflater().inflate(R.layout.activity_item, null);
//        builder.setView(view);
//
//        final EditText et_title = view.findViewById(R.id.et_title);
//        final EditText et_price = view.findViewById(R.id.et_price);
//        final EditText et_description = view.findViewById(R.id.et_description);
//
//
//        builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String title = et_title.getText().toString();
//                float price = Float.parseFloat(et_price.getText().toString());
//                String description = et_description.getText().toString();
//
//                Food food = new Food(title, description, price, R.drawable.img_pib);
//                foodList.add(food);
//                foodAdapter.notifyDataSetChanged();
//            }
//        });
//
//        builder.setNegativeButton("Hủy", null);
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }


    private void showAddEditDialog(Food foodItem) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        AddEditDialogFragment fragment = AddEditDialogFragment.newInstance(foodItem);
        fragment.show(fragmentManager, "add_edit_dialog");
    }


}