package org.example;

import org.example.pages.google.GoogleHomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@DisplayName("Application Tests - Firefox")
public class App1Test {

    private WebDriver driver;

    @BeforeEach
    void setupDriver() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL("http://localhost:4444"), new FirefoxOptions());
    }

    @AfterEach
    void teardownDriver() throws IOException {
        takeScreenShot();
        driver.quit();
    }

    @Test
    @DisplayName("Test Google Search Functionality")
    void testGoogleSearch() {
        // Navigate to the URL before we create our page objects
        driver.get("https://www.google.com");

        // Create the new page object using our driver, internally this will call the PageFactory
        GoogleHomePage googleHomePage = new GoogleHomePage(driver);
        googleHomePage.enterSearchText("Test");
        googleHomePage.clickSearchButton();

        // Assert the new URL contains the word 'search' as a basic true/false scenario
        assert driver.getCurrentUrl().contains("search");
    }

    private void takeScreenShot() throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        FileHandler.copy(screenshot, new File(String.format("./%s.png", LocalDateTime.now().format(formatter))));
    }
}
