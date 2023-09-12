package pageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;

    // Элемент выпадающего списка вопросов
    private String listElement = ".//div[@id='accordion__heading-№']";
//    private By listElement = By.xpath(".//div[@role='button']");
    // Ответ из выпадающего списка
    private String elementText = ".//div[@aria-labelledby='accordion__heading-№']/p";
    // Заголовок выпадающего списка вопросов
    private By questionsHeader = By.xpath(".//div[text()='Вопросы о важном']");
    // Кнопка покупки сверху страницы
    private By purchaseButtonTop = By.xpath(".//button[@class='Button_Button__ra12g']");
    // Кнопка покупки снизу страницы
    private By purchaseButtonBottom = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");
    // Кнопка соглашения с использованием куки
    private By cookieAcceptButton = By.xpath(".//button[@class='App_CookieButton__3cvqF']");

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    // Нажатие кнопки соглашения с использованием куки
    public void cookieAcceptButtonСlick() {
        driver.findElement(cookieAcceptButton).click();
    }

    // Открытие элемента выпадающего списка
    public void clickListElement(int elementNumber) {
        driver.findElement(By.xpath(listElement.replace("№",Integer.toString(elementNumber)))).click();
    }

    public String getAnswerText(int elementNumber) {
       return driver.findElement(By.xpath(elementText.replace("№",Integer.toString(elementNumber)))).getText();
    }

    // Скролл к выпадающему списку
    public void scrollToQuestions() {
        WebElement element =  driver.findElement(questionsHeader);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    // Нажать кнопку "Заказать" сверху страницы
    public void purchaseButtonTopClick() {
        driver.findElement(purchaseButtonTop).click();
    }

    // Нажать кнопку "Заказать" снизу страницы
    public void purchaseButtonBottomClick() {
        driver.findElement(purchaseButtonBottom).click();
    }

    // Скролл к кнопке "Заказать" снизу страницы
    public void scrollToPurchaseButtonBottom() {
        WebElement element =  driver.findElement(questionsHeader);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}