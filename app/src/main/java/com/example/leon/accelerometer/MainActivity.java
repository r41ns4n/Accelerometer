package com.example.leon.accelerometer;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView xText, yText, zText;
    private TextView xMaxText, yMaxText, zMaxText;
    private TextView einheit;
    private Sensor accelerometer;
    private SensorManager sensorManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        einheit = (TextView)findViewById(R.id.einheit);
        xText = (TextView)findViewById(R.id.xText);
        yText = (TextView)findViewById(R.id.yText);
        zText = (TextView)findViewById(R.id.zText);
        xMaxText = (TextView)findViewById(R.id.xMaxText);
        yMaxText = (TextView)findViewById(R.id.yMaxText);
        zMaxText = (TextView)findViewById(R.id.zMaxText);

    }

    float now = SensorManager.GRAVITY_EARTH;
    float last = SensorManager.GRAVITY_EARTH;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        xText.setText("X: " + sensorEvent.values[0]);
        yText.setText("Y: " + sensorEvent.values[1]);
        zText.setText("Z: " + sensorEvent.values[2]);

        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];

        last = now;
        now = (float) Math.sqrt(x*x + y*y + z*z);
        float grenzwert = now - last;

        if(grenzwert > 12) {
            Toast.makeText(MainActivity.this, "Schüttel mich nicht", Toast.LENGTH_LONG).show();
            xMaxText.setText("X-Max: " + sensorEvent.values[0]);
            yMaxText.setText("Y-Max: " + sensorEvent.values[1]);
            zMaxText.setText("Z-Max: " + sensorEvent.values[2]);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
