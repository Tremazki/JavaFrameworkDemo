package org.example.model.pages.automationexercise;

import org.example.model.pages.Page;
import org.example.reporting.TestStep;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AutomationExerciseSignUpPage extends Page<AutomationExerciseSignUpPage> {

    // Login
    static final String usernameLocator = ".//input[@data-qa=\"login-email\"]";
    static final String passwordLocator = ".//input[@data-qa=\"login-password\"]";
    static final String loginBtnLocator = ".//button[@data-qa=\"login-button\"]";

    // Signup
    static final String nameLocator      = ".//input[@data-qa=\"signup-name\"]";
    static final String emailLocator     = ".//input[@data-qa=\"signup-email\"]";
    static final String signupBtnLocator = ".//button[@data-qa=\"signup-button\"]";

    @FindBy(how = How.XPATH, using = usernameLocator)
    WebElement usernameField;

    @FindBy(how = How.XPATH, using = passwordLocator)
    WebElement passwordField;

    @FindBy(how = How.XPATH, using = loginBtnLocator)
    WebElement loginButton;

    public AutomationExerciseSignUpPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void load() {
        driver.get("https://www.automationexercise.com/login");
    }

    @Override
    protected void isLoaded() {
        assert driver.getTitle().contains("Signup / Login");
    }

    @TestStep("The user enters their login information and submits the login form")
    public void submitLoginForm(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    @TestStep("The user begins the registration process")
    public void startRegistration() {

    }
}
