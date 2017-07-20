package com.boing.tsur.boing;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

/**
 * Jump Detection Based on Accelerometer x, y, z axes
 * It applies a low pass filter to remove the effect of gravity
 * (reads 0 m/s2 when no acceleration at all)
 */

public class SensorService extends Service implements SensorEventListener {

    private long lastUpdate = 0;
    private double gravity[] = {9.8, 9.8, 9.8};
    static final float ALPHA = 0.8f;
    private SensorManager sensorManager;
    private Intent soundServiceIntent;
    private double sensitivity;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_GAME);
        soundServiceIntent = new Intent(this, SoundService.class);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sensitivity = intent.getDoubleExtra("sensitivity", 18);
        return START_STICKY;
    }

    public void onDestroy() {
        sensorManager.unregisterListener(this);
        stopService(soundServiceIntent);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // Read sensor data (with gravity)
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            // Recalculate gravity (it gets better as time goes)
            gravity[0] = ALPHA * gravity[0] + (1 - ALPHA) * x;
            gravity[1] = ALPHA * gravity[1] + (1 - ALPHA) * y;
            gravity[2] = ALPHA * gravity[2] + (1 - ALPHA) * z;
            // Remove gravity effect
            double x2 = x - gravity[0];
            double y2 = y - gravity[1];
            double z2 = z - gravity[2];
            // Normalize data
            double alpha = Math.sqrt(x2*x2+y2*y2+z2*z2);
            long curTime = System.currentTimeMillis();
            // Run every 500ms and if acceleration is higher than sensitivity
            if((curTime - lastUpdate) > 500 && alpha > sensitivity){
                startService(soundServiceIntent);
                lastUpdate = curTime;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

}