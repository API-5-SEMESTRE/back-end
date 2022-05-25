package api.theVelopers.sas.integration;

import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import libraries.WebDriverLibrary;

public class IntegracaoTest {

	private WebDriver webDriver;
	private String baseUrl = "https://score-analysis-system-front.herokuapp.com/#/";
	
	
	@Before
	public void setUp() {
		WebDriverLibrary driver = new WebDriverLibrary();
		webDriver = driver.getChromeDriver();
	}
	
	@Test
	public void testeIntegracao() {

		webDriver.get(baseUrl);

		String title = webDriver.getTitle();
		

		assertTrue(StringUtils.equals(title, "Gestão Usuário"));
		
		// Fechando o navegador
		webDriver.close();
	}
	
}