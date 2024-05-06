package org.example.selenium.strategies.impl;

import org.example.reporting.IReporter;
import org.example.selenium.strategies.ValidationStrategy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.example.reporting.ReporterFactorySupplier;

import java.time.Duration;

public class ValidationStrategies {

    private static final IReporter reporter;

    static {
        reporter = new ReporterFactorySupplier().supply().create();
    }

    public static ValidationStrategy textContains(String text) {
        return (driver, element) -> {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            assert wait.until(ExpectedConditions.textToBePresentInElement(element, text));
            reporter.passStep(String.format("Successfully validated the element: [%s] contained the text: [%s]", element, text));
        };
    }

    public static ValidationStrategy textEquals(String text) {
        return (driver, element) -> {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            assert wait.until(ExpectedConditionsExtension.textEqualsInElement(element, text));
            reporter.passStep(String.format("Successfully validated the element: [%s] had the exact text: [%s]", element, text));
        };
    }

    public static ValidationStrategy attributeContains(String attribute, String text) {
        return (driver, element) -> {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            assert wait.until(ExpectedConditions.attributeContains(element, attribute, text));
            reporter.passStep(String.format("Successfully validated the element: [%s] had the attribute: [%s] containing the text: [%s]", element, attribute, text));
        };
    }

    public static ValidationStrategy attributeEquals(String attribute, String text) {
        return (driver, element) -> {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            assert wait.until(ExpectedConditions.attributeToBe(element, attribute, text));
            reporter.passStep(String.format("Successfully validated the element: [%s] had the attribute: [%s] with the exact text: [%s]", element, attribute, text));
        };
    }

    public static ValidationStrategy titleContains(String text) {
        return (driver, element) -> {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            assert wait.until(ExpectedConditions.titleContains(text));
            reporter.passStep(String.format("Successfully validated the title contains: [%s]", text));
        };
    }

    public static ValidationStrategy titleEquals(String text) {
        return (driver, element) -> {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            assert wait.until(ExpectedConditions.titleIs(text));
            reporter.passStep(String.format("Successfully validated the title equals: [%s]", text));
        };
    }

    public static ValidationStrategy urlContains(String text) {
        return (driver, element) -> {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            assert wait.until(ExpectedConditions.urlContains(text));
            reporter.passStep(String.format("Successfully validated the url contains: [%s]", text));
        };
    }

    public static ValidationStrategy urlEquals(String text) {
        return (driver, element) -> {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            assert wait.until(ExpectedConditions.urlToBe(text));
            reporter.passStep(String.format("Successfully validated the url equals: [%s]", text));
        };
    }

    public static ValidationStrategy elementVisible() {
        return (driver, element) -> {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            assert wait.until(ExpectedConditions.visibilityOf(element)) != null;
            reporter.passStep(String.format("Successfully validated the element: [%s] was visible", element));
        };
    }

    public static ValidationStrategy elementInvisible() {
        return (driver, element) -> {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            assert wait.until(ExpectedConditions.invisibilityOf(element)) != null;
            reporter.passStep(String.format("Successfully validated the element: [%s] was invisible", element));
        };
    }

}
