package PageObject.PageSteps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import java.io.IOException;

import static PageObject.PageElements.TaskElem.*;
import static hooks.WebSettings.getScreenshoot;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.screenshot;

public class TaskSteps {

    @Step("Назначаем задаче статус \"В процессе\"")
    public static void inProgress() {
        $x(inProgressButton).click();
    }

    @Step("Назначаем задаче статус \"Готово\"")
    public static void done() {
        $x(businessProcessButton).click();
        $x(doneButton).click();
    }

    @Step("Проверяем, что статус задачи {stat}")
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
