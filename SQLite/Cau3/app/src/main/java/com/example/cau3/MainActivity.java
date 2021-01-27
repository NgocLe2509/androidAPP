package com.example.cau3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cau3.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactAdapter.onClickListener{
    private RecyclerView rc_Person;
    private EditText etName, etPhone;
    private Button btnAdd;
    private ContactAdapter adapter;
    private DatabaseHandler db;
    private List<Contact> contacts;
    private ArrayList<Contact> contactsArr;
    private int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHandler(this);

        etName = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etPhone);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        Log.d("Insert: ", "Inserting ..");
        rc_Person = (RecyclerView) findViewById(R.id.rc_person);
        contactsArr = new ArrayList<Contact>();

        contacts = db.getAllContacts();
        adapter = new ContactAdapter(this, contactsArr, this);
        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rc_Person.setLayoutManager(lm);
        rc_Person.setHasFixedSize(true);
        rc_Person.setAdapter(adapter);
        for (Contact cn : contacts) {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            Contact ct = new Contact();

            ct.setId(cn.getId());
            ct.setName(cn.getName());
            ct.setPhoneNumber(cn.getPhoneNumber());
            contactsArr.add(ct);
            adapter.notifyDataSetChanged();
            // Writing Contacts to log
            Log.e("Name: ", log);
        }
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addContact(new Contact(etName.getText().toString(), etPhone.getText().toString()));
                Contact ct = new Contact();
                Contact  lastContact= db.getLastContact();
                ct.setId(lastContact.getId());
                ct.setName(lastContact.getName());
                ct.setPhoneNumber(lastContact.getPhoneNumber());
                contactsArr.add(ct);
                adapter.notifyDataSetChanged();
                String log = "Id: " + lastContact.getId() + " ,Name: " + lastContact.getName() + " ,Phone: " + lastContact.getPhoneNumber();
                Log.e("Name: ", log);
                Toast.makeText(MainActivity.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                etName.setText("");
                etPhone.setText("");
            }
        });
        Log.e("Reading: ", "Reading all contacts..");
    }

    @Override
    public void onClick(int position) {
        contactsArr.get(position);
        Toast.makeText(this, "Id: " + contactsArr.get(position).getId() + " ,Name: " + contactsArr.get(position).getName()
                + " ,Phone: " + contactsArr.get(position).getPhoneNumber(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLongItemClick(int position) {
        int p = contactsArr.get(position).getId();
        db.deleteItemContact(p);
        contactsArr.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
    }
}
