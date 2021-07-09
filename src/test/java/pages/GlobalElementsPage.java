package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;

public class GlobalElementsPage {

    SelenideElement shoppingCart(SelenideElement page) {
        return page.find(byXpath(".//*[@title = 'View my shopping cart']"));
    }
}
