import com.google.gson.Gson;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class AutomacaoTexoStep {

    private WebDriver driver;
    private DSL dsl;
    private JSONArray jsonArray;

    @Given("Que initialize o navegador e acesso o site recomendado")
    public void queInitializeONavegadorEAcessoOSiteRecomendado() {

            driver = new ChromeDriver();
            driver.manage().window().setSize(new Dimension(1200, 765));
            driver.get("https://jsonplaceholder.typicode.com/");
            dsl = new DSL(driver);
    }

    @When("Eu access o menu Guide")
    public void euAccessOMenuGuide() {
            driver.findElement(By.xpath("//a[text()='Guide']")).click();

        // Capturar a evidencia
        SalvarEvidencia("menu_Guide");
        }


    @And("Eu nave até o link de fotos")
    public void euNaveAtéOLinkDeFotos() {
        WebElement Element = driver.findElement(By.xpath("//a[text()='/albums/1/photos']"));


        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView();", Element);

        Element.click();

        // Capturar a Evidencia
        SalvarEvidencia("navigate_to_photos_link");
    }

    @Then("Eu capture todos os dados exibidos em tela e os salvo em um array JSON validando o ID = {int}")
    public void euCaptureTodosOsDadosExibidosEmTelaEOsSalvoEmUmArrayJSONValidandoOID(int arg0) {
        // Localizar a tag <pre>
        WebElement preTag = driver.findElement(By.tagName("pre"));

        // Extrair o texto da tag <pre>
        String preText = preTag.getText();

        // Analisar o texto extraído para converter em um JSONArray
        jsonArray = new JSONArray(preText);

        // Exibir o JSONArray
        System.out.println ("Segue abaixo Json Criado");

        System.out.println(jsonArray.toString());

        Gson gson = new Gson();
        ObjetoJson[] objects = gson.fromJson(String.valueOf(jsonArray), ObjetoJson[].class);

        // Validar os campos
        for (ObjetoJson obj : objects) {
            if (obj.getId() == 6 && obj.getAlbumId() == 1
                    && obj.getTitle().equals("accusamus ea aliquid et amet sequi nemo")
                    && obj.getUrl().equals("https://via.placeholder.com/600/56a8c2")
                    && obj.getThumbnailUrl().equals("https://via.placeholder.com/150/56a8c2")) {

                System.out.println("Validado com Sucesso itens do id 6");
                break;
            }

        }
    }

    // Método para capturar e salvar evidencias
    private void SalvarEvidencia(String filename) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(sourceFile, new File(filename + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

