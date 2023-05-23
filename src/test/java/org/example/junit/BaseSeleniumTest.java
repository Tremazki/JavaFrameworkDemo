package org.example.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class BaseSeleniumTest {

    protected WebDriver driver;


    @BeforeEach
    void setupDriver() throws URISyntaxException, MalformedURLException {
        driver = new RemoteWebDriver(new URI("https://localhost:4444").toURL(), getDriverOptions());
    }

    @AfterEach
    void teardownDriver() {
        driver.close();
        driver.quit();
    }

    private MutableCapabilities getDriverOptions() {
        switch(System.getProperty("browser")) {
            case "chrome":
                return new ChromeOptions();
            case "edge":
                return new EdgeOptions();
            case "firefox":
            default:
                return new FirefoxOptions();
        }
    }
}
