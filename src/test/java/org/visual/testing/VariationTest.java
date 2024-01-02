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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.visual.testing.config.Config;

import java.io.IOException;
import java.util.Random;

public class VariationTest {
    static WebDriver driver;
    static Config configuration;
    static EyesRunner runner;
    Eyes eyes;

    @BeforeAll
    static void beforeAll() throws IOException {
        driver = WebDriverManager.firefoxdriver().create();
        runner = new ClassicRunner();
        configuration = new Config("Third batch", "Variation",
                StitchMode.CSS, "branch2");
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
    public void variationTest() {
        driver.get("https://applitools.com/helloworld/");
        simulateVariation((JavascriptExecutor) driver);
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

    private void simulateVariation(JavascriptExecutor executor) {
        Random random = new Random();
        int result = random.nextInt(3);
        if (result == 1) {
         executor.executeScript("document.querySelector('.demo-page').setAttribute('class', 'demo-page'); document.querySelector('.demo-page').style.texAlign = 'left'");
        } else if (result == 2) {
            executor.executeScript("document.querySelector('div.section:nth-child(1)').style.backgroundColor = 'lightgrey'");
        }
    }
}
