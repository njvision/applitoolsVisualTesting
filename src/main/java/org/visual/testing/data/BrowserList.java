package org.visual.testing.data;

import com.applitools.eyes.selenium.BrowserType;
import org.visual.testing.type.Browser;

import java.util.ArrayList;
import java.util.List;

public class BrowserList {
    private final List<Browser> list;

    public BrowserList() {
        this.list = createBrowserList();
    }

    private List<Browser> createBrowserList() {
        List<Browser> browserList = new ArrayList<>();
        browserList.add(new Browser(1600, 1200, BrowserType.FIREFOX));
        browserList.add(new Browser(1400, 1000, BrowserType.EDGE_CHROMIUM));
        browserList.add(new Browser(1100, 800, BrowserType.SAFARI));
        return browserList;
    }

    public List<Browser> getList() {
        return list;
    }
}
