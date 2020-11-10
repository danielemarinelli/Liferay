package tests;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LiferayForms;

import java.util.HashMap;

public class LiferayTest extends TestBase{

    LiferayForms formPage;

    @Test(priority=1)
    public void Verify_Form_Fields() throws InterruptedException {
        formPage = new LiferayForms(driver());
        String msgAfterSubmit = formPage.insertMandatoryFormFields("Leonard","I would like new opportunities in a worldwide company");
        System.out.println(msgAfterSubmit);
        if(msgAfterSubmit.equals("Information sent")){
            reporter().report(LogStatus.PASS,
                    "Checking submit form process",
                    "Submit test is Successful");
        }else{
            reporter().report(LogStatus.FAIL,
                    "Checking submit form process",
                    "Submit test failed");
        }
    }

    @Test(priority=2)
    public void Verify_Form_Fields_Skipping_DOB() throws InterruptedException {
        formPage = new LiferayForms(driver());
        String pageErrorDisplayed = formPage.verifySkippingDateOfBirthField("Kate","I would like to test the mandatory DOB field");

        if(pageErrorDisplayed.equals("This field is required.")) {
            reporter().report(LogStatus.PASS,
                    "Didn't insert all mandatory fields",
                    "Didn't insert DOB field test is Successful");
        }else{
                reporter().report(LogStatus.FAIL,
                        "Checking not inserted one mandatory field",
                        "Skipping one mandatory field test failed");
            }
    }

    @Test(priority=3)
    public void Verify_Selected_Language(){
        formPage = new LiferayForms(driver());
        String partOfBrasilianMessage = formPage.changeLanguage();
        //Assert.assertEquals(partOfBrasilianMessage,"primeira","The selected language isn't correct");
        if(partOfBrasilianMessage.equals("primeira")) {
        reporter().report(LogStatus.PASS,
                "Change language in web site",
                "The change language form test is Successful");
        }else{
            reporter().report(LogStatus.FAIL,
                    "Checking change language on form",
                    "Changing language test failed");
        }
    }

}
