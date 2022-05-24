package api.theVelopers.sas.service.impl;

import java.util.concurrent.TimeUnit;
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

    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--headless");
    driver = new ChromeDriver(options);

    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    driver.manage().window().maximize();
    driver.get(baseUrl);
    driver.close();
  }
}