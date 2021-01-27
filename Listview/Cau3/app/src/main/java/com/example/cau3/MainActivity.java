package com.example.cau3;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
        final ListView lvPerson = (ListView)findViewById(R.id.lv_person);
        final EditText et_MaNV = (EditText) findViewById(R.id.etMaNV);
        final EditText et_TenNV = (EditText) findViewById(R.id.etTenNV);
        final RadioButton rbChinhThuc = (RadioButton) findViewById(R.id.rbChinhThuc);
        RadioButton rbThoiVu = (RadioButton) findViewById(R.id.rbThoiVu);
        CompoundButton.OnCheckedChangeListener listenerRadio = null;
        rbChinhThuc.setOnCheckedChangeListener(listenerRadio);
        rbThoiVu.setOnCheckedChangeListener(listenerRadio);
        Button bt_input = (Button) findViewById(R.id.btinput);

        final ArrayList employees = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, employees);
        lvPerson.setAdapter(adapter);

        // set onClick button
        bt_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lấy ra đúng id của Radio Button được checked
                String id = et_MaNV.getText().toString();
                String name = et_TenNV.getText().toString();
                Employee employee;
                if (rbChinhThuc.isChecked()) {
                    //tạo instance là FullTime
                    employee = new EmployeeFullTime();
                } else {
                    //Tạo instance là Partime
                    employee = new EmployeeParttime();
                }
                //FullTime hay Partime thì cũng là Employee nên có các hàm này là hiển nhiên
                employee.setId(id);
                employee.setName(name);
                //Đưa employee vào ArrayList
                employees.add(employee);
                adapter.notifyDataSetChanged();
            }
        });

        // set onClick item
        lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                TextView tvSelection = (TextView)findViewById(R.id.tvSelection);
                tvSelection.setText("position:" + arg2 + "; value=" + employees.get(arg2));

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
                employees.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}