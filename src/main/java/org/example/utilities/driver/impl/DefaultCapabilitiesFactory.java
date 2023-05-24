package org.example.utilities.driver.impl;

import org.example.utilities.driver.IFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariOptions;

public class DefaultCapabilitiesFactory implements IFactory<Capabilities> {

    public Capabilities create() {
        switch(System.getProperty("browser", "edge")) {
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
