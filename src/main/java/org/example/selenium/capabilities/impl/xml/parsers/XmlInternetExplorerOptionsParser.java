package org.example.selenium.capabilities.impl.xml.parsers;

import org.openqa.selenium.ie.InternetExplorerOptions;
import java.io.File;

public class XmlInternetExplorerOptionsParser extends XmlCapabilitiesParser<InternetExplorerOptions> {

    public XmlInternetExplorerOptionsParser() {
        super();
        options = new InternetExplorerOptions();
    }

    @Override
    public InternetExplorerOptions create(File file) {
        parseXml(file);
        options.setPageLoadStrategy(pageLoadStrategy);
        options.setAcceptInsecureCerts(acceptInsecureCerts);
        for(String key : capabilities.keySet()) {
            options.setCapability(key, capabilities.get(key));
        }
        return options;
    }
}
