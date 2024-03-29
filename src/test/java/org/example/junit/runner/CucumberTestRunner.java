package org.example.junit.runner;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME,   value = "org.example.stepdefinitions")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "org.example.cucumber.CucumberReportListener, org.example.cucumber.CucumberLogListener")
public class CucumberTestRunner {
}
