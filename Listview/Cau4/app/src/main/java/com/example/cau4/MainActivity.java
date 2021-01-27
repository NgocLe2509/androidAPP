package com.example.cau4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.BreakIterator;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvPerson;
    private TextView tv_Selection;
    private EditText et_ID, et_TenNV;
    private CheckBox cb_Manager;
    private Button bt_Add;

    ArrayList<Employee> employees = new ArrayList<Employee>();
    EmployeeAdapter adapter =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv_Selection = (TextView)findViewById(R.id.tvSelection);
        lvPerson = (ListView)findViewById(R.id.lv_person);
        et_ID = (EditText) findViewById(R.id.etID);
        et_TenNV = (EditText) findViewById(R.id.etName);
        cb_Manager = (CheckBox) findViewById(R.id.checkManager);
        bt_Add = (Button) findViewById(R.id.btAdd);
        employees = new ArrayList<Employee>();

        adapter = new EmployeeAdapter(this, R.layout.item_employee, employees);
        lvPerson.setAdapter(adapter);

        // set onClick button
        bt_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lấy ra đúng id của Radio Button được checked
                String id = et_ID.getText().toString();
                String name = et_TenNV.getText().toString();
                boolean chkmanager = false;
                if (cb_Manager.isChecked()) {
                   chkmanager = true;
                }
                else {
                    chkmanager = false;
                }
                Employee em = new Employee();
                em.setID(id);
                em.setFullName(name);
                em.setManager(chkmanager);
                //Đưa employee vào ArrayList
                employees.add(em);
                adapter.notifyDataSetChanged();
                et_ID.setText("");
                et_TenNV.setText("");
                cb_Manager.setChecked(false);
            }
        });

        // set onClick item
        lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                TextView tvSelection = (TextView)findViewById(R.id.tvSelection);
                tvSelection.setText("position:" + arg2 + "; id=" + employees.get(arg2).getID() + "; name=" + employees.get(arg2).getFullName());
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