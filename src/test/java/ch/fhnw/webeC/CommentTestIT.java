package ch.fhnw.webeC;

import ch.fhnw.webeC.pages.TestPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@ActiveProfiles("Test")
public class CommentTestIT {
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
    void writeCommentAsLoggedInUserTest() {
        //login as normal user
        var page = TestPage.create(driver, port, "/login");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        page.login(driver, "Reader", "reader");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        //navigate to recipe-page
        TestPage.goTo(driver, port, "/recipe");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        var firstRecipeThumbnail = page.getRecipeThumbnail().stream().findFirst().get();
        assertTrue(firstRecipeThumbnail.isDisplayed());

        firstRecipeThumbnail.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        var commentTextArea = page.getCommentTextArea();
        var commentButton = page.getCommentButton();

        //Check if comment textarea & comment button are shown
        assertTrue(commentTextArea.isDisplayed());
        assertTrue(commentButton.isDisplayed());

        page.getCommentTextArea().sendKeys("This is a test comment");
        page.getCommentButton().click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        var comment = driver.findElement(By.xpath("/html/body/div/div/div/main/div/div/div[2]/div/div[3]/div/div/div/p[1]"));

        assertEquals("This is a test comment", comment.getText());
    }
}
