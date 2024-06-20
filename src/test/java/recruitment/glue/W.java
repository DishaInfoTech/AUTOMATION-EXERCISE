package recruitment.glue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

/**
 * The W class is a singleton class designed to manage a WebDriver instance  for ChromeDriver throughout the application.
 */

public class W {
    private static W instance = null;

    //The get() method ensures that only one instance of W exists throughout the application.
    public static W get() {
        // If instance is null, it creates a new instance; otherwise, it returns the existing one.
        if (instance == null) {
            instance = new W();
        }
        return instance;
    }

    protected WebDriver driver;

    private W() {
        String pathToDriver = System.getProperty("user.dir") + "/src/test/resources/propertiesfiles/config.properties";
        if (pathToDriver == null || pathToDriver.isEmpty()) {
            throw new RuntimeException("define a path to the chrome driver using system property 'webdriver.chrome.driver'");
        }

        // Initialize ChromeDriver
        driver = new ChromeDriver();
        // Configure WebDriver timeouts and window maximize
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    // Close() releases resources by closing the WebDriver instance
    public static void close() {
        if (instance != null) {
            instance.driver.close();
            instance = null;
        }
    }

}
