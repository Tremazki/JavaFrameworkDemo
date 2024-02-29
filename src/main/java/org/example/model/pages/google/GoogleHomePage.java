package org.example.model.pages.google;

import org.example.model.pages.Page;
import org.example.reporting.TestStep;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * An example of an object following the Page Object Model pattern.
 * <br>
 * Each page is responsible for holding their own locators as well as implementing convenience functions for
 * interacting with the page.
 */
public class GoogleHomePage extends Page<GoogleHomePage> {

    static final String SearchButtonLocation = "(.//input[@aria-label=\"Google Search\"])[1]";

    @FindBy(name = "q")
    private WebElement searchBar;

    @FindBy(how = How.XPATH, using = SearchButtonLocation)
    private WebElement searchButton;

    public GoogleHomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void load() {
        driver.get("https://www.google.com");
    }

    @Override
    protected void isLoaded() {
        assert driver.getTitle().contains("Google");
    }

    @TestStep("The user enters the text into the search bar and clicks the search button")
    public void enterTextAndSubmit(String text) {
       enterSearchText(text);
       clickSearchButton();
    }

    @TestStep("The user enters the text into the search bar")
    public void enterSearchText(String text) {
        log.info("Entering search text..");
        wait.until(ExpectedConditions.visibilityOf(searchBar));
        searchBar.sendKeys(text);
    }

    @TestStep("The user clicks the search button")
    public void clickSearchButton() {
        log.info("Clicking the search button..");
        wait.until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.click();
    }
}
