package api.theVelopers.sas.integration;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IntegracaoTest
{
  public WebDriver driver;
  public String baseUrl = "https://score-analysis-system-front.herokuapp.com/#/";

  @Test
  public void testeIntegracao() {

    // Setando o WebDriverManager que substitui o chromedriver
    WebDriverManager.chromedriver().setup();

    // Criando um objeto pra setar as configurações do navegador
    ChromeOptions options = new ChromeOptions();

    // Setando as configurações do navegador pra ele não iniciar uma renderização visual
    // e desabilitar possiveis elementos que podem gerar erro no teste
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--headless");

    // Criando um objeto e setando as configurações do navegador
    driver = new ChromeDriver(options);

    // Abrindo o navegador com a url informada
    driver.get(baseUrl);

    // Fechando o navegador
    driver.close();
  }
}