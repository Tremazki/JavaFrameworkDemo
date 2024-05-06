package org.example.selenium.strategies;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface ValidationStrategy {
    void apply(WebDriver driver, WebElement element);
}
