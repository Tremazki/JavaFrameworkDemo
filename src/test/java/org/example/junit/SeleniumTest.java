package org.example.junit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.utilities.driver.impl.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class SeleniumTest {

    protected Logger    log;
    protected WebDriver driver;

    @BeforeEach
    void setupDriver() throws MalformedURLException, URISyntaxException {
        log    = LogManager.getLogger(getClass());
        driver = new WebDriverFactory().create();
    }

    @AfterEach
    void teardownDriver() {
        driver.close();
        driver.quit();
    }
}
