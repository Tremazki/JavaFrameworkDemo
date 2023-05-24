package org.example.utilities.driver.impl;

import org.example.utilities.driver.IFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class WebDriverFactory {

    protected boolean                          remote;
    protected String                           grid;
    protected IFactory<? extends Capabilities> capabilitiesFactory;

    public WebDriverFactory() {
        this(new DefaultCapabilitiesFactory());
    }

    public WebDriverFactory(IFactory<? extends Capabilities> capabilitiesFactory) {
       this.remote              = Boolean.parseBoolean(System.getProperty("remote", "false"));
       this.grid                = System.getProperty("grid", "http://localhost:4444");
       this.capabilitiesFactory = capabilitiesFactory;
    }

    public WebDriver create() throws URISyntaxException, MalformedURLException {
        if (remote) {
            return new RemoteWebDriver(new URI(grid).toURL(), capabilitiesFactory.create());
        } else {
            return new LocalDriverFactory(capabilitiesFactory).create();
        }
    }
}

