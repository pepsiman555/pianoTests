package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class OrderPage extends GlobalElementsPage {

    public SelenideElement mainPage = $(byXpath("//*[@id='order']"));

    public SelenideElement trashButton = mainPage.find(byXpath(".//*[@class = 'icon-trash']"));

    public SelenideElement warning = mainPage.find(byXpath(".//*[@class = 'alert alert-warning']"));

    public ElementsCollection cartSummary = mainPage.find(byXpath(".//*[@id = 'cart_summary']")).findAll(byXpath(".//tr"));

    @Step("Delete order {productName} in my cart")
    public void clickToDeleteOrderButton(String productName) {
        cartSummary.findBy(Condition.text(productName)).find(byXpath(".//*[@class = 'icon-trash']")).click();
    }

    @Step("Get warning message")
    public String getWarningMessage() {
        warning.shouldHave(Condition.visible, Duration.ofSeconds(5));
        return warning.text();
    }

    @Step("Get product count in cart")
    public String getProductCountInCart() {
        String productCount = shoppingCart(mainPage).text();
        if (!productCount.equals("Cart (empty)")) {
            productCount = productCount.replace("Cart", "").trim();
        }
        return productCount;
    }
}
