package com.mobfox.engine.framework;

import java.util.List;

public interface TiltingSensor {
    public static class TiltEvent {
        public static final int TILT_HORIZONTAL = 0;
        public static final int TILT_VERTICAL = 1;
        public static final int TILT_PERPENDICULAR = 2;

        public int type;
        public float x, y, z;
        private long lastUpdate;
    }

    /* Tilting Events */
    public boolean isTilting(int sensor);

    public int getTiltingHorizontal(int sensor);

    public int getTiltingVertical(int sensor);

    public int getTiltingPerpendicular(int sensor);

    public long getLastUpdate(int sensor);

    public List<TiltEvent> getTiltingEvents();
}
