package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class SeleniumTest {

    protected WebDriver driver;

    @BeforeEach
    void setupDriver() throws URISyntaxException, MalformedURLException {
        driver = new EdgeDriver();
//        driver = new RemoteWebDriver(new URI("https://localhost:4444").toURL(), new FirefoxOptions());
    }

    @AfterEach
    void teardownDriver() {
        driver.close();
        driver.quit();
    }
}
