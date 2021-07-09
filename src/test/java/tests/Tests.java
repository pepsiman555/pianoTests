package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import pages.EveningDressesPage;
import pages.MyStorePage;
import pages.OrderPage;
import pages.ProductPage;

import java.util.Map;

import static asserts.AssertsMethods.assertMap;
import static asserts.AssertsMethods.assertText;

public class Tests extends TestBase {

    @Test
    @Description("Scenario 1")
    public void scenario1() {

        MyStorePage myStorePage = new MyStorePage().open();

        EveningDressesPage eveningDressesPage = myStorePage.goToCategorie("Evening Dresses");

        ProductPage productPage = eveningDressesPage.findProduct("Printed Dress");

        Map<String, String> expected = Map.of(
                "Compositions", "Viscose",
                "Styles", "Dressy",
                "Properties", "Short Dress"
        );

        assertMap(productPage.getDataSheetInformation(), expected);
    }

    @Test
    @Description("Scenario 2")
    public void scenario2() {

        MyStorePage myStorePage = new MyStorePage().open();

        EveningDressesPage eveningDressesPage = myStorePage.goToCategorie("Evening Dresses");

        ProductPage productPage = eveningDressesPage.findProduct("Printed Dress");
        productPage.selectProductParameters("M", "Pink");
        productPage.clickToAddToCartButton();
        productPage.clickToContinueShoppingButton();

        assertText(productPage.getProductCountInCart(), "1 Product");
    }

    @Test
    @Description("Scenario 3")
    public void scenario3() {

        MyStorePage myStorePage = new MyStorePage().open();

        EveningDressesPage eveningDressesPage = myStorePage.goToCategorie("Evening Dresses");

        ProductPage productPage = eveningDressesPage.findProduct("Printed Dress");
        productPage.selectProductParameters("M", "Pink");
        productPage.clickToAddToCartButton();
        productPage.clickToContinueShoppingButton();

        OrderPage orderPage = productPage.goToCart();
        orderPage.clickToDeleteOrderButton("Printed Dress");

        assertText(orderPage.getWarningMessage(), "Your shopping cart is empty.");
        assertText(orderPage.getProductCountInCart(), "Cart (empty)");
    }
}
