package org.example.selenium;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtilities {

    private static WebDriver driver;

    public static void setDriver(WebDriver _driver) {
        driver = _driver;
    }

    /**
     * Takes a screenshot using the internal static driver variable and saves it to the ./tmp/ folder using the current
     * state of the given WebDriver instance
     * @throws IOException IOException if the file can not be copied successfully
     */
    public static String takeScreenShotFile() throws IOException {
        return takeScreenShotAsFile(driver);
    }

    /**
     * Takes a screenshot and as Base64 using the internal static driver and returns it using the current state of the
     * given WebDriver instance
     */
    public static String takeScreenShotAsBase64() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

    /**
     * Takes a screenshot and saves it to the ./tmp/ folder using the current state of the given WebDriver instance
     * @param driver WebDriver instance
     * @throws IOException IOException if the file can not be copied successfully
     */
    public static String takeScreenShotAsFile(WebDriver driver) throws IOException {
        final Path path = Paths.get("./tmp");
        if(Files.notExists(path)) {
            Files.createDirectory(path);
        }

        File              screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String            savePath   = String.format("./tmp/%s.png", LocalDateTime.now().format(formatter));

        File savedFile = new File(savePath);
        FileHandler.copy(screenshot, savedFile);

        return savedFile.getCanonicalPath();
    }

    /**
     * Takes a screenshot and as Base64 and returns it using the current state of the given WebDriver instance
     * @param driver WebDriver instance
     */
    public static String takeScreenShotAsBase64(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

}
