package org.example.model.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.annotations.reporting.TestStep;
import org.example.model.pages.strategies.SelectionStrategy;
import org.example.model.pages.strategies.ValidationStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

/**
 * Page defines the functionality we want all of our pages to extend from.
 * <br>
 * In this case, since we want all of our pages to initialize using the PageFactory, we use the constructor here to enforce that
 * in the subclasses.
 */
@SuppressWarnings({"unchecked", "unused"})
public abstract class Page<T extends Page<T>> extends LoadableComponent<T> {

    protected Properties           objectRepository;
    protected Page<?>              parent;
    protected WebDriver            driver;
    protected WebDriverWait        wait;
    protected Logger               log;

    public Page(WebDriver driver) {
        this.parent           = null;
        this.driver           = driver;
        this.wait             = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.objectRepository = new Properties();
        this.log              = LogManager.getLogger(this.getClass());

        loadProperties();
        PageFactory.initElements(driver, this);
    }

    public Page(Page<?> parent) {
        this(parent.getDriver());
        this.parent = parent;
    }

    @TestStep(value = "The user enters the given text on the web element", reportArguments = true)
    public T type(WebElement element, String text) {
        element.sendKeys(text);
        return (T) this;
    }

    @TestStep(value = "The user clicks on the given web element", reportArguments = true)
    public T click(WebElement element) {
        element.click();
        return (T) this;
    }

    @TestStep(value = "The user selects the option from the dropdown menu", reportArguments = true)
    public T select(WebElement element, SelectionStrategy strategy) {
        strategy.apply(new Select(element));
        return (T) this;
    }

    @TestStep(value = "The user performs the given validation strategy", reportArguments = true)
    public T validate(WebElement element, ValidationStrategy strategy) {
        strategy.apply(driver, element);
        return (T) this;
    }

    @TestStep(value = "The user validates the text equals the given string", reportArguments = true)
    public T validateTextEquals(String text) {
        validate(null, ValidationStrategy.textEquals(text));
        return (T) this;
    }

    @TestStep(value = "The user validates the text contains the given string", reportArguments = true)
    public T validateTextContains(String text) {
        validate(null, ValidationStrategy.textContains(text));
        return (T) this;
    }

    @TestStep(value = "The user validates the element is visible", reportArguments = true)
    public T validateVisible(WebElement element) {
        validate(element, ValidationStrategy.elementVisible());
        return (T) this;
    }

    @TestStep(value = "The user validates the element is invisible", reportArguments = true)
    public T validateInvisible(WebElement element) {
        validate(element, ValidationStrategy.elementInvisible());
        return (T) this;
    }

    @TestStep(value = "The user validates the title equals the given string", reportArguments = true)
    public T validateTitleEquals(String title) {
        validate(null, ValidationStrategy.titleEquals(title));
        return (T) this;
    }

    @TestStep(value = "The user validates the title contains the given string", reportArguments = true)
    public T validateTitleContains(String title) {
        validate(null, ValidationStrategy.titleContains(title));
        return (T) this;
    }

    @TestStep(value = "The user validates the url equals the given string", reportArguments = true)
    public T validateUrlEquals(String url) {
        validate(null, ValidationStrategy.urlEquals(url));
        return (T) this;
    }

    @TestStep(value = "The user validates the url contains the given string", reportArguments = true)
    public T validateUrlContains(String url) {
        validate(null, ValidationStrategy.urlContains(url));
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
