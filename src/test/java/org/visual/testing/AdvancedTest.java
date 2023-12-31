package org.visual.testing;

import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.selenium.fluent.Target;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.visual.testing.config.Config;

import java.io.IOException;

public class AdvancedTest {
    static WebDriver driver;
    static Config configuration;
    static EyesRunner runner;
    Eyes eyes;

    @BeforeAll
    static void beforeAll() throws IOException {
        driver = switchDriver(false);
        runner = new ClassicRunner();
        configuration = new Config("Second batch", "Advanced Visual Testing",
                StitchMode.CSS, "branch");
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

    @Test
    public void testLayoutLevel() {
        driver.get("https://applitools.com/helloworld/?diff1");
        eyes.check(Target.window()
                .content(By.cssSelector("div.section:nth-child(1)"))
                .layout(By.cssSelector("div.section:nth-child(2) > p:nth-child(4)")));
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

    private static WebDriver switchDriver(boolean flag) {
        if (flag) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless");
            return WebDriverManager.firefoxdriver().capabilities(firefoxOptions).create();
        } else {
            return WebDriverManager.firefoxdriver().create();
        }
    }
}
