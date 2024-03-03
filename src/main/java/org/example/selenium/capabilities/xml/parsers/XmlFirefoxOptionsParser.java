package org.example.selenium.capabilities.xml.parsers;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XmlFirefoxOptionsParser extends XmlCapabilitiesParser<FirefoxOptions> {

    public XmlFirefoxOptionsParser() {
        super();
        options = new FirefoxOptions();
    }

    @Override
    public FirefoxOptions create(File file) throws ParserConfigurationException, IOException, SAXException {
        parseXml(file);
        options.addArguments(arguments);
        options.setPageLoadStrategy(pageLoadStrategy);
        options.setAcceptInsecureCerts(acceptInsecureCerts);
        for(String key : preferences.keySet()) {
            options.addPreference(key, preferences.get(key));
        }
        for(String key : capabilities.keySet()) {
            options.setCapability(key, capabilities.get(key));
        }
        return options;
    }

}
