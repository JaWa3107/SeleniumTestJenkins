package de.jawa.seleniumkurs.test;
import static org.junit.Assert.*;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import de.jawa.config.Config;

public class TestNavigationSeleniumFireFox {

	WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		
		System.out.println("initialisiere Webdriver");
		
		URL linkHub = new URL("http://localhost:4444/wd/hub");
		driver = new RemoteWebDriver(linkHub, DesiredCapabilities.firefox());
		
		//System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
		//driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(Config.getBasURL());
		
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
		
		
		//Act
		
		driver.findElement(By.id("portaltab-burger-menu")).click();
		driver.findElement(By.cssSelector("a[title='Selenium Testapplikationen'] span[class='menu-item-title']")).click();
		driver.findElement(By.linkText("Selenium Test Form1")).click();
		
		//Assert
		String erfolgsMeldung = driver.findElement(By.tagName("h1")).getText();
		assertEquals(erfolgsMeldung, "Selenium Test Form1");
	}

}
