package com.epam.TA.businessObj;
import com.epam.TA.DriverManager;
import com.epam.TA.pages.GmailHomePage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class GmailDeleteCanceled {
    public static final Logger LOG = LogManager.getLogger(GmailDeleteCanceled.class);

    int NUMBER_OF_MESSAGES = 3;
    private GmailHomePage gmailHomePage = new GmailHomePage();

    @Step("Selected three messages step...")
    public void selectMsg() {

        if (gmailHomePage.getSelectMsg().size() < NUMBER_OF_MESSAGES) {
            LOG.info("Msg less then 3");
        } else {
            gmailHomePage
                    .getSelectMsg()
                    .stream()
                    .limit(NUMBER_OF_MESSAGES)
                    .forEach(checkBox -> checkBox.setChecked());

        }
    }

    @Step("Delete selected messages and canceled them deleting step...")
    public void canceledDeleteMsg(){
        LOG.info("Delete and canceled delete message");
        gmailHomePage.getDeleteButton().click();
        gmailHomePage.getCanceledButton().click();
    }


    @Step("Verify that messages don't delete Step...")
    public boolean varifyCanceledDelete()
    {
        if(gmailHomePage.getCanceledAlert().isCheck())
            LOG.info("Test end successful!!");
        else
            LOG.error("Test failed");
        return gmailHomePage.getCanceledAlert().isCheck();

    }


}
