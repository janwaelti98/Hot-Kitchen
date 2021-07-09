package ch.fhnw.webeC;

import ch.fhnw.webeC.pages.TestPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@ActiveProfiles("Test")
public class LoginTestIT {
    @LocalServerPort
    int port;

    WebDriver driver = new HtmlUnitDriver();

    @Test
    void loginAsAdminTest() {
        var page = TestPage.create(driver, port, "/login");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        page.login(driver, "Admin", "admin");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        assertEquals("http://localhost:" + port + "/", driver.getCurrentUrl());
    }

    @Test
    void loginAsAuthorTest() {
        var page = TestPage.create(driver, port, "/login");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        page.login(driver, "Author", "author");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        assertEquals("http://localhost:" + port + "/", driver.getCurrentUrl());
    }

    @Test
    void loginAsReaderTest() {
        var page = TestPage.create(driver, port, "/login");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        page.login(driver, "Reader", "reader");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        assertEquals("http://localhost:" + port + "/", driver.getCurrentUrl());
    }

    @Test
    void loginWithWrongCredentialsTest() {
        var page = TestPage.create(driver, port, "/login");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        page.login(driver, "UserWhoNotExists", "wrongPassword");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        var errorMessage = driver.findElement(By.xpath("/html/body/div/div/div/main/div/div/form/div/div[1]/div/div"));

        assertEquals("http://localhost:" + port + "/login-error", driver.getCurrentUrl());
        assertEquals("Bad credentials", errorMessage.getText());
    }
}
