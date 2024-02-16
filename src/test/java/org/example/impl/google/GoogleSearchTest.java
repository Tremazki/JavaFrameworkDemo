package org.example.impl.google;

import org.example.junit.SeleniumTest;
import org.example.model.pages.google.GoogleHomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Application Tests - Firefox")
public class GoogleSearchTest extends SeleniumTest {

    @Test
    @DisplayName("Test Google Search Functionality")
    void testGoogleSearch() {
        /*
         * Create the new page object using our driver, internally this will call the PageFactory and resolve the
         * WebElements within the class
         */
        GoogleHomePage home = new GoogleHomePage(driver).get();

        home.enterSearchText("Test")
            .clickSearchButton();

        // Assert the new URL contains the word 'search' as a basic true/false scenario
        assert driver.getCurrentUrl().contains("search");
    }

}
