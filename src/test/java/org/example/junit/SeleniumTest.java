package org.example.junit;

import org.example.junit.extensions.Log4jExtension;
import org.example.selenium.DriverUtilities;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(Log4jExtension.class)
public class SeleniumTest extends ReportedTest {

    protected WebDriver driver;

    public SeleniumTest() {
        super();
    }

    @BeforeEach
    void setupDriver() throws Exception {
        driver = DriverUtilities.createDefaultDriver();
    }

    @AfterEach
    void teardownDriver() {
        if(driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
