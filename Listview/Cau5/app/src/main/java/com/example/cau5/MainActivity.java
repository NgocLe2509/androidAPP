package com.example.cau5;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cau5.Dish;
import com.example.cau5.DishAdapter;
import com.example.cau5.ListSpinner;
import com.example.cau5.ThumbnailAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ListSpinner> mCountryList;
    private ThumbnailAdapter mAdapter;
    ArrayList<Dish> dish;
    DishAdapter adapter =null;

    private GridView gvThumbnail;
    private EditText etName;
    private CheckBox cb_Promotion;
    private Button bt_Add;
    private Spinner spinnerCountries;

    private int idThumbnailSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        // set spinner
        //
        initList();
        spinnerCountries = findViewById(R.id.spinner_dish);
        mAdapter = new ThumbnailAdapter(this,0, mCountryList);
        spinnerCountries.setAdapter(mAdapter);
        spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ListSpinner clickedItem = (ListSpinner) parent.getItemAtPosition(position);
                String clickedCountryName = clickedItem.get_Dish_Name();
                Toast.makeText(MainActivity.this, clickedCountryName + " selected", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idThumbnailSelect = mCountryList.get(position).get_Dish_Image();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //
        // set GridView
        //
        etName = (EditText) findViewById(R.id.etName);
        cb_Promotion = (CheckBox) findViewById(R.id.cb_Promotion);
        bt_Add = (Button) findViewById(R.id.btAdd);
        gvThumbnail = (GridView) findViewById(R.id.gvThumbnail);

        dish = new ArrayList<Dish>();
        adapter = new DishAdapter(this, R.layout.item_dish, dish);
        gvThumbnail.setAdapter(adapter);

        // set onClick button
        bt_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lấy ra đúng id của Radio Button được checked
                String name = etName.getText().toString();
                boolean chkpromotion = false;
                if (cb_Promotion.isChecked()) {
                    chkpromotion = true;
                }
                else {
                    chkpromotion = false;
                }
                Dish d = new Dish();
                d.setName(name);
                d.setPromotion(chkpromotion);
                d.setSpinner(idThumbnailSelect);
                //Đưa employee vào ArrayList
                dish.add(d);
                adapter.notifyDataSetChanged();
                etName.setText("");
                spinnerCountries.setSelection(0);
                cb_Promotion.setChecked(false);
                Context context = getApplicationContext();
                CharSequence text = "Added successfully";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        gvThumbnail.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dish.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }


    private void initList() {
        mCountryList = new ArrayList<>();
        mCountryList.add(new ListSpinner("Thumbnail 1", R.drawable.first_thumbnail));
        mCountryList.add(new ListSpinner("Thumbnail 2", R.drawable.second_thumbnail));
        mCountryList.add(new ListSpinner("Thumbnail 3", R.drawable.third_thumbnail));
        mCountryList.add(new ListSpinner("Thumbnail 4", R.drawable.fourth_thumbnail));
    }
}
