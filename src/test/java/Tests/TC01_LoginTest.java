package Tests;

import DriverManager.DriverManager;
import Listenres.IInvokedMethodListenerClass;
import Listenres.Retry;
import Pages.P01_LoginPage;
import Utilities.Utilities;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Utilities.Utilities;
import java.io.IOException;
import java.time.Duration;

import static DriverManager.DriverManager.*;

@Test(retryAnalyzer = Retry.class)
@Listeners({IInvokedMethodListenerClass.class})
public class TC01_LoginTest {

 //condition ? true : false
    @BeforeMethod
    public void setup() throws IOException {
        String browser = System.getProperty("browser")!= null ? System.getProperty("browser") : Utilities.getPropertyValue("browser");
        setupDriver(browser);
        getDriver().manage().window().maximize();
        getDriver().get(Utilities.getPropertyValue("URL"));
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }



    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the user can login with valid credentials")
    @Test(priority = 1,retryAnalyzer = Retry.class)
    public void validLoginTC() throws IOException {
        new P01_LoginPage(getDriver()).enterUsername(Utilities.getJsonData("validLoginData","username"))
                .enterPassword(Utilities.getJsonData("validLoginData","password"))
                .clickOnLoginButton();
        Utilities.takeScreenshot(getDriver(),"validLoginTC");
        Assert.assertFalse(new P01_LoginPage(getDriver()).assertLoginTCS(Utilities.getPropertyValue("HomePageUrl")));
    }

    @Test(priority = 2)
    public void invalidLoginWithWrongUsernameTC() throws IOException {
        new P01_LoginPage(getDriver()).enterUsername(Utilities.getJsonData("inValidLoginData","wrong_username"))
                .enterPassword(Utilities.getJsonData("inValidLoginData","correct_password"))
                .clickOnLoginButton();
        Utilities.takeScreenshot(getDriver(),"invalidLoginWithWrongUsernameTC");
        Assert.assertFalse(new P01_LoginPage(getDriver()).assertLoginTCS(Utilities.getPropertyValue("HomePageUrl")));
    }

    @Test(priority = 3)
    public void invalidLoginWithWrongPasswordTC() throws IOException {
        new P01_LoginPage(getDriver()).enterUsername(Utilities.getJsonData("inValidLoginData","correct_username"))
                .enterPassword(Utilities.getJsonData("inValidLoginData","wrong_password"))
                .clickOnLoginButton();
        Utilities.takeScreenshot(getDriver(),"invalidLoginWithWrongPasswordTC");
        Assert.assertFalse(new P01_LoginPage(getDriver()).assertLoginTCS(Utilities.getPropertyValue("HomePageUrl")));
    }


    @AfterMethod
    public void quit()
    {
        quitDriver();
    }


}
