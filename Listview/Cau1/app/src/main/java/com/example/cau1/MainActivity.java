package com.example.cau1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.BreakIterator;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final int[] save = {-1};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv_Selection = (TextView)findViewById(R.id.tvSelection);
        tv_Selection.setText("position: 0;value=Teo");
        final ListView lvPerson = (ListView)findViewById(R.id.lv_person);
        final String arr[] = {"Teo", "Ty", "Bin", "Bo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, arr);
        lvPerson.setAdapter(adapter);
        lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                TextView tvSelection = (TextView)findViewById(R.id.tvSelection);
                tvSelection.setText("position:" + arg2 + ";value=" + arr[arg2]);

                lvPerson.getChildAt(arg2).setBackgroundColor(Color.BLUE);
                if (save[0] != -1 && save[0] != arg2){
                    lvPerson.getChildAt(save[0]).setBackgroundColor(Color.WHITE);
                }
                save[0] = arg2;
            }
        });
    }

}