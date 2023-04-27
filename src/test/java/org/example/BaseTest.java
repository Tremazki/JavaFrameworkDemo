package org.example;

import org.example.utilities.ScreenshotUtilities;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    void setupDriver() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL("http://localhost:4444"), new FirefoxOptions());
    }

    @AfterEach
    void teardownDriver() throws IOException {
        ScreenshotUtilities.takeScreenShot(driver);
        driver.quit();
    }
}
