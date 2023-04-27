package org.example.pages;

import org.example.utilities.ScreenshotUtilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * AbstractPage defines the functionality we want all of our pages to extend from.
 * <br>
 * In this case, since we want all of our pages to initialize using the PageFactory, we use the constructor here to enforce that
 * in the subclasses.
 */
public abstract class AbstractPage {

    protected WebDriver     driver;
    protected WebDriverWait wait;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofMinutes(1));
        PageFactory.initElements(driver, this);
    }

    protected void takeScreenshot() throws IOException {
        ScreenshotUtilities.takeScreenShot(driver);
    }
}
