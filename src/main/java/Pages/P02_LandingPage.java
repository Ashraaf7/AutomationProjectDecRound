package Pages;

import Utilities.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P02_LandingPage {
    private WebDriver driver;
    public P02_LandingPage(WebDriver driver)
    {
        this.driver = driver;
    }

    private final By sauceLabsBackpackProductLabel= By.id("item_4_title_link");
    private final By sauceLabsBackpackProductPrice = By.xpath("(//div[@class='inventory_item_price'])[1]");
    private final By addToCartButtonForSauceLabsBackpackProduct = By.id("add-to-cart-sauce-labs-backpack");
    private final By cartIcon = By.className("shopping_cart_link");
    public P02_LandingPage clickOnAddToCart()
    {
        Utilities.clickingOnElement(driver,addToCartButtonForSauceLabsBackpackProduct);
        return this;
    }
    public String getSauceLabsBackpackProductLabel()
    {
        return Utilities.getText(driver,sauceLabsBackpackProductLabel);
    }

    public String getSauceLabsBackpackProductPrice()
    {
        return Utilities.getText(driver,sauceLabsBackpackProductPrice);
    }

    public String[] getProductDetails()
    {
        return new String[]{getSauceLabsBackpackProductLabel(),getSauceLabsBackpackProductPrice()};
    }

    public P03_CartPage clickOnCartIcon ()
    {
        Utilities.clickingOnElement(driver,cartIcon);
        return new P03_CartPage(driver);
    }

}

