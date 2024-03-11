package org.example.selenium.google;

import org.example.junit.SeleniumTest;
import org.example.model.pages.google.GoogleHomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class GoogleSearchTest extends SeleniumTest {

    @DisplayName("Test Google Search Functionality")
    @ParameterizedTest
    @ValueSource(strings = {"Test", "Automation"})
    void testGoogleSearch(String searchTerm) {
        GoogleHomePage home = new GoogleHomePage(driver).get();
        home.enterTextAndSubmit(searchTerm);
        home.validateUrlContains("search");
    }

}
