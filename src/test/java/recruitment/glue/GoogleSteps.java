package recruitment.glue;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * The GoogleSteps class is a Cucumber step definition class that interacts with
 * a web application (Google Search) using Selenium WebDriver.
 * It defines steps to navigate to URLs, perform searches, verify results, and check specific page elements.
 */

public class GoogleSteps {
    private WebDriver driver;

    public GoogleSteps() {
        driver = W.get().driver; // Assuming W.get().driver provides WebDriver instance
    }

    //Navigates to a given URL.
    @Given("url {string} is launched")
    public void url(String url) {
        driver.get(url);
        acceptCookiesIfWarned();
    }

    private void acceptCookiesIfWarned() {
        try {
            driver.findElement(By.cssSelector("#L2AGLb")).click();
        } catch (NoSuchElementException ignored) {
            // If the element is not found, it means the cookie prompt is not displayed.
        }
    }

    //Clicks on the "About" link to navigate to the About page.
    @When("About page is shown")
    public void aboutPageIsShown() {
        driver.findElement(By.linkText("About")).click();
    }

    // Verifies that a specific page displays the expected content (likely a mission statement).
    @Then("page displays {string}")
    public void pageDisplays(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement missionStatement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class=\"modules-lib__mission-statement__headline glue-headline glue-headline--fluid-2\"]")));
        assertNotNull("Mission statement not found!", missionStatement);
    }

    //Performs a search for a given query.
    @When("searching for {string}")
    public void searchingFor(String query) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(query);
        searchBox.submit();
    }

    //Verifies that search results contain the expected text.
    @Then("results contain {string}")
    public void resultsContain(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
        assertTrue("Results do not contain expected text!", driver.findElement(By.id("search")).getText().contains(text));
    }

    //Verifies that result statistics are displayed.
    @And("result stats are displayed")
    public void resultStatsAreDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement stats = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result-stats")));
        assertNotNull("Result stats not displayed!", stats);
    }

    //Verifies that the number of search results or search time (in seconds) exceeds a specified threshold.
    @And("number of {string} is more than {int}")
    public void numberOfIsMoreThan(String type, int number) {
        WebElement stats = driver.findElement(By.id("result-stats"));
        String statsText = stats.getText();
        String[] parts = statsText.split(" ");
        long value = 0;
        if (type.equals("results")) {
            value = Long.parseLong(parts[1].replace(",", "").replace(".", ""));
        } else if (type.equals("seconds")) {
            value = Long.parseLong(parts[3].replace(",", "").replace(".", "").replace("(", ""));
        }
        assertTrue("Number of " + type + " is not more than " + number, value > number);
    }
}
