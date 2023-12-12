package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.internal.bind.util.ISO8601Utils;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.decorators.WebDriverDecorator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

public class Utilities {
    private static final String screenshotsPath = "src\\main\\resources\\screenshots\\";
    private static final String environmentPath = "src/main/resources/environment.properties";
    private static final String testDataPath = "src/main/resources/TestData/";

    /**
     * function waits fot the element to be clickable before clicking on it.
     * @param driver an Object from WebDriver class
     * @param locator an element that you want to click on
     */
    public static void clickingOnElement(WebDriver driver, By locator)
    {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    public static void sendData(WebDriver driver,By locator,String data)
    {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(data);
    }
    public static String getText(WebDriver driver,By locator)
    {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
       return driver.findElement(locator).getText();
    }

    public static Set<Cookie> getAllCookies(WebDriver driver)
    {
        return driver.manage().getCookies();
    }

    public static void restoreSession (WebDriver driver,Set<Cookie> cookies)
    {
        for (Cookie cookie : cookies )
            driver.manage().addCookie(cookie);
    }

    /**
     * General wait for specific conditions
     * @param driver an Object from WebDriver
     * @return an instance from WebDriverWait, so you can add any conditions after it.
     */
    public static WebDriverWait generalWait (WebDriver driver)
    {
        return new WebDriverWait(driver,Duration.ofSeconds(20));
    }
    public static void takeScreenshot(WebDriver driver,String name) throws IOException {
        Screenshot screenshot = new AShot().takeScreenshot(driver);
        File finalScreenshot = new File(screenshotsPath+name+getTimestamp()+".png");
        ImageIO.write(screenshot.getImage(),"png",finalScreenshot);
        Allure.addAttachment(name , Files.newInputStream(Path.of(finalScreenshot.getPath())));
    }


    public static WebElement byToWebElement(WebDriver driver,By locator)
    {
        return driver.findElement(locator);
    }

    public static void selectFromDropDown(WebDriver driver,By locator,String option)
    {
        new Select(byToWebElement(driver,locator)).selectByVisibleText(option);
    }

    public static void scrolling(WebDriver driver,By locator )
    {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",byToWebElement(driver,locator));
    }

    //TODO: Create function to get the timestamp
    public static String getTimestamp()
    {
        return new SimpleDateFormat("yyyy-MM-dd_h-m-ss a").format(new Date());
    }

    public static String getPropertyValue (String key) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(environmentPath));
        return properties.getProperty(key);
    }


    public static String getJsonData(String fileName,String field)  {
        try {
            FileReader reader = new FileReader(testDataPath+fileName+".json");  //src/resources/testData/validLoginData.json
            JsonElement jsonElement = JsonParser.parseReader(reader);
            return jsonElement.getAsJsonObject().get(field).getAsString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
