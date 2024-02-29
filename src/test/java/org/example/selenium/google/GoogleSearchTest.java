package org.example.selenium.google;

import org.example.junit.SeleniumTest;
import org.example.model.pages.google.GoogleHomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GoogleSearchTest extends SeleniumTest {

    @Test
    @DisplayName("Test Google Search Functionality")
    void testGoogleSearch() {
        GoogleHomePage home = new GoogleHomePage(driver).get();
        home.enterTextAndSubmit("Test");
        home.validateUrlContains("search");
    }

}
