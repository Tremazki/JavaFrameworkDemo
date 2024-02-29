package org.example.model.pages.automationexercise;

import org.example.model.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.fail;

@SuppressWarnings("unchecked")
public class AutomationExerciseSecuredPage<T extends Page<T>> extends Page<T> {

    private final String username;
    private final String password;

    private final String logoutLocator = ".//a[@href=\"/logout\"]";

    public AutomationExerciseSecuredPage(Page<?> parent, String username, String password) {
        super(parent);
        this.username = username;
        this.password = password;
    }

    @Override
    protected void load() {
        AutomationExerciseSignUpPage signUpPage = new AutomationExerciseSignUpPage(driver).get();
        signUpPage.submitLoginForm(username, password);
        parent.get();
    }

    @Override
    public T get() {
        super.get();
        return (T) parent;
    }

    @Override
    protected void isLoaded() {
        try {
            driver.findElement(By.xpath(logoutLocator));
        } catch (NoSuchElementException e ) {
            fail("Failed to locate the logout element");
        }
    }
}
