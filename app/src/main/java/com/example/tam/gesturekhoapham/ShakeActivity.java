package com.example.tam.gesturekhoapham;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ShakeActivity extends AppCompatActivity {
    SensorManager sensorManager;
    Sensor sensor;
    ShakeDectector shakeDectector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        shakeDectector = new ShakeDectector();
        shakeDectector.setOnShakeListener(new ShakeDectector.OnShakeListener() {
            @Override
            public void onShake(int count) {
                Toast.makeText(ShakeActivity.this, "lắc lắc: "+count, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShow(String result) {
                System.out.println(result);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(shakeDectector,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(shakeDectector);
    }
}
