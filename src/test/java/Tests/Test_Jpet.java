package Tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BASECLASS.Screenshot;
import BASECLASS.Utilities;
import Excel.Read_excel;
import Pages.Add_to_cart;
import Pages.Login_page;
import Pages.Register_page;
import Pages.Search_page;
import Pages.product_in_cart;



public class Test_Jpet extends Read_excel {
	WebDriver driver;
	Utilities ult;

	
	Register_page Register;
	Login_page Login;
	Search_page search;
	Add_to_cart addcart;
	product_in_cart productincart;
	Screenshot screenshot;
	
	
	
	@BeforeClass
	public void beforeclass() 
	{
		ult=new Utilities(driver);
		get_data();
		
		
	}
	@BeforeMethod
	public void beforemethod() 
	{
		//passing url and browser to base class function
		driver=ult.Launch_browser("CHROME", "https://jpetstore.cfapps.io/catalog");
		//creating a object to the register function
		Register=new Register_page(driver);
		
		
		//creating a object to the login function
			Login=new Login_page(driver);
			//creating a object to the search function
		search=new Search_page(driver);
		//ceating a object to the addcart function
		addcart=new Add_to_cart(driver);
		//creating a  object to products in cart function
		productincart=new product_in_cart();
		//creating a object to the screenshot function
		screenshot=new Screenshot(driver);
	}
  
@Test(dataProvider="loginpage")
  public void Register_login(String un,String pd,String cpd,String nm,String ln,String eml,String ph,String a1,String a2,String ct,String st,String zp,String ctr,String uid,String pwd) throws IOException 
  {
	  
	  String phn=ph.substring(1, 10);
	  String zpc=zp.substring(1, 6);
	  //calling register function
	  Register.do_reg(un,pd,cpd,nm,ln,eml,phn,a1,a2,ct,st,zpc,ctr);
  
	 
	  
	  
	 //calling login function 
	 String s= Login.Login(uid,pwd);
	 Assert.assertTrue(s.contains("Demo"));
	 driver.quit();
	 
	  
  }
	  @Test(priority=2)
	  public void search_secenario() throws IOException {
	  //calling searching function
		  Login.Login("giri","babuyadav");
		  //calling searching function
		  search.search();
		  //calling adda_product function
		  addcart.add_product(); 
		  //calling screenshot function
		  screenshot.Take_screenshot();
		  driver.quit();
		  
	  }
		  //calling add cart function
	
	  @Test(priority=3) public void adding_product() throws IOException { 
		  String expected="Thank you, your order has been submitted.";
	  Login.Login("giri","babuyadav"); 
	  search.search(); 
	  //calling add_product function
	  addcart.add_product(); 
	  //calling placing_order class
	   String Actuals= productincart.placing_order();
	  Assert.assertEquals(Actuals, expected);
	  
	 
	  
  }
  @DataProvider(name="loginpage")
  public String[][] Provide_data()
  {
	  //reading a data from excel
	  return testdata;
  }
  
  
}
