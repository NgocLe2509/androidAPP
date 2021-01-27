package com.example.cau5;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class DishAdapter extends ArrayAdapter<Dish> {
    private Activity context = null;
    private ArrayList<Dish> objects=null;
    private int layoutId;

    public DishAdapter(@NonNull Activity context, int layoutId, @NonNull ArrayList<Dish> objects) {
        super(context, layoutId, objects);
        this.context = context;
        this.layoutId = layoutId;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutId, null);
        }

        // Get item
        Dish dish = getItem(position);

        // Get view
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        tvName.setSelected(true);
        ImageView ivPromotion = (ImageView) convertView.findViewById(R.id.ivPromotion);
        LinearLayout llItemDish = (LinearLayout) convertView.findViewById(R.id.llItemDish);
        LinearLayout llInfo = (LinearLayout) convertView.findViewById(R.id.llInfo);

        // Set fullname
        if (dish.getName()!=null) {
            tvName.setText(dish.getName());
            llItemDish.setBackgroundResource(dish.getSpinner());
        }
        else tvName.setText("");

        // If this is a manager -> show icon manager. Otherwise, show Staff in tvPosition
        if (dish.isPromotion())
        {
            ivPromotion.setVisibility(View.VISIBLE);
            ivPromotion.setImageResource(R.drawable.ic_promotion);
        }
        else
        {
            ivPromotion.setVisibility(View.GONE);
        }

        return convertView;
    }

}

