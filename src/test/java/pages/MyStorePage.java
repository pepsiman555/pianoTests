package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class MyStorePage {

    public SelenideElement mainPage = $(By.xpath("//*[@id='index']"));

    private EveningDressesPage eveningDressesPage = new EveningDressesPage();

    @Step("Open main page My Store")
    public MyStorePage open() {
        Selenide.open("http://automationpractice.com/index.php");
        mainPage.shouldHave(visible, Duration.ofSeconds(10));
        if (!Selenide.title().contains("My Store")) {
            throw new IllegalStateException("This is not My Store page");
        }
        return this;
    }

    @Step("Go to {categorie} categorie")
    public EveningDressesPage goToCategorie(String categorie) {
        dressesMenu.hover();
        contextdressmenu.findBy(text(categorie)).click();
        eveningDressesPage.mainPage.shouldHave(visible, Duration.ofSeconds(3));
        if (!Selenide.title().contains(categorie + " - My Store")) {
            throw new IllegalStateException("This is not " + categorie + " - My Store page");
        }
        return page(EveningDressesPage.class);
    }

    public SelenideElement topMenu = mainPage.find((byXpath(".//*[@id='block_top_menu']/ul")));

    public SelenideElement dressesMenu = topMenu.$x("li[2]");

    public ElementsCollection contextdressmenu = dressesMenu.$$x("ul/li");

}
