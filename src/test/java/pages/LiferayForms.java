package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.TestBase;

import java.util.List;

public class LiferayForms extends TestBase {

    @FindBy(xpath =".//input[@class='ddm-field-text form-control']")
    private WebElement nameField;

    @FindBy(xpath =".//input[@class='form-control input-group-inset input-group-inset-after']")
    private WebElement birthField;

    @FindBy(xpath =".//textarea[@class='ddm-field-text form-control']")
    private WebElement reasonField;

    @FindBy(xpath =".//select[@name='year']")
    private WebElement dob_Year;

    @FindBy(xpath =".//select[@name='month']")
    private WebElement dob_Month;

    @FindBy(xpath ="(.//div[@class=' day'])[29]")
    private WebElement dob_Day;

    @FindBy(xpath =".//button[@type='submit']")
    private WebElement btnSubmit;

    @FindBy(xpath =".//h1[text()='Information sent']")
    private WebElement submitMessage;

    @FindBy(xpath =".//div[text()='This field is required.']")
    private WebElement errorMessage;

    @FindBy(xpath =".//span[@class='btn-section']")
    private WebElement btnLanguage;

    @FindBy(xpath =".//span[contains(text(),'Brasil')]")
    private WebElement brasilLanguage;

    @FindBy(xpath =".//h3[contains(text(),'primeira')]")
    private WebElement sentenceDisplayed;

    @FindBy(xpath =".//h4[@class='lfr-ddm-form-page-description']")
    private WebElement sentenceDisplayedPage;

    private WebDriver driver;
    public LiferayForms(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    public String insertMandatoryFormFields(String name, String reason) throws InterruptedException {
        driver.manage().window().maximize();
        birthField.click();
        Select year = new Select(dob_Year);
        Select month = new Select(dob_Month);
        year.selectByVisibleText("2015");
        year.selectByVisibleText("2010");
        year.selectByVisibleText("2005");
        year.selectByVisibleText("2000");
        /*List<WebElement> years =  year.getOptions();
        for(WebElement yearsInList:years){
            System.out.println(yearsInList.getText());
        }*/
        month.selectByVisibleText("June");
        dob_Day.click();  //selecting --> June 29th, 2000
        Thread.sleep(2000);
        reasonField.sendKeys(reason);
        Thread.sleep(2000);
        nameField.sendKeys(name);
        Thread.sleep(2000);
        sentenceDisplayedPage.click();
        btnSubmit.click();
        WebDriverWait wait = new WebDriverWait(driver,7);
        wait.until(ExpectedConditions.visibilityOf(submitMessage));
        String msg = submitMessage.getText();
        return msg;
    }

    public String verifySkippingDateOfBirthField(String name, String reason) throws InterruptedException {
        driver.manage().window().maximize();
        nameField.sendKeys(name);
        Thread.sleep(2000);
        reasonField.sendKeys(reason);
        Thread.sleep(2000);
        btnSubmit.click();
        if(errorMessage.isDisplayed()) {
            System.out.println("One mandatory field is missing! Check the form again please...");
        }
        String err = errorMessage.getText();
        Thread.sleep(2000);
        return err;
    }

    public String changeLanguage(){
            driver.manage().window().maximize();
            btnLanguage.click();
            brasilLanguage.click();
            try {
                Thread.sleep(4000);
                } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String braz = sentenceDisplayed.getText();
            System.out.println(" --> "+braz);
            String brazParts[] = braz.split(" ");
            String fourthWordinBraz = brazParts[3];
            return fourthWordinBraz;
    }


}
