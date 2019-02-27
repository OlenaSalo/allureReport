package com.epam.TA.Test;
import com.epam.TA.DriverManager;
import com.epam.TA.businessObj.GmailDeleteCanceled;
import com.epam.TA.businessObj.GmailLoginPageBo;
import com.epam.TA.businessObj.GmailTrash;
import com.epam.TA.reporter.TestListener;
import com.epam.TA.unit.JsonObject;
import com.epam.TA.unit.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;

@Listeners(TestListener.class)
public class GmailCanceledDeleteTest {
    public static final Logger LOG = LogManager.getLogger(GmailCanceledDeleteTest.class);

    JsonObject jsonObject = new JsonObject();
    public String countExpected;
    public String countActual;

    @BeforeSuite(alwaysRun = true)
    public void configSuite() {

        LOG.info("===================================");
        LOG.info("   TESTS  VALID - STARTED");
        LOG.info("===================================");
    }

    @Test(dataProvider = "getUser")
    public void verifyDeleteCanceledMsgTest(User user) {
        GmailLoginPageBo gmailPageObject = new GmailLoginPageBo();
        GmailDeleteCanceled gmailDeleteCanceled = new GmailDeleteCanceled();
        gmailPageObject.logIn(user.getUserName(),user.getPsw() );
        gmailDeleteCanceled.selectMsg();
        gmailDeleteCanceled.canceledDeleteMsg();
            Assert.assertTrue(gmailDeleteCanceled.varifyCanceledDelete());

    }

    @Test(dataProvider = "getUser")
    public void verifyDeleteTrashMsgTest(User user) {
        GmailLoginPageBo gmailPageObject = new GmailLoginPageBo();
        GmailTrash gmailTrash = new GmailTrash();
        gmailPageObject.logIn(user.getUserName(),user.getPsw() );
        gmailTrash.selectTrash();
        countExpected = gmailTrash.countMessage();
        gmailTrash.deleteTrash();
        countActual = gmailTrash.countMessageAfter();
        Assert.assertEquals(countExpected, countActual);

    }

    @AfterMethod
            public void closeAll() {
        DriverManager.getInstance().getDriver().quit();
    }


    @DataProvider(parallel = true)
    public Object[][] getUser() throws FileNotFoundException {

       return jsonObject.getDataForUser();
    }
}





