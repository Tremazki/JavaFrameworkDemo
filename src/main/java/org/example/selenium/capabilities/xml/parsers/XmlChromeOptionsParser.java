package org.example.selenium.capabilities.xml.parsers;

import org.openqa.selenium.chrome.ChromeOptions;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XmlChromeOptionsParser extends XmlCapabilitiesParser<ChromeOptions> {

    public XmlChromeOptionsParser() {
        super();
        options = new ChromeOptions();
    }

    @Override
    public ChromeOptions create(File file) throws ParserConfigurationException, IOException, SAXException {
        parseXml(file);
        options.addArguments(arguments);
        options.setPageLoadStrategy(pageLoadStrategy);
        options.setAcceptInsecureCerts(acceptInsecureCerts);
        for(String key : capabilities.keySet()) {
            options.setCapability(key, capabilities.get(key));
        }
        return options;
    }
}
