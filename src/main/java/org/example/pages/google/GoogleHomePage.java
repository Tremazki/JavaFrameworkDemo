package org.example.pages.google;

import org.example.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GoogleHomePage extends AbstractPage {

    static final String SearchButtonLocation = "(.//input[@aria-label=\"Google Search\"])[1]";
    static final String IFLButtonLocation    = "(.//input[@aria-label=\"I'm Feeling Lucky\"])[1]";

    @FindBy(name = "q")
    private WebElement searchBar;

    @FindBy(how = How.XPATH, using = SearchButtonLocation)
    private WebElement searchButton;

    @FindBy(how = How.XPATH, using = IFLButtonLocation)
    private WebElement iflButton;

    public GoogleHomePage(WebDriver driver) {
        super(driver);
    }

    public void enterSearchText(String text) {
        searchBar.sendKeys(text);
        searchBar.click(); //Get focus again from the dropdown that appears
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickImFeelingLuckyButton() {
        iflButton.click();
    }
}
