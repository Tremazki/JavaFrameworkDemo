package org.example.selenium;

import org.example.selenium.capabilities.exceptions.CapabilitiesCreationException;
import org.example.selenium.driver.WebDriverFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class DriverUtilities {

    /**
     * Create and set up a default WebDriver instance and return it to the calling class for usage in a test.
     *
     * @return WebDriver instance
     * @throws MalformedURLException
     * @throws URISyntaxException
     */
    public static WebDriver createDefaultDriver() throws MalformedURLException, URISyntaxException, CapabilitiesCreationException {
        // Create the driver and set up the default resolution
        WebDriver driver = new WebDriverFactory().create();
        driver.manage().window().setSize(new Dimension(1920, 1080));

        // Set up our ScreenshotUtilities class with an internal instance of the driver so the user doesn't have to
        // pass this in every time they wish to take a screenshot of the current state
        ScreenshotUtilities.setDriver(driver);

        return driver;
    }
}
