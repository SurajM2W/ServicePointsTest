package com.servicepoints.testCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

import junit.framework.Assert;

public class TC16_VerifyUpdateQuotation extends BaseClass {
	ReadConfig rc = new ReadConfig();
	public String c1price = rc.setChangePrice1Pcs();
	public String c2price = rc.setChangePrice2Pcs();
	public String c3price = rc.setChangePrice3Pcs();
	public String c4price = rc.setChangePrice4Pcs();

	
	@Test
	public void verifyUpdateQuotation() throws InterruptedException, IOException {
		logger.info("Application Opened.");
		LoginPage lp = new LoginPage(driver);
		Thread.sleep(1000);

		lp.setAdminMailId(agentsupmail);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);

		lp.setAdminPassword(agentsuppass);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(3000);

		AgentSupProductsPage aspp = new AgentSupProductsPage(driver);
		aspp.getProductsPage();
		Thread.sleep(4000);
		aspp.clickQuotationsClientsTab();
		Thread.sleep(2000);

		aspp.searchProductName(product);
		Thread.sleep(2000);
		logger.info("Product name entered.");
		aspp.clickOnfdiv();

		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		String parent = it.next();
		String child = it.next();
		driver.switchTo().window(child);
		Thread.sleep(4000);

		aspp.firstPcsPrice(FirstPcsPrice);
		aspp.secPcsPrice(SecPcsPrice);
		aspp.thirdPcsPrice(ThirdPcsPrice);
		aspp.forthPcsPrice(ForthPcsprice);
		logger.info("Price entered");
		Thread.sleep(4000);

		aspp.clickOnSubmitQuote();
		Thread.sleep(6000);

		if (aspp.getStatus().equals("Quotation done")) {
			Thread.sleep(2000);
			Assert.assertTrue(true);
			logger.info("Verification of Submit quotation Successed..");
		} else {
			captureScreen(driver, "Submit Quote Test");
			logger.info("Verification of Submit quotation failed..");
			Assert.assertTrue(false);
			Thread.sleep(4000);
		}
		
		aspp.firstPcsPrice(c1price);
		aspp.secPcsPrice(c2price);
		aspp.thirdPcsPrice(c3price);
		aspp.forthPcsPrice(c4price);
		logger.info("Price entered");
		Thread.sleep(4000);
		
		aspp.updateQuotation();
		Thread.sleep(4000);
		
		if(driver.getPageSource().contains("Quotation updated successfully.")) {
			Thread.sleep(2000);
			Assert.assertTrue(true);
			logger.info("Verification of Quotation updation Successed..");
		}else {
			captureScreen(driver, "Submit Updated Quote Test");
			logger.info("Verification of Quotation updation failed..");
			Assert.assertTrue(false);
			Thread.sleep(4000);
		}		
	}
}
