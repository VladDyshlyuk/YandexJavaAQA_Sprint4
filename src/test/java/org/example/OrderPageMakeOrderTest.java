package org.example;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pom.MainPage;
import pom.OrderPage;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderPageMakeOrderTest {
    private WebDriver driver;

    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phoneNumber;
    private final String date;
    private final String term;
    private final String color;
    private final String comment;
    private final String expectedOrderSuccessText = "Заказ оформлен";


    public OrderPageMakeOrderTest(String name, String surname, String address, String metro, String phoneNumber, String date, String term, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.term = term;
        this.color = color;
        this.comment = comment;
    }


    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {"Владислав", "Дышлюк", "г. Барнаул, ул. Ленина", "Сокольники", "+79999999999", "05.08.2025", "трое суток", "чёрный жемчуг","я комментатор!"},
                {"Иван", "Иванов", "За МКАДом", "Зорге", "+79808297890", "06.09.2026", "шестеро суток", "серая безысходность", "ааа!"},
        };
    }

    @Test
    public void makeOrderHeaderButtonTest(){
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        mainPage.open();
        mainPage.clickUpOrderButton();
        makeOrder(orderPage);
        MatcherAssert.assertThat(
                "Не удалось создать заказ",
                orderPage.getNewOrderMessage(),
                containsString(this.expectedOrderSuccessText)
        );

    }

    @Test
    public void makeOrderLowerButtonTest(){
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        mainPage.open();
        mainPage.clickCookieButton();
        mainPage.clickDownOrderButton();
        makeOrder(orderPage);
        MatcherAssert.assertThat(
                "Не удалось создать заказ",
                orderPage.getNewOrderMessage(),
                containsString(this.expectedOrderSuccessText)
        );

    }

    public void makeOrder(OrderPage orderPage){
        orderPage.waitForLoadForm();
        orderPage.setName(name);
        orderPage.setSurname(surname);
        orderPage.setAddress(address);
        orderPage.setMetro(metro);
        orderPage.setPhone(phoneNumber);
        orderPage.clickNextButton();
        orderPage.clickDateInput();
        orderPage.setDate(date);
        orderPage.clickTermDropDown(term);
        orderPage.setColor(color);
        orderPage.setComment(comment);
        orderPage.clickOrderButton();
        orderPage.clickAcceptOrderButton();
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
