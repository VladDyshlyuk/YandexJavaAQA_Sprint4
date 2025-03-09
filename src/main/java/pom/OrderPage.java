package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OrderPage {
    private final WebDriver driver;

    //Форма заказа
    private final By orderForm = By.xpath(".//div[starts-with(@class, 'Order_Form')]");
    //Поле ввода имени
    private final By nameInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Имя')]");
    //Поле ввода фамилии
    private final By surnameInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Фамилия')]");
    //Поле ввода адреса
    private final By addressInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Адрес')]");
    //Поле ввода метро
    private final By metroInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Станция метро')]");
    // Обёртка для списка доступных станций метро
    private final By metroList = By.className("select-search__select");
    // Список доступных для выбора станций метро
    private final By metroListItems = By.xpath(".//div[@class='select-search__select']//div[starts-with(@class,'Order_Text')]");

    //Поле ввода номера телефона
    private final By phoneInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Телефон')]");
    //Кнопка далее
    private final By nextButton = By.xpath(".//div[starts-with(@class, 'Order_NextButton')]/button");

    //Датапикер
    private final By dateSelected = By.className("react-datepicker__day--selected");
    private final By dateInput = By.xpath(".//div[starts-with(@class, 'react-datepicker__input-container')]//input");

    //Обертка селектор срока аренды
    private final By termDropdownRoot = By.className("Dropdown-root");
    //Варианты селектора срока аренды
    private final By termDropdownOption = By.className("Dropdown-option");
    //Список цветов
    private final By colorLabels = By.xpath(".//div[starts-with(@class, 'Order_Checkboxes')]//label");

    //Поле ввода комментария
    private final By commentInput = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Комментарий')]");
    //Кнопка Заказать
    private final By orderButton = By.xpath(".//button[text()='Заказать']");
    // Кнопка "Да" в окошке с подтверждением заказа
    private final By acceptOrderButton = By.xpath(".//div[starts-with(@class, 'Order_Buttons')]/button[not(contains(@class,'Button_Inverted'))]");
    //Текст об успешном оформлении заказа
    private final By newOrderSuccessMessage = By.xpath(".//div[starts-with(@class, 'Order_Modal')]//div[(starts-with(@class,'Order_ModalHeader'))]");
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadForm() {
        new WebDriverWait(this.driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(this.driver.findElement(this.orderForm)));
    }

    private void waitForElementLoad(By element) {
        new WebDriverWait(this.driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(this.driver.findElement(element)));

    }

    private void chooseElementFromDropdown(By dropdownElements, String elementToChoose) {
        List<WebElement> elementsFiltered = this.driver.findElements(dropdownElements);
        for (WebElement element : elementsFiltered) {
            if (element.getText().equals(elementToChoose)) {
                element.click();
                break;
            }
        }
    }

    public void setName(String name){
        driver.findElement(nameInput).sendKeys(name);
    }

    public void setSurname(String surname){
        driver.findElement(surnameInput).sendKeys(surname);
    }

    public void setAddress(String address){
        driver.findElement(addressInput).sendKeys(address);
    }

    public void setPhone(String phone){
        driver.findElement(phoneInput).sendKeys(phone);
    }


    public void clickNextButton(){
        driver.findElement(nextButton).click();
    }

    public void clickOrderButton(){
        driver.findElement(orderButton).click();
    }


    public void clickSelectedDate(){
        driver.findElement(dateSelected).click();
    }

    public void clickTermDropDown(String choose){
        driver.findElement(termDropdownRoot).click();
        chooseElementFromDropdown(termDropdownOption, choose);
    }

    public void setMetro(String metro) {
        driver.findElement(metroInput).sendKeys(metro);
        waitForElementLoad(metroList);
        chooseElementFromDropdown(metroListItems, metro);
    }

    public void setDate(String date){
        driver.findElement(dateInput).sendKeys(date);
        clickSelectedDate();
    }

    public void setComment(String comment){
        driver.findElement(commentInput).sendKeys(comment);
    }

    public void setColor(String color) {
        chooseElementFromDropdown(colorLabels, color);
    }

    public void clickDateInput(){
        driver.findElement(dateInput).click();
    }

    public void clickAcceptOrderButton(){
        driver.findElement(acceptOrderButton).click();
    }

    public String getNewOrderMessage() {
        return driver.findElement(newOrderSuccessMessage).getText();
    }
}
