package org.example.selenium.capabilities.impl.xml.parsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class XmlCapabilitiesParser<T extends AbstractDriverOptions<?>> {

    protected final Logger log = LogManager.getLogger();

    protected T                   options;
    protected List<String>        arguments;
    protected Map<String, String> preferences;
    protected Map<String, String> capabilities;
    protected PageLoadStrategy    pageLoadStrategy;
    protected boolean             acceptInsecureCerts;


    public XmlCapabilitiesParser() {
        arguments        = new ArrayList<>();
        preferences      = new HashMap<>();
        capabilities     = new HashMap<>();
        pageLoadStrategy = PageLoadStrategy.NORMAL;
    }

    /**
     * Create and return an object extending from the AbstractDriverOptions class.
     * This includes all the standard browser option classes i.e. ChromeOptions, FirefoxOptions
     *
     * @param file File to be parsed
     * @return Implementation of the AbstractDriverOption class
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public abstract T create(File file) throws ParserConfigurationException, IOException, SAXException;

    /**
     * Very loose implementation of a parser.
     * This does not account for object nodes placed in the incorrect order and doesn't even obey hierarchy of the XML document.
     * Can be improved upon in the future if need be.
     *
     * @param file
     */
    protected void parseXml(File file) {
       try {
           DocumentBuilder builder  = DocumentBuilderFactory.newInstance().newDocumentBuilder();
           Document        document = builder.parse(file);

           document.getDocumentElement().normalize();

           NodeList arguments = document.getElementsByTagName("argument");
           for (int i = 0; i < arguments.getLength(); i++) {
               this.arguments.add(arguments.item(i).getTextContent());
           }

           NodeList preferences = document.getElementsByTagName("preference");
           for (int i = 0; i < preferences.getLength(); i++) {
               this.preferences.put(preferences.item(i).getFirstChild().getTextContent(),
                       preferences.item(i).getLastChild().getTextContent());
           }

           NodeList capabilities = document.getElementsByTagName("capability");
           for (int i = 0; i < capabilities.getLength(); i++) {
               this.capabilities.put(capabilities.item(i).getFirstChild().getTextContent(),
                       capabilities.item(i).getLastChild().getTextContent());
           }

           NodeList pageLoadStrategy = document.getElementsByTagName("pageloadstrategy");
           if (pageLoadStrategy.getLength() > 0) {
               this.pageLoadStrategy = PageLoadStrategy.valueOf(pageLoadStrategy.item(0).getTextContent().toUpperCase());
           }

           NodeList acceptInsecureCerts = document.getElementsByTagName("acceptinsecurecerts");
           if (acceptInsecureCerts.getLength() > 0) {
               this.acceptInsecureCerts = Boolean.parseBoolean(acceptInsecureCerts.item(0).getTextContent());
           }
       } catch (Exception e) {
           log.error("An error occurred while parsing the XML file: ", e);
       }
    }
}
