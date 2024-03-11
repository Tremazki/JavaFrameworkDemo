package org.example.model.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.annotations.TestStep;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

/**
 * Page defines the functionality we want all of our pages to extend from.
 * <br>
 * In this case, since we want all of our pages to initialize using the PageFactory, we use the constructor here to enforce that
 * in the subclasses.
 */
@SuppressWarnings("unchecked")
public abstract class Page<T extends Page<T>> extends LoadableComponent<T> {

    protected Properties           objectRepository;
    protected Page<?>              parent;
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

    public Page(Page<?> parent) {
        this(parent.getDriver());
        this.parent = parent;
    }

    @TestStep(value = "The user validates the title equals the given string", reportArguments = true)
    public T validateTitleEquals(String title) {
        assert driver.getTitle().equals(title);
        return (T) this;
    }

    @TestStep(value = "The user validates the title contains the given string", reportArguments = true)
    public T validateTitleContains(String title) {
        assert driver.getTitle().contains(title);
        return (T) this;
    }

    @TestStep(value = "The user validates the url equals the given string", reportArguments = true)
    public T validateUrlEquals(String url) {
        assert driver.getCurrentUrl().equals(url);
        return (T) this;
    }

    @TestStep(value = "The user validates the url contains the given string", reportArguments = true)
    public T validateUrlContains(String url) {
        assert driver.getCurrentUrl().contains(url);
        return (T) this;
    }

    protected abstract void load();

    protected abstract void isLoaded();

    protected WebDriver getDriver() {
        return driver;
    }

    private void loadProperties() {
        Class<?> clazz = this.getClass();
        try {
            objectRepository.load(clazz.getResourceAsStream("/pages/" + clazz.getSimpleName() + ".properties"));
        } catch (Throwable e) {
            log.info("Failed to find an associated object properties folder for the page class: " + clazz.getName());
        }
    }
}
