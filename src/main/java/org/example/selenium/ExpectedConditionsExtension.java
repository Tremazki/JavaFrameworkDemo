package org.example.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ExpectedConditionsExtension {

    public static ExpectedCondition<Boolean> textEquals(final WebElement element, final String value) {
        return new ExpectedCondition<Boolean>() {
            private String currentValue = null;

            public Boolean apply(WebDriver driver) {
                try {
                    this.currentValue = element.getText();
                    return this.currentValue.equals(value);
                } catch (Exception var3) {
                    return false;
                }
            }

            public String toString() {
                return String.format("element found by %s to have text \"%s\". Current text: \"%s\"", element , value, this.currentValue);
            }
        };
    }

}
