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
import org.openqa.selenium.support.ui.Select;

public class TestForm1SeleniumFireFox {

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
		driver.findElement(By.linkText("Selenium Test Form1")).click();
		
		driver.findElement(By.id("form-widgets-betreff")).sendKeys("Automatischer Test");
		driver.findElement(By.id("form-widgets-name")).sendKeys("Wannik Jalter");
		
		Select selectAuswahl1 = new Select(driver.findElement(By.id("form-widgets-auswahl1")));
		selectAuswahl1.selectByVisibleText("Java Grundlagen Kurs mit Dieter");
		
		Select selectAuswahl2 = new Select(driver.findElement(By.id("form-widgets-auswahl2-from")));
		selectAuswahl2.selectByIndex(2);
		selectAuswahl2.selectByIndex(4);
		selectAuswahl2.selectByIndex(6);
		
		driver.findElement(By.name("from2toButton")).click();
		
		Select selectAuswahl3 = new Select(driver.findElement(By.id("form-widgets-auswahl2-to")));
		selectAuswahl3.selectByIndex(2);
		
		driver.findElement(By.name("upButton")).click();
		
		
		//Act
		
		driver.findElement(By.name("form.buttons.speichern")).click();
		
		
		
		//Assert
		String erfolgsMeldung = driver.findElement(By.id("message")).getText();
		assertTrue(erfolgsMeldung.contains("Java Grundlagen Kurs"));
		
		String erstesElement = driver.findElement(By.xpath("//ul[@id='companies']/li[1]")).getText();
		assertEquals(erstesElement, "Magazzini Alimentari Riuniti");
	}

}
