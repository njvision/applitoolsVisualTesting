package org.visual.testing;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.RunnerOptions;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestCase {
    static WebDriver driver;
    static BatchInfo batchInfo;
    static Configuration configuration;
    static EyesRunner runner;
    Eyes eyes;

    @BeforeAll
    static void beforeAll() throws IOException {
        driver = WebDriverManager.firefoxdriver().create();
        batchInfo = new BatchInfo("First batch");
        runner = new VisualGridRunner(new RunnerOptions().testConcurrency(5));
        configuration = new Configuration();
        Properties prop = new Properties();
        InputStream file = new FileInputStream("testng-selenium.properties");
        prop.load(file);
        configuration.setApiKey(prop.getProperty("APPLITOOLS_API_KEY"));
        file.close();
        configuration.setBatch(batchInfo);
        configuration.addBrowser(1600, 1200, BrowserType.FIREFOX);
        configuration.addBrowser(1400, 1000, BrowserType.EDGE_CHROMIUM);
        configuration.addBrowser(1600, 1200, BrowserType.SAFARI);

        configuration.addDeviceEmulation(DeviceName.Galaxy_Note_3, ScreenOrientation.PORTRAIT);
        configuration.addDeviceEmulation(DeviceName.Galaxy_Note_3, ScreenOrientation.LANDSCAPE);
    }

    @BeforeEach
    public void beforeEach(TestInfo testInfo) {
        eyes = new Eyes(runner);
        eyes.setConfiguration(configuration);
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
