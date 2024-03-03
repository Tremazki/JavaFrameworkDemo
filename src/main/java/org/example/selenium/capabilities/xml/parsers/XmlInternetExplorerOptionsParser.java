package org.example.selenium.capabilities.xml.parsers;

import org.openqa.selenium.ie.InternetExplorerOptions;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XmlInternetExplorerOptionsParser extends XmlCapabilitiesParser<InternetExplorerOptions> {

    public XmlInternetExplorerOptionsParser() {
        super();
        options = new InternetExplorerOptions();
    }

    @Override
    public InternetExplorerOptions create(File file) throws ParserConfigurationException, IOException, SAXException {
        parseXml(file);
        options.setPageLoadStrategy(pageLoadStrategy);
        options.setAcceptInsecureCerts(acceptInsecureCerts);
        for(String key : capabilities.keySet()) {
            options.setCapability(key, capabilities.get(key));
        }
        return options;
    }
}
