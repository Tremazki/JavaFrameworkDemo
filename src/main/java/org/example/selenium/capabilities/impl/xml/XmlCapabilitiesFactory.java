package org.example.selenium.capabilities.impl.xml;

import org.example.annotations.CapabilitiesFactory;
import org.example.selenium.capabilities.impl.DefaultCapabilitiesFactory;
import org.example.selenium.capabilities.impl.xml.parsers.*;
import org.openqa.selenium.Capabilities;

import java.io.File;
import java.net.URISyntaxException;

import static org.example.utilities.IOUtilities.findFile;
import static org.example.utilities.IOUtilities.getResource;

/**
 * XmlCapabilitiesFactory will use XML files defined in a location (Default: src/test/resources/capabilities)
 * to create and return the browser options.
 * <br>
 * Alternatively, you can provide your own folder using the xmlCapabilities system property.
 * i.e. -DxmlCapabilities="myNewCapabilitiesFolder"
 */
@CapabilitiesFactory("XML")
public class XmlCapabilitiesFactory extends DefaultCapabilitiesFactory {

    private final File xmlFileFolder;

    public XmlCapabilitiesFactory() throws URISyntaxException {
        xmlFileFolder = getResource(System.getProperty("capabilitiesFolder", "capabilities"));
    }

    public Capabilities create() {
        return create(System.getProperty("browser", "edge"));
    }

    @Override
    public Capabilities create(String _condition) {
        switch (_condition) {
            case "firefox":
                return new XmlFirefoxOptionsParser().create(findFile(xmlFileFolder, "firefox.xml"));
            case "safari":
                return new XmlSafariOptionsParser().create(findFile(xmlFileFolder, "safari.xml"));
            case "explorer":
                return new XmlInternetExplorerOptionsParser().create(findFile(xmlFileFolder, "explorer.xml"));
            case "chrome":
                return new XmlChromeOptionsParser().create(findFile(xmlFileFolder, "chrome.xml"));
            case "edge":
            default:
                return new XmlEdgeOptionsParser().create(findFile(xmlFileFolder, "edge.xml"));
        }
    }

}
