package org.example.junit;

import org.example.utilities.driver.impl.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class SeleniumTest extends ExtentReportTest {

    protected WebDriver driver;

    public SeleniumTest() {
        super();
    }

    @BeforeEach
    void setupDriver() throws MalformedURLException, URISyntaxException {
        driver = new WebDriverFactory().create();
    }

    @AfterEach
    void teardownDriver() {
        driver.close();
        driver.quit();
    }
}
