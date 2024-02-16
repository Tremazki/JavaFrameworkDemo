package org.example.model.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page defines the functionality we want all of our pages to extend from.
 * <br>
 * In this case, since we want all of our pages to initialize using the PageFactory, we use the constructor here to enforce that
 * in the subclasses.
 */
public abstract class Page<T extends Page<T>> extends LoadableComponent<T> {

    protected LoadableComponent<?> parent = null;
    protected WebDriver            driver;
    protected WebDriverWait        wait;
    protected Logger               log;

    public Page(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.log    = LogManager.getLogger(getClass());
        PageFactory.initElements(driver, this);
    }

    public Page(WebDriver driver, LoadableComponent<?> parent) {
        this(driver);
        this.parent = parent;
    }

    protected abstract void load();

    protected abstract void isLoaded();
}
