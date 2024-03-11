package org.example.selenium.capabilities.impl.xml.parsers;

import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;

public class XmlChromeOptionsParser extends XmlCapabilitiesParser<ChromeOptions> {

    public XmlChromeOptionsParser() {
        super();
        options = new ChromeOptions();
    }

    @Override
    public ChromeOptions create(File file) {
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
