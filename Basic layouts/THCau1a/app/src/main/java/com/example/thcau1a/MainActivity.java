package com.example.thcau1a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private LinearLayout llNameContainer, llAddressContainer, llParentContainer;
    private void createNameContainer(){
        llNameContainer = new LinearLayout(this);
        llNameContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        llNameContainer.setOrientation(LinearLayout.HORIZONTAL);

        TextView tvNameValue = new TextView(this);
        tvNameValue.setText("Name: ");
        llNameContainer.addView(tvNameValue);
    }

    private void createAddressContainer(){
        llAddressContainer = new LinearLayout(this);
        llAddressContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        llAddressContainer.setOrientation(LinearLayout.HORIZONTAL);

        TextView tvAddress = new TextView(this);
        tvAddress.setText("911 Hollywood Blvd");
        llAddressContainer.addView(tvAddress);
    }

    private void createParentContainer(){
        llParentContainer = new LinearLayout(this);
        llAddressContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        llParentContainer.setOrientation(LinearLayout.VERTICAL);

        llParentContainer.addView(llNameContainer);
        llParentContainer.addView(llAddressContainer);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNameContainer();
        createAddressContainer();
        createParentContainer();
        setContentView(llParentContainer);
    }
}
