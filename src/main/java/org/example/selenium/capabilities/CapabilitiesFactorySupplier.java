package org.example.selenium.capabilities;

import org.example.ISupplier;
import org.example.selenium.capabilities.xml.XmlCapabilitiesFactory;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CapabilitiesFactorySupplier is responsible for storing all possible CapabilitiesFactory objects
 * and returning them based on the specified system property, 'capabilities' allowing for easy configuring
 * through the command line.
 */
public final class CapabilitiesFactorySupplier {

    private static Map<Class<?>, CapabilitiesFactory> registry;

    public CapabilitiesFactorySupplier() throws URISyntaxException {
        if(registry == null || registry.isEmpty()) {
            registry = new ConcurrentHashMap<>();
            registry.put(CapabilitiesFactory.class,    new CapabilitiesFactory());
            registry.put(XmlCapabilitiesFactory.class, new XmlCapabilitiesFactory());
        }
    }

    public static CapabilitiesFactory supply() {
        switch(System.getProperty("capabilities", "default")) {
            default:
            case "default":
                return registry.get(CapabilitiesFactory.class);
            case "xml":
                return registry.get(XmlCapabilitiesFactory.class);
        }
    }
}
