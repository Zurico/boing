//package com.boing.tsur.boing;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//
//public class _MainActivity extends AppCompatActivity {//implements SensorEventListener {
//
////    private SensorManager sensorManager;
////    private Sensor accSensor;
////    private TextView accXTextView;
////    private TextView accYTextView;
////    private TextView accZTextView;
////    private TextView jumpedTextView;
////    private MediaPlayer mp;
//    Intent boingServiceIntent;
////    private long lastUpdate = 0;
////    private double gravity[] = {9.8, 9.8, 9.8};
////    static final float ALPHA = 0.8f;
////    private PowerManager.WakeLock mWakeLock;
//
////    public void appendLog(String text){
////        File sdcard = Environment.getExternalStorageDirectory();
////        File logFile = new File(sdcard, "/jumplog.csv");
////        if (!logFile.exists()) {
////            try {
////                logFile.createNewFile();
////            }
////            catch (IOException e) {
////                // TODO Auto-generated catch block
////                e.printStackTrace();
////            }
////        }
////        try{
////            //BufferedWriter for performance, true to set append to file flag
////            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
////            buf.append(text);
////            buf.newLine();
////            buf.close();
////        }
////        catch (IOException e){
////            e.printStackTrace();
////        }
////    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout._activity_main);
////        accXTextView = (TextView)findViewById(R.id.accSensorX);
////        accYTextView = (TextView)findViewById(R.id.accSensorY);
////        accZTextView = (TextView)findViewById(R.id.accSensorZ);
////        jumpedTextView = (TextView)findViewById(R.id.jumped);
////        mp = MediaPlayer.create(this, R.raw.boing);
//        boingServiceIntent = new Intent(this, SensorService.class);
//        startService(boingServiceIntent);
////        final Button resetButton = (Button) findViewById(R.id.resetButton);
////        resetButton.setOnClickListener(new View.OnClickListener() {
////            public void onClick(View v) {
////                jumpedTextView.setText("No jumped !!");
////                accXTextView.setText("0");
////                accYTextView.setText("0");
////                accZTextView.setText("0");
////            }
////        });
////        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
////        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
////        sensorManager.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_GAME);
//        // Remove this and android:theme="@android:style/Theme.Translucent.NoTitleBar" in
//        // android manifest to use UI again
////        finish();
//    }
//
////    protected float[] lowPass( float[] input, float[] output ) {
////        if ( output == null ) return input;
////
////        for ( int i=0; i<input.length; i++ ) {
////            output[i] = output[i] + ALPHA * (input[i] - output[i]);
////        }
////        return output;
////    }
//
////    @Override
////    public void onSensorChanged(SensorEvent sensorEvent) {
////        Sensor mySensor = sensorEvent.sensor;
////
////        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
////
////            float x = sensorEvent.values[0];
////            float y = sensorEvent.values[1];
////            float z = sensorEvent.values[2];
////
////            gravity[0] = ALPHA * gravity[0] + (1 - ALPHA) * x;
////            gravity[1] = ALPHA * gravity[1] + (1 - ALPHA) * y;
////            gravity[2] = ALPHA * gravity[2] + (1 - ALPHA) * z;
////
////            double x2 = x - gravity[0];
////            double y2 = y - gravity[1];
////            double z2 = z - gravity[2];
//
////            double alpha = Math.sqrt(x*x+y*y+z*z);
////            double alpha2 = Math.sqrt(x2*x2+y2*y2+z2*z2);
////            accXTextView.setText(String.format("%1$.4f", alpha));
////            accYTextView.setText(String.format("%1$.4f", alpha2));
////            accZTextView.setText(String.format("%1$.4f", z));
////            long curTime = System.currentTimeMillis();
////            this.appendLog(String.format(Locale.US, "%1$.4f, %2$.4f, %3$d", alpha, alpha2, curTime));
//
////            if((curTime - lastUpdate) > 500 && alpha2 > 18){
////                jumpedTextView.setText("Jumped!!");
////                startService(boingServiceIntent);
//                //mp.stop();
////                mp.start();
////                accXTextView.setText(String.format("%1$.4f", x));
////                accYTextView.setText(String.format("%1$.4f", y));
////                accZTextView.setText(String.format("%1$.4f", z));
////                lastUpdate = curTime;
////            }
//
////        }
////        // In this example, alpha is calculated as t / (t + dT),
////        // where t is the low-pass filter's time-constant and
////        // dT is the event delivery rate.
////
////        final double alpha = 0.8;
////        final double[] gravity = new float[3]();
////        final double[] linear_acceleration = new float[3]();
////
////        // Isolate the force of gravity with the low-pass filter.
////        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
////        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
////        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];
////
////        // Remove the gravity contribution with the high-pass filter.
////        linear_acceleration[0] = event.values[0] - gravity[0];
////        linear_acceleration[1] = event.values[1] - gravity[1];
////        linear_acceleration[2] = event.values[2] - gravity[2];
////    }
//
////    @Override
////    public void onAccuracyChanged(Sensor sensor, int accuracy) {
////
////    }
//
//    protected void onPause() {
////        try {
////
////            mWakeLock.release();
////
////        }catch(Exception ex){
////        }
//        super.onPause();
////        if(mWakeLock != null && !mWakeLock.isHeld()) mWakeLock.acquire();
////        sensorManager.unregisterListener(this);
//    }
//
//    protected void onStop() {
//        super.onStop();
////        if(mWakeLock != null) mWakeLock.acquire();
////        sensorManager.unregisterListener(this);
//    }
//
//    protected void onStart() {
//        super.onStart();
////        if(mWakeLock != null) mWakeLock.release();
////        sensorManager.unregisterListener(this);
//    }
//
//    protected void onResume() {
////        try {
////            mWakeLock.acquire();
////        } catch (Exception ex) {
////
////        }
//        super.onResume();
////        if(mWakeLock != null && mWakeLock.isHeld()) mWakeLock.release();
////        sensorManager.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_NORMAL);
//    }
//
//    protected void onDestroy(){
//        stopService(boingServiceIntent);
//    }
//
//}
