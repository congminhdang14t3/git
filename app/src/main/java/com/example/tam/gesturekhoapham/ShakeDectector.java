package com.example.tam.gesturekhoapham;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ShakeDectector implements SensorEventListener {
    float SHAKE_THRESHHOLD_GRAVITY = 2.0F;
    int SHAKE_SLOP_TIME_MS = 500;
    int SHAKE_COUNT_RESET_TIME_MS = 3000;

    public OnShakeListener listener;

    public long mShakeTimetamp;
    public int mShakeCount;

    public void setOnShakeListener(OnShakeListener listener){
        this.listener = listener;
    }
    public interface OnShakeListener{
        public void onShake(int count);
        public void onShow(String result);
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(listener != null){
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            float gX = x/ SensorManager.GRAVITY_EARTH;
            float gY = y/ SensorManager.GRAVITY_EARTH;
            float gZ = z/ SensorManager.GRAVITY_EARTH;

            float gForce = (float) Math.sqrt(gX*gX + gY*gY + gZ*gZ);
            if(gForce > SHAKE_THRESHHOLD_GRAVITY){
                listener.onShow("x: "+x+" y: "+y+" z: "+z);
                long now = System.currentTimeMillis();
                if(mShakeTimetamp + SHAKE_SLOP_TIME_MS > now){
                    return;
                }
                if(mShakeTimetamp + SHAKE_COUNT_RESET_TIME_MS < now){
                    mShakeCount=0;
                }
                mShakeTimetamp = now;
                mShakeCount++;
                listener.onShake(mShakeCount);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
