package GenericCode;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {

	public static void main(String[] args) throws InterruptedException {
		
		// Selenium Code..

		System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver.exe");
		
		WebDriver driver=new ChromeDriver();
		
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
//		Thread.sleep(2000);
		
		driver.manage().window().maximize();
		
		//IMPLICIT WAIT..
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//Using Arrays to add multiple number products in the list..
		
		String [] items = {"Cucumber","Brocolli","Beetroot","Carrot"};
		
		
		//OUTSIDE METHOD addedItems is accessed inside the item..
		//Call the method inside this method..
		addedItems(driver,items);
		
		//If we want access one method in another we have to create a object based on the class that is created now..
		Waits w = new Waits();
		w.addedItems(driver, items); //Now the item is called here..
		
		
		//After Finishing add to cart click on the AddtoCart Button to chk whether the items are added..
		
		WebElement clk1 =	driver.findElement(By.xpath("//img[@src='./images/bag.png']"));
		clk1.click();
		
		//Proceed to chkout the items...
		WebElement proceed = driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']"));
		proceed.click();
		
		//Apply Promo Code..
		driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");
		WebElement App = driver.findElement(By.xpath("//button[@class='promoBtn']"));
		App.click();
		
		//EXPLICIT WAIT...
		//Promocode applied Get that text...
		WebDriverWait w1 = new WebDriverWait(driver,5); //driver,seconds=5 is given there..
		w1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo")));
		System.out.println(driver.findElement(By.cssSelector("span.promoInfo")).getText());
		
		
		//Placing order..
		WebElement order = driver.findElement(By.xpath("//button[text()='Place Order']"));
		order.click();
		
		//Dropdown Button..
		
		//Now Clicking on Dropdown Button..
		WebElement Dropdown = driver.findElement(By.xpath("//select[@style='width: 200px;']"));
		Dropdown.click();
		
		//Using Select For dropdown box..
		Select sel = new Select(Dropdown);
		sel.selectByValue("India");
		Thread.sleep(1000);
		
		//Tick the chk box
		WebElement Chkbox = driver.findElement(By.cssSelector("input.chkAgree"));
		Chkbox.click();
		Thread.sleep(1000);
		//To chk hw many chkboxes are placed..
		System.out.print(driver.findElement(By.cssSelector("input[type='checkbox']")).getSize());
	
		//Finishing with ending..
		WebElement Finish = driver.findElement(By.xpath("//button[text()='Proceed']"));
		Finish.click();
		Thread.sleep(1000);
		
		//Close All Tabs..
		driver.quit();
		
		
	}
	
	//Static is becoz we are calling the method based on the object..
	public static void addedItems(WebDriver driver,String[] items)
	{
		int j=0;
		
		//Getting the css path of cucumber and other prods commonly..
		List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));
		
		System.out.println("size of the products :" + products.size());
		
		for(int i=0;i<products.size();i++) 
		{
				//getting index and text of cucumber product..
				String[] str1 = products.get(i).getText().split("-"); //split method will generally split using "-" symbol..
				
				//Remove the whites spaces Eg: Brocoli ...
				String Whitespcesrmvd = str1[0].trim(); //trim() will remove the white spaces in the products.. Eg: Brocoli ..
				
				System.out.println(str1);
				
				//Convert Array into ArrayList to check the products is present or not...
				List arrlst = Arrays.asList(items);
//				arrlst.add("Beetroot");
				System.out.println(arrlst);
				//Chk whether the products in the array matches the arraylist or not..
				if(arrlst.contains(Whitespcesrmvd))
				{
					j++;
					driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click(); //get() will get the index...
					
//					break; //if we use array list then we cant able to use break;
					
					// This j condition is declared to remove the other rows running in the for loop...
					
					if(j==4) {
						break;
					}
					
	}

}
	}
	
}