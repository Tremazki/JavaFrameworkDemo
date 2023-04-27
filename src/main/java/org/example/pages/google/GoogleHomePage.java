package org.example.pages.google;

import org.example.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * An example of an object following the Page Object Model pattern.
 * <br>
 * Each page is responsible for holding their own locators as well as implementing convenience functions for
 * interacting with the page.
 */
public class GoogleHomePage extends AbstractPage {

    /* Define our locators */
    static final String SearchButtonLocation = "(.//input[@aria-label=\"Google Search\"])[1]";

    /* Define our WebElement objects since we want to use the Selenium Page Factory to instantiate them for us */
    @FindBy(name = "q")
    private WebElement searchBar;

    @FindBy(how = How.XPATH, using = SearchButtonLocation)
    private WebElement searchButton;

    /* The call to super() will make the call to the Page Factory in our AbstractPage.java class */
    public GoogleHomePage(WebDriver driver) {
        super(driver);
    }

    /* Convenience function for entering search text */
    public void enterSearchText(String text) {
        searchBar.sendKeys(text);
    }

    /* Convenience function for clicking the search button */
    public void clickSearchButton() {
        wait.until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.click();
    }
}
