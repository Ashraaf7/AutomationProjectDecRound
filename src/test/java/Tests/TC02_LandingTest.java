package Tests;

import Pages.P01_LoginPage;
import Pages.P02_LandingPage;
import Pages.P03_CartPage;
import Utilities.Utilities;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Set;

import static DriverManager.DriverManager.*;

public class TC02_LandingTest  {

    private Set<Cookie> cookies;
    private SoftAssert softAssert;

    @BeforeClass
    public void login() throws IOException {
        setupDriver("edge");
        getDriver().manage().window().maximize();
        getDriver().get(Utilities.getPropertyValue("URL"));
        new P01_LoginPage(getDriver())
                .loginSteps(Utilities.getJsonData("validLoginData","username"),Utilities.getJsonData("validLoginData","password"));
        cookies = Utilities.getAllCookies(getDriver());

    }

    @BeforeMethod
    public void restoreSession() throws IOException {
        getDriver().get(Utilities.getPropertyValue("URL"));
        Utilities.restoreSession(getDriver(),cookies);
        getDriver().get(Utilities.getPropertyValue("HomePageUrl"));
        getDriver().navigate().refresh();
    }

/*
    @Step
    public void loginSteps()
    {
        new P01_LoginPage(getDriver())
                .loginSteps(Utilities.getJsonData("validLoginData","username"),Utilities.getJsonData("validLoginData","password"));
    }
*/
    @Test
    public void addProductToCartTC()
    {
      //  loginSteps();
        softAssert= new SoftAssert();
      String[] productDetails=
                new P02_LandingPage(getDriver()).clickOnAddToCart()
                .getProductDetails();
            new P02_LandingPage(getDriver()).clickOnCartIcon();

        softAssert.assertTrue(new P03_CartPage(getDriver()).verifyProductLabel(productDetails[0])); //label
        softAssert.assertTrue(new P03_CartPage(getDriver()).verifyProductPrice(productDetails[1])); //price
        softAssert.assertAll();
    }


    @AfterMethod
    public void quit()
    {
        quitDriver();
    }

    @AfterClass
    public void removeSet()
    {
        quitDriver();
    }
}
