package com.boing.tsur.boing;

import android.content.Intent;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * This Activity is in charge of the layout.
 * It updates the sensor service every time the sensitivity is modified.
 */
public class MainActivity extends AppCompatActivity {

    private Intent boingServiceIntent;
    private PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
        setContentView(R.layout.activity_main);
        initSeekBar();
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        // Lock is needed to let the accelerometer work when device screen is off
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "BoingWakeLock");
        acquireLock();
        boingServiceIntent = new Intent(this, SensorService.class);
        boingServiceIntent.putExtra("sensitivity", 18.0);
        startService(boingServiceIntent);
    }

    private void initSeekBar() {
        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        final TextView seekBarValue = (TextView)findViewById(R.id.seekbarText);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            double sensitivity;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue.setText(String.valueOf(progress));
                // Sensitivity goes from 25 to 15, which maps from 0%(25) to 100%(15)
                sensitivity = (progress*-0.1)+25;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Restart service with a new sensitivity value
                stopService(boingServiceIntent);
                boingServiceIntent.putExtra("sensitivity", sensitivity);
                startService(boingServiceIntent);
            }
        });
    }

    private void hideActionBar(){
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
    }

    private void acquireLock(){
        try {
            if(wakeLock != null){
                wakeLock.acquire();
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void releaseLock(){
        try {
            if(wakeLock != null){
                wakeLock.release();
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        releaseLock();
    }

    @Override
    public void onResume() {
        super.onResume();
        acquireLock();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
