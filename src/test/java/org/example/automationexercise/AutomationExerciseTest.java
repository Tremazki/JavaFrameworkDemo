package org.example.automationexercise;

import org.example.BaseTest;
import org.example.pages.automationexercise.AutomationExerciseHomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AutomationExerciseTest extends BaseTest {

    @Test
    @DisplayName("Login to Automation Exercise")
    public void automationExerciseTest()  {
        driver.get("https://www.automationexercise.com/");
        AutomationExerciseHomePage homePage = new AutomationExerciseHomePage(driver);
        homePage.clickLogin();
        assert true;
    }

}
