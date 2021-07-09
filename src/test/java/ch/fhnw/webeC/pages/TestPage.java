package ch.fhnw.webeC.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TestPage {

    private static final String URL = "http://localhost:%d%s";

    public static void login(WebDriver driver, String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("btnLogin")).click();
    }

    public static void register(WebDriver driver, String email, String username, String password) {
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("userName")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("btnRegister")).click();
    }

    public static TestPage create(WebDriver driver, int port, String path) {
        driver.get(URL.formatted(port, path));

        return PageFactory.initElements(driver, TestPage.class);
    }

    public static void goTo(WebDriver driver, int port, String path) {
        driver.get(URL.formatted(port, path));
    }

    /* Login page */
    @FindBy(id="username")
    private WebElement usernameField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(id="loginButton")
    private WebElement loginButton;

    /* Recipe page */
    @FindBy(id="addNewRecipeButton")
    private WebElement addNewRecipeButton;

    @FindBy(className = "recipeThumbnail")
    private List<WebElement> recipeThumbnail;

    /* Recipe details page */
    @FindBy(id = "recipeEditButton")
    private WebElement recipeEditButton;

    @FindBy(id = "recipeDeleteButton")
    private WebElement recipeDeleteButton;

    @FindBy(id = "commentText")
    private WebElement commentTextArea;

    @FindBy(id = "btnComment")
    private WebElement commentButton;

    /* Add recipe page */
    @FindBy(id="recipeTitle")
    private WebElement recipeTitleField;

    @FindBy(id="shortDescription")
    private WebElement shortDescriptionField;

    @FindBy(id="entireTime")
    private WebElement entireTimeField;

    @FindBy(id="activeTime")
    private WebElement activeTimeField;

    @FindBy(id="servings")
    private WebElement servingsField;

    @FindBy(id="createRecipeButton")
    private WebElement createRecipeButton;

    @FindBy(id="diet")
    private WebElement dietSelection;

    @FindBy(id="equipment^")
    private List<WebElement> equipments;

    /* Edit recipe page */
    @FindBy(id="saveRecipeButton")
    private WebElement saveRecipeButton;


    //Getter
    public WebElement getUsernameField() {
        return usernameField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public static String getURL() {
        return URL;
    }

    public WebElement getAddNewRecipeButton() {
        return addNewRecipeButton;
    }

    public WebElement getRecipeTitleField() {
        return recipeTitleField;
    }

    public WebElement getShortDescriptionField() {
        return shortDescriptionField;
    }

    public WebElement getEntireTimeField() {
        return entireTimeField;
    }

    public WebElement getActiveTimeField() {
        return activeTimeField;
    }

    public WebElement getServingsField() {
        return servingsField;
    }

    public WebElement getCreateRecipeButton() {
        return createRecipeButton;
    }

    public List<WebElement> getRecipeThumbnail() {
        return recipeThumbnail;
    }

    public WebElement getRecipeEditButton() {
        return recipeEditButton;
    }

    public WebElement getRecipeDeleteButton() {
        return recipeDeleteButton;
    }

    public WebElement getDietSelection() {
        return dietSelection;
    }

    public List<WebElement> getEquipments() {
        return equipments;
    }

    public WebElement getSaveRecipeButton() {
        return saveRecipeButton;
    }

    public WebElement getCommentTextArea() {
        return commentTextArea;
    }

    public WebElement getCommentButton() {
        return commentButton;
    }
}
