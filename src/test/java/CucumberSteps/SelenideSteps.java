package CucumberSteps;

import com.codeborne.selenide.Condition;
import io.cucumber.java.ru.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.io.IOException;

import static PageObject.PageElements.AuthorizationElem.*;
import static PageObject.PageElements.MainElem.*;
import static PageObject.PageElements.MainElem.createTaskButton;
import static PageObject.PageElements.TaskElem.*;
import static PageObject.PageElements.TaskElem.status;
import static Utils.PropertiesReader.getFromProperties;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static hooks.WebSettings.getScreenshoot;

public class SelenideSteps {

    @Дано("^открыта страница (.*)$")
    public static void openUrl(String url) {
        open(url);
    }

    @Тогда("^пробуем авторизоваться под логином (.*)$")
    public static void autorisation(String login) {
        $x(loginField).sendKeys(login);
        $x(loginField).pressTab();
        $x(passwordField).sendKeys(getFromProperties("jiraPassword"));
        $x(loginButton).click();
    }

    @Когда("авторизация прошла успешно")
    public static void isOpened() {
        $x(projectButton).shouldBe(Condition.visible);
    }

    @То("переходим в список задач")
    public static void goToProjectPage() {
        $x(projectButton).click();
        $x(testProjectButton).click();
        $x(tasks).shouldHave(Condition.text("Список задач")).click();
    }

    @И("проверяем, что количество задач в шапке соответствует реальному количеству заведённых задач")
    public static void compareTasks() {

        int pval = Integer.parseInt($x(problemValue).text().split(" ")[0]);
        Allure.addAttachment("Количество задач в шапке проекта", String.valueOf(pval));

        int xval = $$(By.xpath("//div[@class='ghx-issue-content']")).size();
        Allure.addAttachment("Количество заведённых задач", String.valueOf(xval));
        Assertions.assertEquals(pval, xval);
    }

    @То("создаём задачу и переходим в неё")
    public static void createAndGoToTask() {
        $x(createButton).click();
        $x(topic).setValue("Сорокин Николай");
        $(By.xpath("//*[@id='aui-uid-1']")).click();
        $x(description).setValue("Тестовое описание");
        $x(createTaskButton).click();

        $(By.xpath("//*[@id='aui-flag-container']//a")).click();
    }

    @И("назначаем задаче статус \"В процессе\"")
    public static void inProgress() {
        $x(inProgressButton).click();
    }

    @Также("назначаем задаче статус \"Готово\"")
    public static void done() {
        $x(businessProcessButton).click();
        $x(doneButton).click();
    }

    @И("^проверяем, что статус задачи (.*)$")
    public static void statusIs(String stat) {
        $x(status).shouldBe(Condition.text(stat));

        screenshot("result.png");
        try {
            getScreenshoot("result.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
