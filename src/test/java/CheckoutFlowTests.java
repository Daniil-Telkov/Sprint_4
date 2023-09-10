import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pageObject.CustomerInformationPage;
import pageObject.MainPage;
import pageObject.RentInformationPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CheckoutFlowTests {
    private WebDriver driver;

    String name;
    String surname;
    String address;
    String metro;
    String phone;
    String date;
    String rentPeriod;
    String colour;
    String comment;
    boolean orderSuccess;

    public CheckoutFlowTests(String name, String surname, String address, String metro, String phone, String date,
                             String rentPeriod, String colour, String comment,boolean orderSuccess) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.rentPeriod = rentPeriod;
        this.colour = colour;
        this.comment = comment;
        this.orderSuccess = orderSuccess;
    }

    @Parameterized.Parameters
    public static Object[][] getCustomerData() {
        return new Object[][] {
                {"Имя","Фамилиев", "Бульвар 4", "Лубянка", "79077777777", "10.09.2023", "двое суток", "black", "my comment", true}
                ,{"Тест","Тестфамилия", "Адрес 1", "Тропарёво", "79999999999", "24.09.2022", "сутки", "grey", "", true}
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.firefoxdriver().setup();
//        WebDriverManager.chromedriver().clearDriverCache().setup();
    }

    @Test
    public void PositiveCustomerScenarioPurchaseButtonTop() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
//        driver = new ChromeDriver(options);

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new FirefoxDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage objMainPage = new MainPage(driver);
        objMainPage.cookieAcceptButtonСlick();
        objMainPage.purchaseButtonTopClick();

        CustomerInformationPage objCustomerInformationPage = new CustomerInformationPage(driver);
        objCustomerInformationPage.waitForLoadingPage();
        objCustomerInformationPage.inputFieldsAndClickNextButton(name,surname, address, metro, phone);

        RentInformationPage objRentInformationPage = new RentInformationPage(driver);
        objRentInformationPage.waitForLoadingPage();
        objRentInformationPage.inputFieldsAndClickPurchaseButton(date, rentPeriod, colour, comment);

        assertEquals(orderSuccess, objRentInformationPage.isOrderStatusTextVisible());
    }

    @Test
    public void PositiveCustomerScenarioPurchaseButtonBottom() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
//        driver = new ChromeDriver(options);

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new FirefoxDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage objMainPage = new MainPage(driver);
        objMainPage.scrollToPurchaseButtonBottom();
        objMainPage.cookieAcceptButtonСlick();
        objMainPage.purchaseButtonBottomClick();

        CustomerInformationPage objCustomerInformationPage = new CustomerInformationPage(driver);
        objCustomerInformationPage.waitForLoadingPage();
        objCustomerInformationPage.inputFieldsAndClickNextButton(name,surname, address, metro, phone);

        RentInformationPage objRentInformationPage = new RentInformationPage(driver);
        objRentInformationPage.waitForLoadingPage();
        objRentInformationPage.inputFieldsAndClickPurchaseButton(date, rentPeriod, colour, comment);

        assertEquals(orderSuccess, objRentInformationPage.isOrderStatusTextVisible());
    }

    @After
    public void teardown() {
        driver.quit(); // Закрыть браузер
    }
}
