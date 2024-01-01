package org.visual.testing.type;

import com.applitools.eyes.selenium.BrowserType;

public class Browser {
    private int width;
    private int height;
    private BrowserType browserType;

    public Browser(int width, int height, BrowserType browserType) {
        this.width = width;
        this.height = height;
        this.browserType = browserType;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BrowserType getBrowserType() {
        return browserType;
    }

    public void setBrowserType(BrowserType browserType) {
        this.browserType = browserType;
    }
}
