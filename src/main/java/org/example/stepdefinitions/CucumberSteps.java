package org.example.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class CucumberSteps {

    protected WebDriver driver;

    @Given("(the user )opens the browser")
    public void openBrowser() {
        driver = new EdgeDriver();
    }

    @Given("(the user )opens the application")
    public void openApplication() {

    }

    @When("(the user )closes the browser")
    public void closeBrowser() {
        driver.close();
        driver.quit();
    }
}
