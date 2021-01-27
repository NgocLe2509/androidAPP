package com.example.cau6;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerDataAdapter.onClickListener {
    private RecyclerView rc_Person;
    private TextView tv_Selection;
    private EditText et_ID, et_TenNV;
    private CheckBox cb_Manager;
    private Button bt_Add;

    ArrayList<Employee> employees = new ArrayList<Employee>();
    RecyclerDataAdapter adapter =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv_Selection = (TextView)findViewById(R.id.tvSelection);
        rc_Person = (RecyclerView) findViewById(R.id.rc_person);
        et_ID = (EditText) findViewById(R.id.etID);
        et_TenNV = (EditText) findViewById(R.id.etName);
        cb_Manager = (CheckBox) findViewById(R.id.checkManager);
        bt_Add = (Button) findViewById(R.id.btAdd);
        employees = new ArrayList<Employee>();

        adapter = new RecyclerDataAdapter(this, employees, this);
        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rc_Person.setLayoutManager(lm);
        rc_Person.setHasFixedSize(true);
        rc_Person.setAdapter(adapter);

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
    }

    @Override
    public void onClick(int position) {
        employees.get(position);
        TextView tvSelection = (TextView)findViewById(R.id.tvSelection);
        tvSelection.setText("position:" + position + "; id=" + employees.get(position).getID() + "; name=" + employees.get(position).getFullName());
    }
}