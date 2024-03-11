package org.example.selenium.capabilities.impl.xml.parsers;

import org.openqa.selenium.firefox.FirefoxOptions;
import java.io.File;

public class XmlFirefoxOptionsParser extends XmlCapabilitiesParser<FirefoxOptions> {

    public XmlFirefoxOptionsParser() {
        super();
        options = new FirefoxOptions();
    }

    @Override
    public FirefoxOptions create(File file) {
        parseXml(file);
        options.addArguments(arguments);
        options.setPageLoadStrategy(pageLoadStrategy);
        options.setAcceptInsecureCerts(acceptInsecureCerts);
        for (String key : preferences.keySet()) {
            options.addPreference(key, preferences.get(key));
        }
        for (String key : capabilities.keySet()) {
            options.setCapability(key, capabilities.get(key));
        }
        return options;
    }

}
