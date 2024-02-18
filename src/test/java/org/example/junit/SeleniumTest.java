package org.example.junit;

import org.example.selenium.driver.impl.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class SeleniumTest extends ReportedTest {

    protected WebDriver driver;

    public SeleniumTest() {
        super();
    }

    @BeforeEach
    void setupDriver() throws MalformedURLException, URISyntaxException {
        driver = new WebDriverFactory().create();
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    @AfterEach
    void teardownDriver() {
        driver.close();
        driver.quit();
    }
}
