package org.example.selenium.driver.impl;

import org.example.IFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class LocalDriverFactory implements IFactory<WebDriver, String> {

    private final String       browser;
    private final Capabilities capabilities;

    LocalDriverFactory(IFactory<? extends Capabilities, String> capabilitiesFactory) {
        assert System.getProperty("browser") != null : "Java system property: 'browser' was null - unable to create a local driver instance";
        this.browser      = System.getProperty("browser");
        this.capabilities = capabilitiesFactory.create();
    }

    public WebDriver create() {
        return create(browser.toLowerCase());
    }

    public WebDriver create(String _condition) {
        switch(_condition) {
            case "firefox":
                return new FirefoxDriver(new FirefoxOptions(capabilities));
            case "safari":
                return new SafariDriver(new SafariOptions(capabilities));
            case "explorer":
                return new InternetExplorerDriver(new InternetExplorerOptions(capabilities));
            case "chrome":
                return new ChromeDriver(new ChromeOptions().merge(capabilities));
            default:
                return new EdgeDriver(new EdgeOptions().merge(capabilities));
        }
    }
}
