package org.visual.testing.config;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    protected Configuration configuration;
    private BatchInfo batchInfo;

    public Config(String batch) {
        configuration = new Configuration();
        batchInfo = new BatchInfo(batch);
    }

    public Config(String batch, String sequenceName) {
        configuration = new Configuration();
        batchInfo = new BatchInfo(batch);
        batchInfo.setSequenceName(sequenceName);
    }

    public Config(String batch, String sequenceName, StitchMode mode) {
        configuration = new Configuration();
        batchInfo = new BatchInfo(batch);
        batchInfo.setSequenceName(sequenceName);
        configuration.setStitchMode(mode);
    }

    public Config(String batch, String sequenceName, StitchMode mode, String branchName) {
        configuration = new Configuration();
        batchInfo = new BatchInfo(batch);
        batchInfo.setSequenceName(sequenceName);
        configuration.setStitchMode(mode);
        configuration.setBranchName(branchName);
    }

    public Config(String batch, String sequenceName, StitchMode mode, MatchLevel level) {
        configuration = new Configuration();
        batchInfo = new BatchInfo(batch);
        batchInfo.setSequenceName(sequenceName);
        configuration.setStitchMode(mode);
        configuration.setMatchLevel(level);
    }

    public Config() {
        configuration = new Configuration();
        batchInfo = null;
    }

    public void setConfig() throws IOException {
        if (batchInfo != null) {
            this.configuration.setBatch(batchInfo);
        }
        this.configuration.setApiKey(getApiKey());
    }

    public void addBrowser(int width, int height, BrowserType type) {
        try {
            this.configuration.addBrowser(width, height, type);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void addDeviceEmulation(DeviceName device, ScreenOrientation screen) {
        try {
            this.configuration.addDeviceEmulation(device, screen);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private String getApiKey() throws IOException {
        Properties prop = new Properties();
        try (InputStream file = new FileInputStream("testng-selenium.properties")) {
            prop.load(file);
            return prop.getProperty("APPLITOOLS_API_KEY");
        } catch (IOException e) {
            throw new FileNotFoundException("File is not found");
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public BatchInfo getBatchInfo() {
        return batchInfo;
    }

    public void setBatchInfo(BatchInfo batchInfo) {
        this.batchInfo = batchInfo;
    }
}
