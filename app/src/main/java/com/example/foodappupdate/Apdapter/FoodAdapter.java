package com.example.foodappupdate.Apdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodappupdate.Entity.Food;
import com.example.foodappupdate.R;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private List<Food> foodList;
    public FoodAdapter(List<Food> foodList){
            this.foodList = foodList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.tv_Title.setText(food.getTitle());
        holder.iv_Image.setImageResource(food.getImgResId());
        holder.tv_Description.setText(food.getDescription());
        holder.tv_Price.setText(String.valueOf(food.getPrice()));
    }

    @Override
    public int getItemCount() {
       return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_Title;
        public ImageView iv_Image;
        public TextView tv_Price;
        public TextView tv_Description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Title = itemView.findViewById(R.id.tv_title);
            iv_Image = itemView.findViewById(R.id.iv_title);
            tv_Price = itemView.findViewById(R.id.tv_price);
            tv_Description = itemView.findViewById(R.id.tv_description);
        }
    }
}
