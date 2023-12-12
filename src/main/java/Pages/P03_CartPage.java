package Pages;

import Utilities.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P03_CartPage {

    WebDriver driver;
    private final By sauceLabsBackpackProductLabel= By.id("item_4_title_link");

    private final By sauceLabsBackpackProductPrice = By.xpath("(//div[@class='inventory_item_price'])[1]");
    public P03_CartPage(WebDriver driver)
    {
        this.driver=driver;
    }
    public String getSauceLabsBackpackProductLabel()
    {
        return Utilities.getText(driver,sauceLabsBackpackProductLabel);
    }

    public String getSauceLabsBackpackProductPrice()
    {
        return Utilities.getText(driver,sauceLabsBackpackProductPrice);
    }
    public Boolean verifyProductLabel(String label)
    {
        return getSauceLabsBackpackProductLabel().equals(label);
    }
    public Boolean verifyProductPrice(String price)
    {
        return getSauceLabsBackpackProductPrice().equals(price);
    }
}
