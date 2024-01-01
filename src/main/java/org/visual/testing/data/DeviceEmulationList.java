package org.visual.testing.data;

import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import org.visual.testing.type.DeviceEmulation;

import java.util.ArrayList;
import java.util.List;

public class DeviceEmulationList {
    private final List<DeviceEmulation> list;

    public DeviceEmulationList() {
        this.list = createDeviceEmulationList();
    }

    private List<DeviceEmulation> createDeviceEmulationList() {
        List<DeviceEmulation> deviceEmulationList = new ArrayList<>();
        deviceEmulationList.add(new DeviceEmulation(DeviceName.Galaxy_Note_3, ScreenOrientation.PORTRAIT));
        deviceEmulationList.add(new DeviceEmulation(DeviceName.Galaxy_Note_3, ScreenOrientation.LANDSCAPE));
        return deviceEmulationList;
    }

    public List<DeviceEmulation> getList() {
        return list;
    }
}
