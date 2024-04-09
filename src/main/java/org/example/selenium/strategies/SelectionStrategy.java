package org.example.selenium.strategies;

import org.openqa.selenium.support.ui.Select;

public abstract class SelectionStrategy {

    abstract public void apply(Select select);

    public static SelectionStrategy selectByIndex(int index) {
        return new SelectionStrategy() {
            public void apply(Select select) {
                select.selectByIndex(index);
            }
        };
    }

    public static SelectionStrategy selectByValue(String value) {
        return new SelectionStrategy() {
            public void apply(Select select) {
                select.selectByValue(value);
            }
        };
    }

    public static SelectionStrategy selectByText(String text) {
        return new SelectionStrategy() {
            public void apply(Select select) {
                select.selectByVisibleText(text);
            }
        };
    }
}
