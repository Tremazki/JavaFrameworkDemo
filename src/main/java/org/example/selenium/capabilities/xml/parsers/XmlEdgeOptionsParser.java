package org.example.selenium.capabilities.xml.parsers;

import org.openqa.selenium.edge.EdgeOptions;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XmlEdgeOptionsParser extends XmlCapabilitiesParser<EdgeOptions> {

    public XmlEdgeOptionsParser() {
        super();
        options = new EdgeOptions();
    }

    @Override
    public EdgeOptions create(File file) throws ParserConfigurationException, IOException, SAXException {
        parseXml(file);
        options.addArguments(arguments);
        options.setPageLoadStrategy(pageLoadStrategy);
        options.setAcceptInsecureCerts(acceptInsecureCerts);
        return options;
    }
}
