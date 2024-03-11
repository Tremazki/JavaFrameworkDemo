package org.example.selenium;

import org.example.selenium.driver.WebDriverFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.Duration;

public class DriverUtilities {

    /**
     * Create and set up a default WebDriver instance and return it to the calling class for usage in a test.
     *
     * @return WebDriver instance
     * @throws MalformedURLException
     * @throws URISyntaxException
     */
    public static WebDriver createDefaultDriver() throws MalformedURLException, URISyntaxException {
        WebDriver driver = new WebDriverFactory().create();
        initializeDefaults(driver);
        ScreenshotUtilities.setDriver(driver);
        return driver;
    }

    /**
     * Initialize the default behaviour of the driver instance
     * @param driver WebDriver instance
     */
    private static void initializeDefaults(WebDriver driver) {
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
}
