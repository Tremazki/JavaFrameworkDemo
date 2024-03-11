package org.example.selenium.capabilities.impl.xml;

import org.example.annotations.CapabilitiesFactory;
import org.example.selenium.capabilities.impl.DefaultCapabilitiesFactory;
import org.example.selenium.capabilities.impl.xml.parsers.*;
import org.openqa.selenium.Capabilities;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

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
        xmlFileFolder = getResourcesFolder(System.getProperty("capabilitiesFolder", "capabilities"));
    }

    public Capabilities create() {
        return create(System.getProperty("browser", "edge"));
    }

    @Override
    public Capabilities create(String _condition) {
        switch (_condition) {
            case "firefox":
                return new XmlFirefoxOptionsParser().create(findXmlFile(xmlFileFolder, "firefox.xml"));
            case "safari":
                return new XmlSafariOptionsParser().create(findXmlFile(xmlFileFolder, "safari.xml"));
            case "explorer":
                return new XmlInternetExplorerOptionsParser().create(findXmlFile(xmlFileFolder, "explorer.xml"));
            case "chrome":
                return new XmlChromeOptionsParser().create(findXmlFile(xmlFileFolder, "chrome.xml"));
            case "edge":
            default:
                return new XmlEdgeOptionsParser().create(findXmlFile(xmlFileFolder, "edge.xml"));
        }
    }

    private File findXmlFile(File folder, String fileName) {
        File[] filteredFiles = folder.listFiles((dir, name) -> name.equals(fileName));
        return Objects.requireNonNull(filteredFiles)[0];
    }

    private File getResourcesFolder(String folderName) throws URISyntaxException {
        URL url = getClass().getClassLoader().getResource(folderName);
        return new File(Objects.requireNonNull(url).toURI());
    }
}
