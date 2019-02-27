package com.epam.TA.businessObj;

import com.epam.TA.pages.GmailHomePage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GmailTrash {

    public static final Logger LOG = LogManager.getLogger(GmailTrash.class);


    private  GmailHomePage gmailHomePage = new GmailHomePage();

    @Step("Selected and delete messages step...")
    public void deleteTrash()
    {
        if(gmailHomePage.getTrashMsg().isChecked()) {
            LOG.info("Messages are selected and deleted");
            gmailHomePage.getTrashMsg().setChecked();
            gmailHomePage.getDeleteTrash().click();
        }else
        LOG.error("Messages didn't find");
    }

    public  void selectTrash(){
        LOG.info("Search trash");
        gmailHomePage.getSearch().sendMsgToField("in:trash");
        LOG.info("Trash didn't achievable");
        gmailHomePage.getSearchButton().click();
    }


    @Step("Quantity of message before delete step...")
    public String countMessage()
    {
        LOG.info("Count of message in trash before delete - " + gmailHomePage.getMailInTrash().getText());
        return gmailHomePage.getMailInTrash().getText();
    }

    @Step("Quantity of message after delete step...")
    public String countMessageAfter()
    {
        LOG.info("Count of message in trash after delete - " + gmailHomePage.getMailInTrash().getText());
        return gmailHomePage.getMailInTrash().getText();
    }
}
