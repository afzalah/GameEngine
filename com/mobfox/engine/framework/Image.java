package com.mobfox.engine.framework;

import com.mobfox.engine.framework.Graphics.ImageFormat;

public interface Image {
    public int getWidth();

    public int getHeight();

    public ImageFormat getFormat();

    public void dispose();
}
