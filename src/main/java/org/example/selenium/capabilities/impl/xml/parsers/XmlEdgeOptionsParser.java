package org.example.selenium.capabilities.impl.xml.parsers;

import org.openqa.selenium.edge.EdgeOptions;
import java.io.File;

public class XmlEdgeOptionsParser extends XmlCapabilitiesParser<EdgeOptions> {

    public XmlEdgeOptionsParser() {
        super();
        options = new EdgeOptions();
    }

    @Override
    public EdgeOptions create(File file)  {
        parseXml(file);
        options.addArguments(arguments);
        options.setPageLoadStrategy(pageLoadStrategy);
        options.setAcceptInsecureCerts(acceptInsecureCerts);
        return options;
    }
}
