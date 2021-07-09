package asserts;

import io.qameta.allure.Step;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertsMethods {

    @Step("Assert actual text: '{actualText}' and expected text: '{expectedText}'")
    public static void assertText(String actualText, String expectedText) {
        assertEquals(expectedText, actualText, "Actual: '" + actualText + "' ,but should be: '" + expectedText + "'");
    }

    @Step("Assert actual text: '{actualMap}' and expected text: '{expectedMap}'")
    public static void assertMap(Map actualMap, Map expectedMap) {
        assertEquals(expectedMap, actualMap, "Actual: '" + actualMap.toString() + "' ,but should be: '" + expectedMap.toString() + "'");
    }
}
