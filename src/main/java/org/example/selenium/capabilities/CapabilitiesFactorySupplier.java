package org.example.selenium.capabilities;

import org.example.ISupplier;
import org.example.selenium.capabilities.xml.XmlCapabilitiesFactory;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class CapabilitiesFactorySupplier implements ISupplier<CapabilitiesFactory> {

    private final Map<Class<?>, CapabilitiesFactory> registry;

    public CapabilitiesFactorySupplier() throws URISyntaxException {
        registry = new HashMap<>();
        registry.put(CapabilitiesFactory.class,    new CapabilitiesFactory());
        registry.put(XmlCapabilitiesFactory.class, new XmlCapabilitiesFactory());
    }

    @Override
    public CapabilitiesFactory supply() {
        switch(System.getProperty("capabilities", "default")) {
            default:
            case "default":
                return registry.get(CapabilitiesFactory.class);
            case "xml":
                return registry.get(XmlCapabilitiesFactory.class);
        }
    }
}
