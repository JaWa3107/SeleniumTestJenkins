package de.jawa.seleniumkurs.test;
import static org.junit.Assert.*;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import de.jawa.seleniumkurs.pages.SeleniumKursLoginPage;

public class TestLoginSeleniumFireFox {

	WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		
		System.out.println("initialisiere Webdriver");
		
		URL linkHub = new URL("http://localhost:4444/wd/hub");
		
		driver = new RemoteWebDriver(linkHub, DesiredCapabilities.firefox());
		
		//System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
		//driver = new FirefoxDriver();
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
		
		SeleniumKursLoginPage loginPage = new SeleniumKursLoginPage(driver);
		
		loginPage.zugangsdatenEingeben("selenium42", "R5vxI0j60");
	
		//Act
		
		loginPage.LoginButtonAnklicken();
		
		//driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		//Assert
		String erfolgsMeldung = loginPage.statusMeldungAuslesen();
		assertTrue(erfolgsMeldung.contains("Willkommen"));
	}

}
