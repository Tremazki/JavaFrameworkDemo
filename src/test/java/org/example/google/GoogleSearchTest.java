package org.example.google;

import org.example.BaseTest;
import org.example.pages.google.GoogleHomePage;
import org.example.utilities.ScreenshotUtilities;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.IOException;
import java.net.MalformedURLException;

@DisplayName("Application Tests - Firefox")
public class GoogleSearchTest extends BaseTest {

    @Test
    @DisplayName("Test Google Search Functionality")
    void testGoogleSearch() {
        // Navigate to the URL before we create our page objects
        driver.get("https://www.google.com");

        // Create the new page object using our driver, internally this will call the PageFactory
        GoogleHomePage googleHomePage = new GoogleHomePage(driver);
        googleHomePage
                .enterSearchText("Test")
                .clickSearchButton();

        // Assert the new URL contains the word 'search' as a basic true/false scenario
        assert driver.getCurrentUrl().contains("search");
    }

}
