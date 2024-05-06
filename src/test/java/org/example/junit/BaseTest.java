package org.example.junit;

import org.example.reporting.IReporter;
import org.example.reporting.ReporterFactorySupplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import static org.example.utilities.IOUtilities.findFile;
import static org.example.utilities.IOUtilities.getResource;

public class BaseTest {

    protected final Logger     logger;
    protected final IReporter  reporter;
    protected final Properties configuration;
    protected final Properties environment;

    BaseTest() {
        logger        = LogManager.getLogger(this.getClass());
        reporter      = new ReporterFactorySupplier().supply().create();
        configuration = new Properties();
        environment   = new Properties();
        loadConfiguration();
        loadEnvironment(System.getProperty("environment", "default"));
    }

    void loadConfiguration() {
        try {
            this.configuration.load(new FileInputStream(getResource("configuration.properties")));
        } catch (IOException | URISyntaxException e) {
            logger.warn("Failed to find the 'configuration.properties' file located at the " +
                        "root of the test resources directory", e);
        }
    }

    void loadEnvironment(String environment) {
        try {
            this.environment.load(new FileInputStream(findFile(getResource("environment"), environment + ".properties")));
        } catch (IOException | URISyntaxException e) {
            logger.warn("Failed to find the associated environment properties file located in the " +
                        "environment folder within the test resources directory", e);
        }
    }

    @BeforeEach
    void setupTestReport(TestInfo _testInfo) {
        reporter.startTest(_testInfo.getDisplayName());
    }

    @AfterEach
    void teardownTestReport() {
        reporter.write();
    }
}
