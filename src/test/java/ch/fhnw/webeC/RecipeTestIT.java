package ch.fhnw.webeC;

import ch.fhnw.webeC.pages.TestPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@ActiveProfiles("Test")
public class RecipeTestIT {
    @LocalServerPort
    int port;

    private static WebDriver driver;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    public void setupTest() {
        driver = new FirefoxDriver();
    }

    @AfterAll
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void createNewRecipeTest() {
        var page = TestPage.create(driver, port, "/login");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        page.login(driver, "Admin", "admin");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        TestPage.goTo(driver, port, "/recipe");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        var addNewRecipeButton = page.getAddNewRecipeButton();
        assertTrue(addNewRecipeButton.isDisplayed());

        addNewRecipeButton.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        //Fill out recipe form
        page.getRecipeTitleField().clear();
        page.getRecipeTitleField().sendKeys("testRecipeTitle");

        page.getShortDescriptionField().clear();
        page.getShortDescriptionField().sendKeys("testShortDescription");

        page.getEntireTimeField().clear();
        page.getEntireTimeField().sendKeys("60");

        page.getActiveTimeField().clear();
        page.getActiveTimeField().sendKeys("30");

        page.getServingsField().clear();
        page.getServingsField().sendKeys("4");

        page.getCreateRecipeButton().click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        //Get elements from last created recipe card
        var recipeTitle = driver.findElement(By.xpath("/html/body/div/div/div/main/div/div[2]/div[4]/div/a/div/div/h5"));
        var shortDescription = driver.findElement(By.xpath("/html/body/div/div/div/main/div/div[2]/div[4]/div/a/div/div/p"));
        var entireTime = driver.findElement(By.xpath("/html/body/div/div/div/main/div/div[2]/div[4]/div/a/div/div/div/table/tbody/tr[2]/td[1]"));
        var diet = driver.findElement(By.xpath("/html/body/div/div/div/main/div/div[2]/div[4]/div/a/div/div/div/table/tbody/tr[2]/td[2]"));
        var rating = driver.findElement(By.xpath("/html/body/div/div/div/main/div/div[2]/div[4]/div/a/div/div/div/table/tbody/tr[2]/td[3]"));

        assertEquals("testRecipeTitle", recipeTitle.getText());
        assertEquals("testShortDescription", shortDescription.getText());
        assertEquals("60 min", entireTime.getText());
        assertEquals("no diet", diet.getText());
        assertEquals("no ratings yet", rating.getText());
    }

    @Test
    void editRecipeAsNotLoggedInUserTest() {
        var page = TestPage.create(driver, port, "/recipe");

        var firstRecipeThumbnail = page.getRecipeThumbnail().stream().findFirst().get();
        assertTrue(firstRecipeThumbnail.isDisplayed());

        firstRecipeThumbnail.click();

        var recipePage = TestPage.create(driver, port, "/recipe/1");

        var recipeDeleteButton = recipePage.getRecipeDeleteButton();
        var recipeEditButton = recipePage.getRecipeEditButton();

        // As not logged-in user recipe shouldn't be editable / deletable
        assertThrows(NoSuchElementException.class, recipeDeleteButton::isDisplayed);
        assertThrows(NoSuchElementException.class, recipeEditButton::isDisplayed);
    }

    @Test
    void editRecipeAsLoggedInUserTest() {
        var page = TestPage.create(driver, port, "/login");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        page.login(driver,"Admin", "admin");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        TestPage.goTo(driver, port, "/recipe");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        var firstRecipeThumbnail = page.getRecipeThumbnail().stream().findFirst().get();
        assertTrue(firstRecipeThumbnail.isDisplayed());

        firstRecipeThumbnail.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        var recipeDeleteButton = page.getRecipeDeleteButton();
        var recipeEditButton = page.getRecipeEditButton();

        //Check if edit & delete buttons are shown
        assertTrue(recipeDeleteButton.isDisplayed());
        assertTrue(recipeEditButton.isDisplayed());

        recipeEditButton.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        page.getRecipeTitleField().clear();
        page.getRecipeTitleField().sendKeys("testRecipeTitle");

        page.getShortDescriptionField().clear();
        page.getShortDescriptionField().sendKeys("testShortDescription");

        page.getEntireTimeField().clear();
        page.getEntireTimeField().sendKeys("100");

        page.getActiveTimeField().clear();
        page.getActiveTimeField().sendKeys("30");

        page.getSaveRecipeButton().click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        var recipeTitle = driver.findElement(By.xpath("/html/body/div/div/div/main/div/div/div[1]/div/div/div/div/div/div[1]/div[1]/h3"));
        var testShortDescription = driver.findElement(By.xpath("/html/body/div/div/div/main/div/div/div[1]/div/div/div/div/div/div[2]/div/p"));
        var entireTime = driver.findElement(By.xpath("/html/body/div/div/div/main/div/div/div[1]/div/div/div/div/div/div[3]/div[1]/table/tbody/tr[2]/td[2]"));
        var activeTime = driver.findElement(By.xpath("/html/body/div/div/div/main/div/div/div[1]/div/div/div/div/div/div[3]/div[1]/table/tbody/tr[3]/td[2]"));

        assertEquals("testRecipeTitle", recipeTitle.getText());
        assertEquals("testShortDescription", testShortDescription.getText());
        assertEquals("100 min", entireTime.getText());
        assertEquals("30 min", activeTime.getText());
    }
}
