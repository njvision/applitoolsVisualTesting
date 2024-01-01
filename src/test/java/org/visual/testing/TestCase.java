package org.visual.testing;

import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.services.RunnerOptions;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.visual.testing.config.ConfigCustom;
import org.visual.testing.data.BrowserList;
import org.visual.testing.data.DeviceEmulationList;

import java.io.IOException;

public class TestCase {
    static WebDriver driver;
    static ConfigCustom configuration;
    static EyesRunner runner;
    Eyes eyes;

    @BeforeAll
    static void beforeAll() throws IOException {
        driver = WebDriverManager.firefoxdriver().create();
        runner = new VisualGridRunner(new RunnerOptions().testConcurrency(5));
        configuration = new ConfigCustom("First batch", new BrowserList().getList(), new DeviceEmulationList().getList());
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
    public void myTestCase() {
        driver.get("https://applitools.com/helloworld/");
        eyes.check(Target.window());
    }

    @Test
    public void myAnotherTestCase() {
        driver.get("https://example.com/");
        eyes.check(Target.window());
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
