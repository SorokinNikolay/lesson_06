package hooks;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class WebSettings {

    @BeforeAll
    static void setDriver() {
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver", "/src/test/resources/chromedriver2");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
    }

    @BeforeAll
    static void setAllure() {
        new AllureSelenide()
                .screenshots(true)
                .savePageSource(false);
    }

    @AfterAll
    static void closeDriver() {
        closeWebDriver();
    }

    @Attachment(value = "Скриншот")
    public static byte[] getScreenshoot(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get("/Users/sorokin/IdeaProjects/lesson_03/build/reports/tests/",
                resourceName));
    }

}
