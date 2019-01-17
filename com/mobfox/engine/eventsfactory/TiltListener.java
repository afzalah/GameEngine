package com.mobfox.engine.eventsfactory;

import android.hardware.SensorEventListener;
import com.mobfox.engine.framework.Input;

import java.util.List;

public interface TiltListener extends SensorEventListener {
    public boolean isTilting(int sensor);

    public float getTiltingHorizontal(int sensor);

    public float getTiltingVertical(int sensor);

    public float getTiltingPerpendicular(int sensor);

    public long getLastUpdateTime(int sensor);

    public List<Input.TiltEvent> getTiltingEvents();
}
