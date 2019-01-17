package com.mobfox.engine.framework;

import android.util.DisplayMetrics;

public interface Game {
    public Audio getAudio();

    public Input getInput();

    public FileIO getFileIO();

    public Graphics getGraphics();

    public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getInitialScreen();

    public DisplayMetrics getDisplayMetrics();
}
