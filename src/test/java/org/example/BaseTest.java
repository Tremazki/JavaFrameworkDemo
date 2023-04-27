package org.example;

import org.example.utilities.ScreenshotUtilities;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    void setupDriver() throws MalformedURLException {
//        driver = new RemoteWebDriver(new URL("http://localhost:4444"), new FirefoxOptions());
        EdgeOptions options = new EdgeOptions();
        options.addExtensions(new File("./bin/1.49.2_0.crx"));
        driver = new EdgeDriver(options);
    }

    @AfterEach
    void teardownDriver() throws IOException {
        ScreenshotUtilities.takeScreenShot(driver);
        driver.quit();
    }
}
