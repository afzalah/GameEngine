package com.mobfox.engine.framework;

import java.util.List;

public interface Input {
    public static class TouchEvent {
        public static final int TOUCH_DOWN = 0;
        public static final int TOUCH_UP = 1;
        public static final int TOUCH_DRAGGED = 2;
        public static final int TOUCH_HOLD = 3;

        public int type;
        public int x, y;
        public int pointer;
    }

    public static class TiltEvent {
        public static final int TILT_HORIZONTAL = 0;
        public static final int TILT_VERTICAL = 1;
        public static final int TILT_PERPENDICULAR = 2;

        public int type;
        public float x, y, z;
        private long lastUpdate;
    }


    /* Screen Touch Events */

    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);

    public int getTouchY(int pointer);

    public List<TouchEvent> getTouchEvents();


    /* Tilting Events */
    public boolean isTilting(int sensor);

    public int getTiltingHorizontal(int sensor);

    public int getTiltingVertical(int sensor);

    public int getTiltingPerpendicular(int sensor);

    public long getLastUpdate(int sensor);

    public List<TiltEvent> getTiltingEvents();
}
