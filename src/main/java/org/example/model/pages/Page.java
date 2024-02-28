package org.example.model.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

/**
 * Page defines the functionality we want all of our pages to extend from.
 * <br>
 * In this case, since we want all of our pages to initialize using the PageFactory, we use the constructor here to enforce that
 * in the subclasses.
 */
public abstract class Page<T extends Page<T>> extends LoadableComponent<T> {

    protected Properties           objectRepository;
    protected LoadableComponent<?> parent;
    protected WebDriver            driver;
    protected WebDriverWait        wait;
    protected Logger               log;

    public Page(WebDriver driver) {
        this.parent           = null;
        this.driver           = driver;
        this.wait             = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.objectRepository = new Properties();
        this.log              = LogManager.getLogger(this.getClass());

        loadProperties();
        PageFactory.initElements(driver, this);
    }

    public Page(WebDriver driver, LoadableComponent<?> parent) {
        this(driver);
        this.parent = parent;
    }

    protected abstract void load();

    protected abstract void isLoaded();

    /**
     * Load a properties file named after the class name of this Page located under the ./resources/page/ folder.
     * This is effectively the object repository for the given page object.
     *
     * This is not required for a page to function and is more a preference of the developer/tester
     */
    private void loadProperties() {
        Class<?> clazz = this.getClass();
        try {
            objectRepository.load(clazz.getResourceAsStream("/pages/" + clazz.getSimpleName() + ".properties"));
        } catch (Throwable e) {
            log.debug("Failed to find an associated object properties folder for the page class: " + clazz.getName());
        }
    }
}
