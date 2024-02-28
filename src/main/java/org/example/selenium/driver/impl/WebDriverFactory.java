package org.example.selenium.driver.impl;

import org.example.IFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * We intentionally don't want to implement the IFactory here because we want our exceptions in the create() method
 * to bubble up and stop execution since the drivers are the crux of all our testing.
 */
public class WebDriverFactory {

    protected boolean                                  remote;
    protected String                                   grid;
    protected IFactory<? extends Capabilities, String> capabilitiesFactory;

    public WebDriverFactory() {
        this(new DefaultCapabilitiesFactory());
    }

    public WebDriverFactory(IFactory<? extends Capabilities, String> capabilitiesFactory) {
       this.remote              = Boolean.parseBoolean(System.getProperty("remote", "false"));
       this.grid                = System.getProperty("grid", "http://localhost:4444");
       this.capabilitiesFactory = capabilitiesFactory;
    }

    /**
     * Determine if the current run is remote and return a remote or local driver based on the 'remote' system property.
     * <br>
     * 1) If it's remote, we retrieve the 'grid' system property and use that URL for our remote grid target.
     * 2) If it's local, we use the LocalDriverFactory class to determine which local driver to create using the 'browser'
     * system property.
     *
     * @return WebDriver instance
     * @throws URISyntaxException
     * @throws MalformedURLException
     */
    public WebDriver create() throws URISyntaxException, MalformedURLException {
        if (remote) {
            return new RemoteWebDriver(new URI(grid).toURL(), capabilitiesFactory.create());
        } else {
            return new LocalDriverFactory(capabilitiesFactory).create();
        }
    }
}

