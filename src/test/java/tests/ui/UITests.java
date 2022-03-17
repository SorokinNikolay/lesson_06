package tests.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static PageObject.PageSteps.AuthorizationSteps.*;
import static PageObject.PageSteps.MainSteps.*;
import static PageObject.PageSteps.TaskSteps.*;

public class UITests {

    @Test
    @Tag("Работа с Jira")
    @DisplayName("Проверка количества задач")
    public void Test1() {
        openUrl("https://edujira.ifellow.ru/login.jsp");
        autorisation("sorNikolai");
        isOpened();
        goToProjectPage();
        compareTasks();
    }

    @Test
    @Tag("Работа с Jira")
    @DisplayName("Проверка создания задачи и изменения её статусов")
    public void Test2() {
        openUrl("https://edujira.ifellow.ru/login.jsp");
        autorisation("sorNikolai");
        isOpened();
        createAndGoToTask();
        inProgress();
        done();
        statusIs("ГОТОВО");
    }

}
