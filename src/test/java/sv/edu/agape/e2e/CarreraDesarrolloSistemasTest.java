package sv.edu.agape.e2e;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@ExtendWith(AllureJunit5.class)
class CarreraDesarrolloSistemasTest {

    private static final String URL = "https://esfe.agape.edu.sv/carrera-desarrollo-de-sistemas/";
    private static final String TEXTO_ESPERADO =
            "Titulo Obtenido: Técnico en Ingeniería de Desarrollo de Sistemas";
    private static final String TEXTO_MISION = "Misión";

    @Test
    @Epic("ESFE Agape")
    @Feature("Oferta académica")
    @Story("Título obtenido")
    @DisplayName("Muestra el título obtenido de la carrera de Desarrollo de Sistemas")
    void muestraTituloObtenido() {
        WebDriver driver = nuevoDriverChrome();
        try {
            driver.get(URL);

            String textoVisible = new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(webDriver -> {
                        String texto = webDriver.findElement(By.tagName("body"))
                                .getText()
                                .replaceAll("\\s+", " ")
                                .trim();
                        return texto.contains(TEXTO_ESPERADO) ? texto : null;
                    });

            assertTrue(textoVisible.contains(TEXTO_ESPERADO),
                    "No se encontró el texto esperado: " + TEXTO_ESPERADO);
        } finally {
            driver.quit();
        }
    }

    @Test
    @Epic("ESFE Agape")
    @Feature("Información institucional")
    @Story("Misión institucional")
    @DisplayName("Abre Nosotros desde Institución y muestra la misión")
    void abreNosotrosYCompruebaMision() {
        WebDriver driver = nuevoDriverChrome();
        try {
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            WebElement menuInstitucion = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[normalize-space()='INSTITUCIÓN']")));
            new Actions(driver).moveToElement(menuInstitucion).perform();

            WebElement enlaceNosotros = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[normalize-space()='Nosotros']")));
            enlaceNosotros.click();
                wait.until(ExpectedConditions.urlContains("/nosotros/"));

            String textoVisible = wait.until(webDriver -> {
                String texto = webDriver.findElement(By.tagName("body"))
                        .getText()
                        .replaceAll("\\s+", " ")
                        .trim();
                return texto.contains(TEXTO_MISION) ? texto : null;
            });

            assertTrue(textoVisible.contains(TEXTO_MISION),
                    "No se encontró el texto esperado: " + TEXTO_MISION);
        } finally {
            driver.quit();
        }
    }

    private WebDriver nuevoDriverChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--headless=new",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--window-size=1440,1000");
        return new ChromeDriver(options);
    }
}