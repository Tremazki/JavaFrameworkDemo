package org.example.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.example.selenium.DriverUtilities;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 * This is an example of a Step Definitions file automatically picked up by the Cucumber JUnit runner
 * to be used as glue for the feature files.
 */
public class CucumberSteps {

    protected WebDriver driver;

    @Given("(the user )opens the browser")
    public void openBrowser() throws Exception {
//        driver = DriverUtilities.createDefaultDriver();
        throw new Exception("");
    }


    @When("(the user )closes the browser")
    public void closeBrowser() {
        driver.close();
        driver.quit();
    }
}
