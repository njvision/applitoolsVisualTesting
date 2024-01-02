package org.visual.testing.config;

import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import org.visual.testing.type.Browser;
import org.visual.testing.type.DeviceEmulation;

import java.util.List;

public class ConfigCustom extends Config {
    private final List<Browser> browser;
    private final List<DeviceEmulation> deviceEmulation;

    public ConfigCustom(String batch, List<Browser> browser, List<DeviceEmulation> deviceEmulation) {
        super(batch);
        this.browser = browser;
        this.deviceEmulation = deviceEmulation;
    }

    public ConfigCustom(String batch, List<Browser> browser, List<DeviceEmulation> deviceEmulation, boolean breakPoint) {
        super(batch);
        this.browser = browser;
        this.deviceEmulation = deviceEmulation;
        configuration.setLayoutBreakpoints(breakPoint);
    }

    public ConfigCustom(List<Browser> browser, List<DeviceEmulation> deviceEmulation) {
        this.browser = browser;
        this.deviceEmulation = deviceEmulation;
    }

    public void addListBrowser() {
        try {
            this.browser.forEach(ob -> configuration.addBrowser(ob.getWidth(), ob.getHeight(), ob.getBrowserType()));
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void addListDeviceEmulation() {
        try {
            this.deviceEmulation.forEach(ob -> configuration.addDeviceEmulation(ob.getDeviceName(), ob.getScreenOrientation()));
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public List<Browser> getBrowser() {
        return browser;
    }

    public List<DeviceEmulation> getDeviceEmulation() {
        return deviceEmulation;
    }
}
