package org.example.automationexercise;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.SeleniumTest;
import org.example.pages.automationexercise.AutomationExerciseCheckoutPage;
import org.example.pages.automationexercise.AutomationExerciseHomePage;
import org.example.pages.automationexercise.AutomationExerciseSecuredPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AutomationExerciseTest extends SeleniumTest {

    public Logger log = LogManager.getLogger(AutomationExerciseTest.class);

    @Test
    @DisplayName("Login to Automation Exercise")
    public void automationExerciseTest()  {
        AutomationExerciseHomePage home = new AutomationExerciseHomePage(driver).get();

        home.clickLogin();

        assert driver.getTitle().contains("Signup");
    }

    @Test
    @DisplayName("Go to Checkout")
    public void automationExerciseTest2()  {
        AutomationExerciseSecuredPage securedPage = new AutomationExerciseSecuredPage(
                driver,
                new AutomationExerciseCheckoutPage(driver),
                "tyler@qac.com",
                "password").get();

        assert driver.getTitle().contains("Checkout");
    }

}
