package com.example.cau2;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.BreakIterator;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final int[] save = {-1};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv_Selection = (TextView)findViewById(R.id.tvSelection);
        tv_Selection.setText("position:; value:");
        final ListView lvPerson = (ListView)findViewById(R.id.lv_person);
        final ArrayList names = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, names);
        lvPerson.setAdapter(adapter);
        final EditText et_input = (EditText) findViewById(R.id.etinput);
        Button bt_input = (Button) findViewById(R.id.btinput);


        // set onClick button
        bt_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                names.add(et_input.getText());
                et_input.setText("");
                adapter.notifyDataSetChanged();
            }
        });

        // set onClick item
        lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                TextView tvSelection = (TextView)findViewById(R.id.tvSelection);
                tvSelection.setText("position:" + arg2 + "; value=" + names.get(arg2));

                lvPerson.getChildAt(arg2).setBackgroundColor(Color.BLUE);
                if (save[0] != -1 && save[0] != arg2){
                    lvPerson.getChildAt(save[0]).setBackgroundColor(Color.WHITE);
                }
                save[0] = arg2;
            }
        });

        //set on clickLong
        lvPerson.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                names.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }

}