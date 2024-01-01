package org.visual.testing.type;

import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;

public class DeviceEmulation {
    private DeviceName deviceName;
    private ScreenOrientation screenOrientation;

    public DeviceEmulation(DeviceName deviceName, ScreenOrientation screenOrientation) {
        this.deviceName = deviceName;
        this.screenOrientation = screenOrientation;
    }

    public DeviceName getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(DeviceName deviceName) {
        this.deviceName = deviceName;
    }

    public ScreenOrientation getScreenOrientation() {
        return screenOrientation;
    }

    public void setScreenOrientation(ScreenOrientation screenOrientation) {
        this.screenOrientation = screenOrientation;
    }
}
