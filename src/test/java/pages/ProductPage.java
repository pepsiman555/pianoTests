package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ProductPage extends GlobalElementsPage {

    private OrderPage orderPage = new OrderPage();

    @Step("Go to cart")
    public OrderPage goToCart() {
        shoppingCart(mainPage).hover();
        checkOutButton.click();
        orderPage.mainPage.shouldHave(visible, Duration.ofSeconds(5));
        if (!Selenide.title().contains("Order - My Store")) {
            throw new IllegalStateException("This is not Order - My Store page");
        }
        return page(OrderPage.class);
    }

    public SelenideElement mainPage = $(byXpath("//*[@id='product']"));

    public SelenideElement dataSheet = mainPage.find(byXpath(".//table[@class = 'table-data-sheet']"));

    public SelenideElement boxInfoProduct = mainPage.find(byXpath(".//div[@class = 'box-info-product']"));

    public SelenideElement addToCartButton = mainPage.find(byXpath(".//*[@id = 'add_to_cart']"));

    public SelenideElement layerCart = mainPage.find(byXpath(".//*[@id = 'layer_cart']"));

    public SelenideElement continueShoppingButton = layerCart.find(byXpath(".//*[@title = 'Continue shopping']"));

    public SelenideElement checkOutButton = mainPage.find(byXpath(".//*[@id = 'button_order_cart']"));

    @Step("Get information about product")
    public Map<String, String> getDataSheetInformation() {
        Map<String, String> actualInformation = new HashMap<String, String>();
        dataSheet.shouldHave(visible);
        ElementsCollection dataSheetMap = dataSheet.findAll(byXpath(".//tr"));
        dataSheetMap.forEach(x -> {
            actualInformation.put(x.findAll(byXpath(".//td")).get(0).text(), x.findAll(byXpath(".//td")).get(1).text());
        });

        return actualInformation;
    }

    @Step("Select product paramters size = {size} and color = {color}")
    public void selectProductParameters(String size, String color) {
        boxInfoProduct.find(byXpath(".//*[@id='group_1']")).selectOption(size);
        boxInfoProduct.find(byXpath(".//*[@id='color_to_pick_list']")).find(byXpath(".//a[@name = '" + color + "']")).click();
    }

    @Step("Continue shopping")
    public void clickToContinueShoppingButton() {
        layerCart.shouldHave(visible, Duration.ofSeconds(10));
        continueShoppingButton.shouldHave(Condition.visible);
        continueShoppingButton.click();
    }

    @Step("Add product to cart")
    public void clickToAddToCartButton() {
        addToCartButton.click();
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
