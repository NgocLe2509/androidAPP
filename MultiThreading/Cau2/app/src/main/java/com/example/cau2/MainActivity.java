package com.example.cau2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ProgressBar pbWaiting, pbSecond;
    private TextView tvTopCaption;
    private EditText etInput;
    private Button btnExecute;
    private int globalValue, accum;
    private long startTime;
    private final String PATIENCE = "Some important data is being collected now.\nPlease be patient...wait...";
    private Handler handler;
    private Runnable fgRunnable, bgRunnable;
    private Thread testThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewByIds();
        initVariables();

        // Handle onClickListenner
        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbWaiting.setVisibility(View.VISIBLE);
                tvTopCaption.setVisibility(View.VISIBLE);
                pbSecond.setVisibility(View.VISIBLE);
                testThread.start();

                // start thread

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        initBgThread();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void findViewByIds(){
        tvTopCaption = (TextView) findViewById(R.id.tv_top_caption);
        pbWaiting = (ProgressBar) findViewById(R.id.pb_waiting);
        etInput = (EditText) findViewById(R.id.et_input);
        btnExecute = (Button) findViewById(R.id.btn_execute);
        pbSecond = (ProgressBar) findViewById(R.id.pbSecond);
    }

    private void initVariables(){
        globalValue = 0;
        accum = 0;
        startTime = System.currentTimeMillis();
        pbWaiting.setMax(20);
        pbWaiting.setProgress(0);
        pbWaiting.setVisibility(View.GONE);
        pbSecond.setVisibility(View.GONE);
        handler = new Handler();
        initfgRunnable();
    }

    private void initfgRunnable(){

        fgRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    // Cakculate new value
                    int progressStep = 5;
                    double totalTime = (System.currentTimeMillis() - startTime) /1000;
                    synchronized (this){
                        globalValue += 100;
                    }

                    // Update UI
                    pbSecond.setVisibility(View.GONE);
                    pbWaiting.incrementProgressBy(progressStep);
                    accum += progressStep;
                    tvTopCaption.setText(PATIENCE + totalTime + " - " + globalValue);

                    // Check to stop
                    if(pbWaiting.getProgress() == 20){
                        tvTopCaption.setText(getString((R.string.bg_work_is_over)));
                        pbWaiting.setVisibility(View.GONE);
                        String input = etInput.getText().toString();
                        Toast.makeText(MainActivity.this, "You said: " + input, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("fgRunnable", e.getMessage());
                }
            }
        };
    }
    private void  initBgThread(){
        bgRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i=0; i<20; i++){
                        Thread.sleep(1000);
                        synchronized (this){
                            globalValue += 1;
                        }

                        handler.post(fgRunnable);


                    }
                } catch (Exception e) {
                    Log.e("bgRunnable", e.getMessage());
                }
            }
        };
        testThread = new Thread(bgRunnable);
    }

}