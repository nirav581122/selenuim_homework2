package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestSuit {
    protected static WebDriver driver;
    static String expectedRegisterCompleteMassage = "Registration completed";
    static String expectedEmailShownOnWebPage = "customers can use email a friend feature";
    static String expectedClearListMassageOnWebPage = "You have no items to compare.";
    static String expectedCommunityMessage = "All users can vote.";
    static String expectedEmailMassageOnWebPage = " message has been sent.";
    static String expectedVotes = "vot(s)...";

    public static void clickOnElement(By by) {
        driver.findElement(by).click();
    }

    public static void typeText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    public static String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    public static long timestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }

    @BeforeMethod
    public static void openBrowser() {
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterMethod
    public static void tearDown() {
        driver.close();
    }


    @Test
    public static void verifyUserShouldBeAbleToRegisterSuccessfully() {
        clickOnElement(By.className("ico-register"));
        //Enter firstName
        typeText(By.id("FirstName"), "test");
        //Enter SecondName
        typeText(By.id("LastName"), "rem");
        //Enter Email id
        typeText(By.id("Email"), "abc@" + timestamp() + "gmail.com");
        //enter Password
        typeText(By.id("Password"), "123456");
        //Confirm password
        typeText(By.id("ConfirmPassword"), "123456");
        // //Click on Register submit button
        clickOnElement(By.id("register-button"));
        //getting text from result page
        String actualMassage = getTextFromElement(By.className("result"));
        System.out.println("My actual Result is:" + actualMassage);
        Assert.assertEquals(actualMassage, expectedRegisterCompleteMassage, "Registration completed");
    }

    @Test
    public static void verifyEmailAFriendWithoutRegistration() {
        clickOnElement(By.linkText("Apple MacBook Pro 13-inch"));
        clickOnElement(By.className("email-a-friend"));
        typeText(By.className("friend-email"), "asd@" + timestamp() + "gmail.com");
        typeText(By.className("your-email"), "pdf@" + timestamp() + "gmail.com");
        typeText(By.id("PersonalMessage"), "please check this mail");
        clickOnElement(By.name("send-email"));
        String actualMassageWillAppearOnPage = getTextFromElement(By.xpath("//li[contains(text(),'Only registered customers can use email a friend f')]"));
        System.out.println();
        System.out.println("My Actual email is:" + actualMassageWillAppearOnPage);
        Assert.assertEquals(actualMassageWillAppearOnPage, expectedEmailShownOnWebPage, "Can't Send Email without Registration");

    }

    @Test
    public static void verifingAddToComapreList() {
        // clicking on add to compare list for HTC One M8 Android L 5.0 Lollipop on Homepage
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[1]/div[4]/div[2]/div[3]/div[1]/div[2]/div[3]/div[2]/button[2]"));
        // clicking on add to compare list for $25 Virtual Gift Card on Homepage
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[1]/div[4]/div[2]/div[4]/div[1]/div[2]/div[3]/div[2]/button[2]"));
        //clicking on product comparison page
        clickOnElement(By.linkText("product comparison"));
        //clicking on clear list button
        clickOnElement(By.className("clear-list"));
        //Getting text from webpage for assertion
        String actualTextFromElement = getTextFromElement(By.className("no-data"));
        System.out.println("Email massage display:" + actualTextFromElement);
        Assert.assertEquals(actualTextFromElement, expectedClearListMassageOnWebPage, "Email Can't send Without Registration");
    }

    @Test
    public static void VerifyCommunityPole() {
        //Click on community pole feedback button
        clickOnElement(By.id("pollanswers-2"));
        //click on vote button
        clickOnElement(By.id("vote-poll-1"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String actualVoteMassage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("poll-vote-error"))).getText();
        System.out.println("Actual vote Massage::" + actualVoteMassage);
        Assert.assertEquals(actualVoteMassage, expectedCommunityMessage, "Unsuccessful to vote");
    }

    @Test
    public static void verifyingClickedProductInBasket() {
        //Clicking on Electronics
        clickOnElement(By.linkText("Electronics"));
        //Clicking on camera and photo
        clickOnElement(By.linkText("Camera & photo"));
        //Clicking on Leica T Mirrorless Digital Camera and storing in actual product variable
        String actualProductBeforeAddingToBasket = getTextFromElement(By.linkText("Leica T Mirrorless Digital Camera"));
        clickOnElement(By.linkText("Leica T Mirrorless Digital Camera"));
        //Adding product in add to cart
        clickOnElement(By.id("add-to-cart-button-16"));
        //Clicking on Shopping cart
        clickOnElement(By.linkText("Shopping cart"));
        //storing add to basket element in product in basket variable
        String actualProductInBasket = getTextFromElement(By.xpath("//*[@id=\"shopping-cart-form\"]/div[1]/table/tbody/tr/td[3]"));
        System.out.println("product In Basket:" + actualProductInBasket);
        //Checking Actual product And add to basket product name is Same or not
        Assert.assertEquals(actualProductBeforeAddingToBasket, actualProductInBasket, "Before adding to basket and After adding to the basket Both product is same");
    }

    @Test
    public static void verifyRegisterUserCanReferAnFriend() {
        clickOnElement(By.className("ico-register"));
        //Enter firstName
        typeText(By.id("FirstName"), "test");
        //Enter SecondName
        typeText(By.id("LastName"), "rem");
        //Enter Email id
        typeText(By.id("Email"), "abc111@gmail.com");
        //enter Password
        typeText(By.id("Password"), "123456");
        //Confirm password
        typeText(By.id("ConfirmPassword"), "123456");
        // //Click on Register submit button
        clickOnElement(By.id("register-button"));
        ////click on log In button
        clickOnElement(By.className("ico-login"));
        //Enter Email id
        typeText(By.id("Email"), "abc111@gmail.com");
        //Enter Password
        typeText(By.id("Password"), "123456");
        //click on Log in Button
        clickOnElement(By.xpath("//button[contains(text(),'Log in')]"));
        //click on Product
//        clickOnElement(By.linkText("Apple MacBook Pro 13-inch"));
        clickOnElement(By.xpath("//div[@class='product-grid home-page-product-grid']/div[2]/div[2]/div[1]/div[2]/div[3]/div[2]/button[1]"));
        //Click on Email a friend button
        clickOnElement(By.className("email-a-friend"));
        //friend Email id
        typeText(By.className("friend-email"), "asd@" + timestamp() + "gmail.com");
        //Personal Message
        typeText(By.id("PersonalMessage"), "please check this mail");
        //Click on send Button
        clickOnElement(By.name("send-email"));
        //storing data in variable from the webpage
        String actualMassageOnEmailAFriendPage = getTextFromElement(By.className("result"));
        System.out.println("Actual Massage for sending Email a friend:" + actualMassageOnEmailAFriendPage);
        //checking actual massage from web page and expected condition is matching
        Assert.assertEquals(actualMassageOnEmailAFriendPage, expectedEmailMassageOnWebPage, "Email A friend Is Unsuccessful");
    }

    @Test
    public static void verifyRegisterUserVoteSuccessfully() {
        clickOnElement(By.className("ico-register"));
        //Enter firstName
        typeText(By.id("FirstName"), "test");
        //Enter SecondName
        typeText(By.id("LastName"), "rem");
        //Enter Email id
        typeText(By.id("Email"), "abc22@gmail.com");
        //enter Password
        typeText(By.id("Password"), "123456");
        //Confirm password
        typeText(By.id("ConfirmPassword"), "123456");
        // //Click on Register submit button
        clickOnElement(By.id("register-button"));
        ////click on log In button
        clickOnElement(By.className("ico-login"));
        //Enter Email id
        typeText(By.id("Email"), "abc22@gmail.com");
        //Enter Password
        typeText(By.id("Password"), "123456");
        //click on Log in Button
        clickOnElement(By.xpath("//button[contains(text(),'Log in')]"));
        //Click on community pole feedback button
        clickOnElement(By.id("pollanswers-2"));
        //click on vote button
        clickOnElement(By.id("vote-poll-1"));
        String actualVoteTotalMassage = getTextFromElement(By.className("poll-total-votes"));
        System.out.println("Total Votes:" + actualVoteTotalMassage);
        Assert.assertEquals(actualVoteTotalMassage, expectedVotes, "Wrong Vots massage ");

    }
}






