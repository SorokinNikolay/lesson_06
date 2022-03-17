package PageObject.PageSteps;


import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;

import static Utils.PropertiesReader.getFromProperties;
import static PageObject.PageElements.AuthorizationElem.*;
import static com.codeborne.selenide.Selenide.*;

public class AuthorizationSteps {

    @Step("Открываем страницу по ссылке {url}")
    public static void openUrl(String url) {
        open(url);
    }

    @Step("Пробуем авторизоваться под логином {login}")
    public static void autorisation(String login) {
        $x(loginField).sendKeys(login);
        $x(loginField).pressTab();
        $x(passwordField).sendKeys(getFromProperties("jiraPassword"));
        $x(loginButton).click();
    }
}
