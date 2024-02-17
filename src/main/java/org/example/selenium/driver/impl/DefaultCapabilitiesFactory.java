package org.example.selenium.driver.impl;

import org.example.IFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariOptions;

public class DefaultCapabilitiesFactory implements IFactory<Capabilities, String> {

    public Capabilities create() {
        return create(System.getProperty("browser", "edge"));
    }

    @Override
    public Capabilities create(String _condition) {
        switch(_condition) {
            case "firefox":
                return new FirefoxOptions();
            case "safari":
                return new SafariOptions();
            case "explorer":
                return new InternetExplorerOptions();
            case "chrome":
                return new ChromeOptions();
            default:
                return new EdgeOptions();
        }
    }
}
