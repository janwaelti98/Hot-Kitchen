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
public class RegistrationTestIT {
    @LocalServerPort
    int port;

    WebDriver driver = new HtmlUnitDriver();

    @Test
    void registerNewUserTest() {
        var registerPage = TestPage.create(driver, port, "/register");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        TestPage.register(driver, "user.test@gmail.com", "testuser", "testpassword");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        assertEquals("http://localhost:" + port + "/login", driver.getCurrentUrl());

        //test if new register user can log in
        var loginPage = TestPage.create(driver, port, "/login");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        TestPage.login(driver, "testuser", "testpassword");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        assertEquals("http://localhost:" + port + "/", driver.getCurrentUrl());
    }

    @Test
    void registerWithEmptyPasswordTest() {
        var registerPage = TestPage.create(driver, port, "/register");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        TestPage.register(driver, "user.test@gmail.com", "testuser", "");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        var errorMessage = driver.findElement(By.xpath("/html/body/div/div/div/main/div/div/form/div/div[1]/div/div/p"));

        assertEquals("http://localhost:" + port + "/register", driver.getCurrentUrl());
        assertEquals("Password can not be empty!", errorMessage.getText());
    }

    @Test
    void registerWithEmptyUserTest() {
        var registerPage = TestPage.create(driver, port, "/register");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        TestPage.register(driver, "user.test@gmail.com", "", "testpassword");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        var errorMessage = driver.findElement(By.xpath("/html/body/div/div/div/main/div/div/form/div/div[1]/div/div/p"));

        assertEquals("http://localhost:" + port + "/register", driver.getCurrentUrl());
        assertEquals("Username can not be empty!", errorMessage.getText());
    }
}
