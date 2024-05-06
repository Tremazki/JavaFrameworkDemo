package org.example.selenium.strategies.impl;

import org.example.selenium.strategies.SelectionStrategy;

public class SelectionStrategies {

    public static SelectionStrategy selectByIndex(int index) {
        return select -> select.selectByIndex(index);
    }

    public static SelectionStrategy selectByValue(String value) {
        return select -> select.selectByValue(value);
    }

    public static SelectionStrategy selectByText(String text) {
        return select -> select.selectByVisibleText(text);
    }
}
