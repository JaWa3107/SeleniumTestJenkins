package de.jawa.seleniumkurs.test;
import static org.junit.Assert.*;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestDragAndDropSeleniumFireFox {

	WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		
		System.out.println("initialisiere Webdriver");
		
		URL linkHub = new URL("http://localhost:4444/wd/hub");
		driver = new RemoteWebDriver(linkHub, DesiredCapabilities.firefox());
		
		//System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
		//driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://seleniumkurs.codingsolo.de");
		
	}

	@After
	public void tearDown() throws Exception {
		
		System.out.println("Test abgeschlossen - ich räume");
		driver.quit();
		
	}

	@Test
	public void test() {
		
		System.out.println("Starte Test Login mit Fehlschlag");
		
		//Arrange
		
		driver.findElement(By.id("__ac_name")).sendKeys("selenium42");
		driver.findElement(By.name("__ac_password")).sendKeys("R5vxI0j60");
		driver.findElement(By.xpath("//input[@value='Anmelden']")).click();
		driver.findElement(By.id("portaltab-burger-menu")).click();
		driver.findElement(By.cssSelector("a[title='Selenium Testapplikationen'] span[class='menu-item-title']")).click();
		driver.findElement(By.linkText("Drag and Drop Beispiel")).click();
		
		//Starte Drag and Drop
		
		WebElement drgLogoWhite = driver.findElement(By.id("white-logo"));
		WebElement drgLogoBlue = driver.findElement(By.id("blue-logo"));
		WebElement drgLogored = driver.findElement(By.id("red-logo"));
		WebElement drgLogoGreen = driver.findElement(By.id("green-logo"));
		
		WebElement drgBox = driver.findElement(By.id("droppable"));
		
		Actions action = new Actions(driver);
		
		
		//Act
		
		action.dragAndDrop(drgLogoWhite, drgBox).build().perform();
		action.dragAndDrop(drgLogoBlue, drgBox).build().perform();
		
		action.dragAndDropBy(drgLogored, 250, 0).build().perform();
		
		action.clickAndHold(drgLogoGreen).perform();
		action.moveByOffset(250, 50).perform();
		action.release(drgLogoGreen).perform();
		
		
		
		
		
		//Assert
		String erfolgsMeldung = driver.findElement(By.cssSelector("div[id='droppable'] p")).getText();
		assertTrue(erfolgsMeldung.contains("green-logo"));
	}

}
