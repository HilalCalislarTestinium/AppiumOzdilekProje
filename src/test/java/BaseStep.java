import com.thoughtworks.gauge.Step;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.logging.Logger;

public class BaseStep extends BaseTest{
    final static Logger logger = Logger.getLogger(BaseStep.class.getName());

    @Step("<second> kadar bekle")
    public void waitForsecond(int second) throws InterruptedException {
        Thread.sleep(1000*second);

    }
    @Step("<Key> İd'li elemente tıkla")
    public void clickElementByid(String Key){
        appiumDriver.findElement(By.id(Key)).click();
        logger.info(Key+" Elementine tiklendi");


    }

    @Step("<Key> xpath'li elemente tıkla")
    public void clickElementByxpath(String Key){
        appiumDriver.findElement(By.xpath(Key)).click();
        logger.info(Key+" Elementine tiklendi");
    }
    @Step("<Key> xpath'li elemente <keyword> değerini yaz")
    public void SendkeyElementByxpath(String Key,String keywordc){
        appiumDriver.findElement(By.xpath(Key)).sendKeys(keywordc);
        logger.info(Key+" Elementine tiklendi");
        Assert.assertTrue("Bekleme yapildi ",appiumDriver.findElement(By.xpath(Key)).getText().equals(keywordc));


    }

    @Step("Sayfayı yukarı kaydır")
    public void swipeUpI(){
        Dimension dims = appiumDriver.manage().window().getSize();
        System.out.println("Telefon Boyutu "+dims);
        PointOption pointOptionStart, pointOptionEnd;
        int edgeBorder = 10;
        final int PRESS_TIME = 200; // ms
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        System.out.println("pointOptionStart " + pointOptionStart);
        pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
        System.out.println("pointOptionEnd " + pointOptionEnd);
        new TouchAction(appiumDriver)
                .press(pointOptionStart)
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                .moveTo(pointOptionEnd)
                .release().perform();
    }
    @Step("Elementi <xpath> bul ve <keyword> değerini kontrol et")
    public void textControl(String xpath,String keyword){
        logger.info("Text degeri: " + appiumDriver.findElement(By.xpath(xpath)).getText());
        Assert.assertTrue("Text yoktur. " ,appiumDriver.findElement(By.xpath(xpath)).getText().equals(keyword));
    }


}
