package org.example.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtilities {

    /**
     * Takes a screenshot and saves it to the ./tmp/ folder using the current state of the given WebDriver instance
     * @param driver WebDriver instance
     * @throws IOException IOException if the file can not be copied successfully
     */
    public static void takeScreenShot(WebDriver driver) throws IOException {
        File              screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String            savePath   = String.format("./tmp/%s.png", LocalDateTime.now().format(formatter));
        FileHandler.copy(screenshot, new File(savePath));
    }

}
