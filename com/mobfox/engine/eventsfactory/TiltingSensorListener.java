package com.mobfox.engine.eventsfactory;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import com.mobfox.engine.framework.Pool;
import com.mobfox.engine.framework.TiltingSensor.TiltEvent;

import java.util.ArrayList;
import java.util.List;

public class TiltingSensorListener implements TiltListener {
    private SensorManager sensorManager;
    boolean isTilting;
    float tiltX, tiltY, tiltZ;
    long lastUpdate;
    Pool<TiltEvent> tiltEventPool;
    List<TiltEvent> tiltEvents = new ArrayList<TiltEvent>();
    List<TiltEvent> tiltEventsBuffer = new ArrayList<TiltEvent>();

    public TiltingSensorListener(SensorManager sensorManager) {
        this.sensorManager = sensorManager;
        Pool.PoolObjectFactory<TiltEvent> factory = new Pool.PoolObjectFactory<TiltEvent>() {
            @Override
            public TiltEvent createObject() {
                return new TiltEvent();
            }
        };
        tiltEventPool = new Pool<TiltEvent>(factory, 100);
    }

    public void registerListener() {
        synchronized (this) {
            sensorManager.registerListener(this,
                    sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void unregisterListener() {
        synchronized (this) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public boolean isTilting(int pointer) {
        synchronized (this) {
            return isTilting;
        }
    }

    @Override
    public float getTiltingHorizontal(int pointer) {
        synchronized (this) {
            return tiltX;
        }
    }

    @Override
    public float getTiltingVertical(int pointer) {
        synchronized (this) {
            return tiltY;
        }
    }

    @Override
    public float getTiltingPerpendicular(int pointer) {
        synchronized (this) {
            return tiltZ;
        }
    }

    @Override
    public long getLastUpdateTime(int pointer) {
        synchronized (this) {
            return lastUpdate;
        }
    }


    @Override
    public List<TiltEvent> getTiltingEvents() {
        synchronized (this) {
            int len = tiltEvents.size();
            for (int i = 0; i < len; i++)
                tiltEventPool.free(tiltEvents.get(i));
            tiltEvents.clear();
            tiltEvents.addAll(tiltEventsBuffer);
            tiltEventsBuffer.clear();
            return tiltEvents;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                this.lastUpdate = event.timestamp;

                float[] values = event.values;

                if (values.length >= 3) {
                    this.isTilting = (
                            values[TiltEvent.TILT_HORIZONTAL] != tiltX ||
                                    values[TiltEvent.TILT_VERTICAL] != tiltY ||
                                    values[TiltEvent.TILT_PERPENDICULAR] != tiltZ
                    );

                    this.tiltX = values[TiltEvent.TILT_HORIZONTAL];
                    this.tiltY = values[TiltEvent.TILT_VERTICAL];
                    this.tiltZ = values[TiltEvent.TILT_PERPENDICULAR];
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
