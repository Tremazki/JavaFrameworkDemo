package org.example.selenium.capabilities.impl.xml.parsers;

import org.openqa.selenium.safari.SafariOptions;
import java.io.File;

public class XmlSafariOptionsParser extends XmlCapabilitiesParser<SafariOptions> {

    public XmlSafariOptionsParser() {
        super();
        options = new SafariOptions();
    }

    @Override
    public SafariOptions create(File file) {
        parseXml(file);
        options.setPageLoadStrategy(pageLoadStrategy);
        options.setAcceptInsecureCerts(acceptInsecureCerts);
        for(String key : capabilities.keySet()) {
            options.setCapability(key, capabilities.get(key));
        }
        return options;
    }
}
