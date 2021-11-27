package de.jawa.seleniumkurs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SeleniumKursLoginPage {
	
	private WebDriver driver;
	
	private By inputBenutzerName = By.cssSelector("input.form-control[type='text']");
	private By inputPasswort = By.cssSelector("input.form-control[type='password']");
	private By btnAnmeldung = By.cssSelector("input.btn.btn-primary");
	private By statusMeldung = By.cssSelector("div.portalMessage:nth-child(1)");
	
	public SeleniumKursLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void zugangsdatenEingeben(String benutzername, String passwort) {
		
		this.driver.findElement(inputBenutzerName).sendKeys(benutzername);
		this.driver.findElement(inputPasswort).sendKeys(passwort);
		
	}
	
	public void LoginButtonAnklicken() {
		
		this.driver.findElement(btnAnmeldung).click();
		
	}
	
	public String statusMeldungAuslesen() {
		return this.driver.findElement(statusMeldung).getText();
	}
}
