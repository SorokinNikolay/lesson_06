package PageObject.PageSteps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static PageObject.PageElements.MainElem.*;
import static com.codeborne.selenide.Selenide.*;

public class MainSteps {

    @Step("Проверяем успешность авторизации")
    public static void isOpened() {
        $x(projectButton).shouldBe(Condition.visible);
    }

    @Step("Переходим в список задач")
    public static void goToProjectPage() {
        $x(projectButton).click();
        $x(testProjectButton).click();
        $x(tasks).shouldHave(Condition.text("Список задач")).click();
    }

    @Step("Проверяем, что количество задач в шапке соответствует реальному количеству заведённых задач")
    public static void compareTasks() {

        int pval = Integer.parseInt($x(problemValue).text().split(" ")[0]);
        Allure.addAttachment("Количество задач в шапке проекта", String.valueOf(pval));

        int xval = $$(By.xpath("//div[@class='ghx-issue-content']")).size();
        Allure.addAttachment("Количество заведённых задач", String.valueOf(xval));
        Assertions.assertEquals(pval, xval);
    }

    @Step("Создаём задачу и переходим в неё")
    public static void createAndGoToTask() {
        $x(createButton).click();
        $x(topic).setValue("Сорокин Николай");
        $(By.xpath("//*[@id='aui-uid-1']")).click();
        $x(description).setValue("Тестовое описание");
        $x(createTaskButton).click();

        $(By.xpath("//*[@id='aui-flag-container']//a")).click();
    }

}
