package com.example.cau1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Cau1_1 extends AppCompatActivity {

    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cau1_1);

        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNewActivity = new Intent(Cau1_1.this, MainActivity.class);
                startActivity(iNewActivity);

                //overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out);
                overridePendingTransition(R.anim.right_in, R.anim.left_in);
            }
        });
    }

}
