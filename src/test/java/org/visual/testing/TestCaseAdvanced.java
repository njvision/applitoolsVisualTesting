package org.visual.testing;

import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.visual.testing.config.Config;
import org.visual.testing.config.ConfigCustom;
import org.visual.testing.data.BrowserList;
import org.visual.testing.data.DeviceEmulationList;

import java.io.IOException;

public class TestCaseAdvanced {
    static WebDriver driver;
    static Config configuration;
    static EyesRunner runner;
    Eyes eyes;

    @BeforeAll
    static void beforeAll() throws IOException {
        driver = WebDriverManager.firefoxdriver().create();
        runner = new ClassicRunner();
        configuration = new Config("Second batch", "Advanced Visual Testing", StitchMode.CSS);
        configuration.setConfig();
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
    public void testCaseFullyMode() {
        driver.get("https://applitools.com/tutorials/");
        eyes.check(Target.window().fully());
    }

    @Test
    public void checkElementByClassName() {
        driver.get("https://applitools.com/tutorials/quickstart/web/selenium/java/junit");
        eyes.check(Target.region(By.cssSelector(".menu")).fully());
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
