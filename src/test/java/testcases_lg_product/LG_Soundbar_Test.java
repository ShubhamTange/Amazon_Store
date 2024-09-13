package testcases_lg_product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LG_Soundbar_Test {

	@Test
    public void LG_Soundbars() {
        
        //Launch a driver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Open amazon.in
        driver.get("https://www.amazon.in");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Search for LG Soundbar
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("lg soundbar");
        searchBox.submit();

        // Wait for the page to load results
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Collect product names and prices from the first page
        List<WebElement> productNames = driver.findElements(By.cssSelector("span.a-size-medium.a-color-base.a-text-normal"));
        List<WebElement> productPrices = driver.findElements(By.cssSelector("span.a-price-whole"));

        // Store the product name and price as key-value pairs in a Map
        Map<Integer, String> productMap = new HashMap<Integer, String>();

        for (int i = 0; i < productNames.size(); i++) {
            String productName = productNames.get(i).getText();

            // Check if price exists, if not consider it zero
            String priceString = (i < productPrices.size()) ? productPrices.get(i).getText().replace(",", "") : "0";
            int price = 0;

            try {
                price = Integer.parseInt(priceString);
            } catch (NumberFormatException e) {
                // Handle potential parsing errors
                price = 0;
            }

            productMap.put(price, productName);
        }

        // Sort the products by price
        Map<Integer, String> sortedProducts = new TreeMap<Integer, String>(productMap);

        // Print the sorted products with prices
        for (Map.Entry<Integer, String> entry : sortedProducts.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        // Close the browser
        driver.quit();
    }
}
