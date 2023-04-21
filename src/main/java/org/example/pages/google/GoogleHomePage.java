package org.example.pages.google;

import org.example.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class GoogleHomePage extends AbstractPage {

    @FindBy(name = "q")
    private WebElement searchBar;

    @FindBy(how = How.XPATH, using = "(.//input[@aria-label=\"Google Search\"])[1]")
    private WebElement searchButton;

    public GoogleHomePage(WebDriver driver) {
        super(driver);
    }

    public void enterSearchText(String text) {
        searchBar.sendKeys(text);
        searchBar.click(); //Get focus again
    }

    public void clickSearchButton() {
        searchButton.click();
    }
}
