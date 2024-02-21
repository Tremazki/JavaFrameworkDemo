package org.example.junit;

import org.example.selenium.ScreenshotUtilities;
import org.example.selenium.driver.impl.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class SeleniumTest extends ReportedTest {

    protected WebDriver driver;

    public SeleniumTest() {
        super();
    }

    @BeforeEach
    void setupDriver() throws Throwable {
        driver = new WebDriverFactory().create();
        driver.manage().window().setSize(new Dimension(1920, 1080));

        // Set this up once, that way it's available through the test and callable without specifying a driver
        ScreenshotUtilities.setDriver(driver);
    }

    @AfterEach
    void teardownDriver() {
        driver.close();
        driver.quit();
    }
}
