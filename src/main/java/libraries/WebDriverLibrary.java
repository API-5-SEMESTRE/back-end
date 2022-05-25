package libraries;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.bonigarcia.wdm.WebDriverManager;

@Configuration
public class WebDriverLibrary {
	
	@Bean
	public WebDriver getChromeDriver() {
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();

		// Setando as configurações do navegador pra ele não iniciar uma renderização
		// visual
		// e desabilitar possiveis elementos que podem gerar erro no teste
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--headless");
		
		return new ChromeDriver(options);
	}
}
