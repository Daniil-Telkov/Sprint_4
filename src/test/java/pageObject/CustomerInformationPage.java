package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerInformationPage {
    private WebDriver driver;

    // Поле для ввода имени
    private By inputName = By.xpath(".//input[@placeholder='* Имя']");
    // Поле для ввода фамилии
    private By inputSurname = By.xpath(".//input[@placeholder='* Фамилия']");
    // Поле для ввода Адреса
    private By inputAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // Выпадающий список станций метро
    private By inputMetro = By.xpath(".//input[@placeholder='* Станция метро']");
    // Кнопка станции метро
    private String buttonMetro = ".//div[@class='select-search__select']//button[div[text()='metro_name']]";
    // Поле для ввода телефона
    private By inputPhone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка "Далее"
    private By nextButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public CustomerInformationPage(WebDriver driver){
        this.driver = driver;
    }

    // Заполнение полей информации о клиенте
    public void inputCustomerFields(String name, String surname, String address, String metro, String phone) {
        driver.findElement(inputName).sendKeys(name);
        driver.findElement(inputSurname).sendKeys(surname);
        driver.findElement(inputAddress).sendKeys(address);

        driver.findElement(inputMetro).click();
        driver.findElement(By.xpath(buttonMetro.replace("metro_name",metro))).click();

        driver.findElement(inputPhone).sendKeys(phone);
    }

    // Нажать кнопку "Далее"
    public void nextButtonClick() {
        driver.findElement(nextButton).click();
    }

    // Объединение заполненния полей и нажатия кнопки "Далее" в один шаг
    public void inputFieldsAndClickNextButton(String name, String surname, String address, String metro, String phone) {
        inputCustomerFields(name, surname, address, metro, phone);
        nextButtonClick();
    }

    // Ожидание загрузки страницы
    public void waitForLoadingPage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(nextButton));
    }
}
