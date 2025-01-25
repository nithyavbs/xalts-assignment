import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AuthTests {
	WebDriver driver;

	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://xaltsocnportal.web.app");
	}
	@AfterMethod
	public void teardown() {
		if (driver != null) {
			driver.quit();        }
	}

	 @Test
	    public void testSignUpSuccess() throws InterruptedException {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement signIn= driver.findElement(By.xpath("(//button[normalize-space()='Sign In'])[1]")); 
			signIn.click();
			//	 Navigate to Sign In page
			//  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			//  wait.until(ExpectedConditions.elementToBeClickable(signIn));
			WebElement signInLink= driver.findElement(By.xpath("(//button[normalize-space()='Already have an account? Click here to sign in.'])[1]"));
			signInLink.click();

			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@id='outlined-basic'])[1]")).sendKeys("qa@yopmail.com");
			driver.findElement(By.xpath("(//input[@id='outlined-basic'])[2]")).sendKeys("Test@1234");

			WebElement signInBtn =driver.findElement(By.xpath("//button[normalize-space()='Sign In']"));
			signInBtn.click();
			//		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
			//		wait1.until(ExpectedConditions.elementToBeClickable(signInBtn));
			Thread.sleep(1000);
			WebElement clickStartedBtn = driver.findElement(By.xpath("//button[normalize-space()='Get Started']"));
			Assert.assertTrue(clickStartedBtn.isDisplayed(), "Welcome message is not displayed.");
			Assert.assertEquals(clickStartedBtn.getText(), "GET STARTED");
	    }

	    @Test
	    public void testSignUpFailure() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        
	        driver.findElement(By.xpath("//button[normalize-space()='Get Started']")).click(); // Navigate to Sign Up page

	        // Enter invalid email format
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='outlined-basic'])[1]"))).sendKeys("testuseryopmail.com");
	        driver.findElement(By.xpath("(//input[@id='outlined-basic'])[2]")).sendKeys("Password@123!");
	        driver.findElement(By.xpath("(//input[@id='outlined-basic'])[3]")).sendKeys("Password@123!");

	        // Check if the "Sign Up" button is disabled
	        WebElement signUpButton = driver.findElement(By.xpath("(//button[normalize-space()='Sign Up'])[1]"));
	        Assert.assertFalse(signUpButton.isEnabled(), "Sign up button should be disabled for invalid input.");
	    }

	    @Test
	    public void testSignUpPasswordValidation() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	        driver.findElement(By.xpath("//button[normalize-space()='Get Started']")).click(); // Navigate to Sign Up page

	        // Test invalid passwords
	        String[] invalidPasswords = {"password", "PASSWORD", "12345678", "Pass1234"};
	        for (String password : invalidPasswords) {
	            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='outlined-basic'])[2]")));
	            WebElement confirmPasswordField = driver.findElement(By.xpath("(//input[@id='outlined-basic'])[3]"));

	            passwordField.clear();
	            confirmPasswordField.clear();
	            passwordField.sendKeys(password);
	            confirmPasswordField.sendKeys(password);

	            WebElement signUpButton = driver.findElement(By.xpath("(//button[normalize-space()='Sign Up'])[1]"));
	            Assert.assertFalse(signUpButton.isEnabled(), "Sign up button should be disabled for invalid password: " + password);
	        }
	    }

	    @Test
	    public void testSignUpPasswordMismatch() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	        driver.findElement(By.xpath("//button[normalize-space()='Get Started']")).click(); // Navigate to Sign Up page

	        // Enter passwords that do not match
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='outlined-basic'])[1]"))).sendKeys("testuser@yopmail.com");
	        driver.findElement(By.xpath("(//input[@id='outlined-basic'])[2]")).sendKeys("Password123");
	        driver.findElement(By.xpath("(//input[@id='outlined-basic'])[3]")).sendKeys("Mismatch123");

	        // Verify error message
	        WebElement errorMessage = driver.findElement(By.xpath("//p[@id='outlined-basic-helper-text']"));
	        Assert.assertTrue(errorMessage.isDisplayed(), "Error message for password mismatch is not displayed.");
	        Assert.assertEquals(errorMessage.getText(), "Password must contain atelast one lowercase letter, uppercase letter, number and special character and be a minimum of 8 characters in length"
					, "Unexpected error message for password mismatch.");
	    }

	    @Test
	    public void testSignInFailure() throws InterruptedException {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement signIn= driver.findElement(By.xpath("(//button[normalize-space()='Sign In'])[1]")); 
			signIn.click();
			//	 Navigate to Sign In page
			WebElement signInLink= driver.findElement(By.xpath("(//button[normalize-space()='Already have an account? Click here to sign in.'])[1]"));
			signInLink.click();

			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@id='outlined-basic'])[1]")).sendKeys("1@yopmail.com");
			driver.findElement(By.xpath("(//input[@id='outlined-basic'])[2]")).sendKeys("Test@1234");

			WebElement signInBtn =driver.findElement(By.xpath("//button[normalize-space()='Sign In']"));
			signInBtn.click();
			//		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
			//		wait1.until(ExpectedConditions.elementToBeClickable(signInBtn));
			Thread.sleep(1000);
			// Switch to the alert box
			Alert alert = driver.switchTo().alert();
			// Get the text of the alert
			String alertText = alert.getText();

			// Assert that the alert text is "User not found"
			Assert.assertEquals(alertText, "User not found", "Alert message is incorrect!");

			// Click 'OK' to accept the alert
			alert.accept();

			System.out.println("Alert message validated and OK clicked.");
	    }

	    @Test
	    public void testSignOut() throws InterruptedException {
	    	// Assuming the user is already signed in
			WebElement signIn= driver.findElement(By.xpath("(//button[normalize-space()='Sign In'])[1]")); 
			signIn.click();
			WebElement signInLink= driver.findElement(By.xpath("(//button[normalize-space()='Already have an account? Click here to sign in.'])[1]"));
			signInLink.click();

			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@id='outlined-basic'])[1]")).sendKeys("qa@yopmail.com");
			driver.findElement(By.xpath("(//input[@id='outlined-basic'])[2]")).sendKeys("Test@1234");

			WebElement signInBtn =driver.findElement(By.xpath("(//button[normalize-space()='Sign In'])[1]"));
			signInBtn.click();

			// Locate and click the "Sign Out" button
			WebElement signOutButton = driver.findElement(By.xpath("(//button[normalize-space()='Sign Out'])[1]"));
			signOutButton.click();

			// Create a WebDriverWait instance
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

			try {
				// Wait until the "Sign In" button is visible
				WebElement signInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("(//button[normalize-space()='Sign In'])[1]")));

				// Assert that the "Sign In" button is displayed
				Assert.assertTrue(signInButton.isDisplayed(), "Sign in button is not displayed after signing out.");
			} catch (StaleElementReferenceException e) {
				// Handle stale element by re-locating and waiting again
				System.out.println("StaleElementReferenceException occurred. Retrying...");
				WebElement signInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("(//button[normalize-space()='Sign In'])[1]")));

				// Assert again
				Assert.assertTrue(signInButton.isDisplayed(), "Sign in button is not displayed after retrying.");
	    }}
	}

