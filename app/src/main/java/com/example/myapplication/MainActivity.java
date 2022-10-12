package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    //STATES
    private SensorManager sensorManager;
    Sensor accelerometer;
    TextView valueX;
    TextView valueY;
    TextView valueZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //SKAPAR APPEN HÄR
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //HÄMTAR TEXTVIEW VIA ''ID'' OCH LAGRAR DOM I VARIABLER
        valueX = (TextView) findViewById(R.id.valueX);
        valueY = (TextView) findViewById(R.id.valueY);
        valueZ = (TextView) findViewById(R.id.valueZ);

        //SKAPAR EN SENSOR
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Log.d("MainActivity", "OnCreate sensor");

        //HÄMTAR ACCELEROMETERN FRÅN SENSORKLASSEN
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //HÄMTAR EN LISTENER SOM KOLLAR IGENOM MINA METODER
        sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d("MainActivity", "OnCreate: registered accelerometer");

    }


    //DENNA METOD LOGGAR VÄRDET AV MINA TRE VÄRDEN -> X,Z & Y (SENSORN) OCH ÄNDRAR TEXTEN I APPEN TILL VAD VÄRDET ÄR BEROENDE PÅ ROTATION
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Log.d("MainActivity", "X: " + sensorEvent.values[0] + " Y: " + sensorEvent.values[1] + " Z: " + sensorEvent.values[2]);

        valueX.setText("X-Value: " + sensorEvent.values[0]);
        valueY.setText("Y-Value: " + sensorEvent.values[1]);
        valueZ.setText("Z-Value: " + sensorEvent.values[2]);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}