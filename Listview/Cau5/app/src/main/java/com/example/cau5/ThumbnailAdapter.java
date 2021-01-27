package com.example.cau5;

import android.app.Activity;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;

        import java.util.ArrayList;
        import java.util.List;

public class ThumbnailAdapter extends ArrayAdapter<ListSpinner> {
    private Activity context;

    public ThumbnailAdapter(Activity context, int layoutID, List<ListSpinner> objects) {
        super(context, layoutID, objects);
        this.context = context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }
    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_thumbnail, parent, false
            );
        }
        ImageView imageViewFlag = convertView.findViewById(R.id.dish_image);
        TextView textViewName = convertView.findViewById(R.id.dish_name);
        ListSpinner currentItem = getItem(position);
        if (currentItem != null) {
            imageViewFlag.setImageResource(currentItem.get_Dish_Image());
            textViewName.setText(currentItem.get_Dish_Name());
        }
        return convertView;
    }
}
