package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RentInformationPage {
    private WebDriver driver;

    // Поле для ввода даты доставки самоката
    private By inputDate= By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Выпадающий список срока аренды
    private By divRentList = By.xpath(".//div[@class='Dropdown-root']");
    // Элемент выпадающего списка срока аренды
    private String divElement = ".//div[text()='period']";
    // Чекбокс цвета
    private String colourCheckbox = ".//input[@id='colour']";
    // Поле для ввода комментария
    private By inputComment = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN']");
    // Кнопка "Заказать"
    private By purchaseButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    // Кнопка "Да" окна "Хотите оформить заказ?"
    private By yesOrderButton = By.xpath(".//button[(text()='Да')]");
    // Текст в блоке div "Заказ оформлен" при успешном оформлении заказа
    private By divOrderIsSuccess = By.xpath(".//div[text()='Заказ оформлен']");

    public RentInformationPage(WebDriver driver){
        this.driver = driver;
    }

    // Заполнение полей информации о клиенте
    public void inputRentFields(String date, String listElement, String colour, String comment) {
        driver.findElement(inputDate).sendKeys(date);
        driver.findElement(inputDate).sendKeys(Keys.ENTER);

        driver.findElement(divRentList).click();
        driver.findElement(By.xpath(divElement.replace("period",listElement))).click();

        driver.findElement(By.xpath(colourCheckbox.replace("colour",colour))).click();

        driver.findElement(inputComment).sendKeys(comment);
    }

    // Нажать кнопку "Заказать"
    public void purchaseButtonClick() {
        driver.findElement(purchaseButton).click();
    }

    // Нажать кнопку "Да" в окне "Хотите оформить заказ?"
    public void yesOrderButtonClick() {
        driver.findElement(yesOrderButton).click();
    }

    // Объединение заполненния полей и нажатия кнопки "Заказать" в один шаг
    public void inputFieldsAndClickPurchaseButton(String date, String listElement, String colour, String comment) {
        inputRentFields(date, listElement, colour, comment);
        purchaseButtonClick();
        yesOrderButtonClick();
    }

    public boolean isOrderStatusTextVisible() {
        return driver.findElement(divOrderIsSuccess).isDisplayed();
    }

    // Ожидание загрузки страницы
    public void waitForLoadingPage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(purchaseButton));
    }
}
