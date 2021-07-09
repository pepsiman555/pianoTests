package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class EveningDressesPage {

    private ProductPage productPage = new ProductPage();

    public SelenideElement mainPage = $(By.xpath("//*[@id='category']"));

    @Step("Find product {productName}")
    public ProductPage findProduct(String productName) {
        SelenideElement product = productListGrid.$$x("li").findBy(Condition.text(productName));
        product.scrollTo();
        product.hover().find(byXpath(".//*[@class = 'button lnk_view btn btn-default']")).click();
        productPage.mainPage.shouldHave(Condition.visible, Duration.ofSeconds(5));
        if (!Selenide.title().contains(productName + " - My Store")) {
            throw new IllegalStateException("This is not " + productName + " - My Store page");
        }
        return page(ProductPage.class);
    }

    public SelenideElement productListGrid = mainPage.find((byXpath(".//ul[@class='product_list grid row']")));

}
