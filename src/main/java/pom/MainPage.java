package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    //Заголовок раскрывающегося блока
    private final By accordionHeading = By.className("accordion__heading");
    //Абзац раскрывающегося блока
    private final By accordionPanel = By.xpath(".//div[@class='accordion__panel']/p");
    //Верхняя кнопка "Заказать"
    private final By upOrderButton = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[@class='Button_Button__ra12g']" );
    //Нижняя кнопка "Заказать"
    private final By downOrderButton = By.className("Button_Middle__1CSJM" );
    //Кнопка "Принять куки"
    private final By cookieAcceptButton = By.id("rcc-confirm-button");

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void open(){
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void clickHeading(int index){
        driver.findElements(accordionHeading).get(index).click();
    }

    public String getPanelText(int index){
        return driver.findElements(accordionPanel).get(index).getText();
    }

    public void clickUpOrderButton(){
        driver.findElement(upOrderButton).click();
    }

    public void clickDownOrderButton(){
        driver.findElement(downOrderButton).click();
    }

    public void clickCookieButton(){
        driver.findElement(cookieAcceptButton).click();
    }

    public void waitForLoadItem(int index) {
        new WebDriverWait(this.driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(this.driver.findElements(this.accordionHeading).get(index)));
    }
}
