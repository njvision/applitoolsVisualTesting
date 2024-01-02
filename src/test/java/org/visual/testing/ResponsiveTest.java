package org.visual.testing;

import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.services.RunnerOptions;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.visual.testing.config.ConfigCustom;
import org.visual.testing.data.BrowserList;
import org.visual.testing.data.DeviceEmulationList;

import java.io.IOException;

public class ResponsiveTest {
    static WebDriver driver;
    static ConfigCustom configuration;
    static EyesRunner runner;
    Eyes eyes;

    @BeforeAll
    static void beforeAll() throws IOException {
        driver = WebDriverManager.firefoxdriver().create();
        runner = new VisualGridRunner(new RunnerOptions().testConcurrency(5));
        configuration = new ConfigCustom("batch3", new BrowserList().getList(), new DeviceEmulationList().getList(), true);
        configuration.setConfig();
        configuration.addListBrowser();
        configuration.addListDeviceEmulation();
    }

    @BeforeEach
    public void beforeEach(TestInfo testInfo) {
        eyes = new Eyes(runner);
        eyes.setConfiguration(configuration.getConfiguration());
        eyes.open(
                driver,
                "Visual Tests",
                testInfo.getTestMethod().get().getName(),
                new RectangleSize(1000, 600)
        );
    }

    @Test
    public void responsiveDesignTest() {
        driver.get("https://applitools.com/");
        eyes.check(Target.window().layoutBreakpoints(true).lazyLoad());
    }

    @Test
    public void ignoreDisplacement() {
        driver.get("https://applitools.com/helloworld/");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("document.querySelector('div.fancy:nth-child(1)').style.borderBottom = '5px solid grey'");

        eyes.check(Target.window().ignoreDisplacements());
    }

    @AfterEach
    public void afterEach() {
        eyes.closeAsync();
    }

    @AfterAll
    static void afterAll() {
        driver.close();
        System.out.println(runner.getAllTestResults());
    }
}
