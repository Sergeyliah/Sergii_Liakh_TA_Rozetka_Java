package tests;

import io.qameta.allure.Description;
import listener.ModifyListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utility.DataProviderClass;

import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

@Listeners(ModifyListener.class)
public class AddToCartTests extends SearchAndBuy{
    public AddToCartTests() throws IOException {
    }

    @Description("***This is description***")
    @Test(dataProvider = "Data", dataProviderClass = DataProviderClass.class)
    public void addToCart(String product, String brand) throws IOException, XPathExpressionException {
        searchAndBuy(product,brand);
        Verify.verify();
    }
}
