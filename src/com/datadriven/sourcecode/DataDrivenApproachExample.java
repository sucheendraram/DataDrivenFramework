package com.datadriven.sourcecode;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DataDrivenApproachExample {

	public static void main(String[] args) {
		try {
			ChromeDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			FileInputStream fis = new FileInputStream(
					"D:\\ExcelR\\Selenium_830_1030\\DataDrivenFramework\\src\\com\\datadriven\\utils\\data.properties");
			Properties p = new Properties();
			p.load(fis);
			String url = p.getProperty("url");
			String mathOpt = p.getProperty("choose_math_option");
			String principalAmt = p.getProperty("principal_amount");
			String rateOfInterest = p.getProperty("interest_rate");
			String interestUnitOptionIndex = p.getProperty("interest_units_index");
			String desiredTime = p.getProperty("desired_time");
			String desiredTimeOptionIndex = p.getProperty("desired_time_units_index");

			driver.get(url);

			driver.findElement(By.linkText("General Math")).click();
			Thread.sleep(2000);
			WebElement topic = driver.findElement(By.id("topicItem"));
			Select chooseMath = new Select(topic);
			chooseMath.selectByVisibleText(mathOpt);
			Thread.sleep(2000);

			driver.findElement(By.name("principal")).sendKeys(principalAmt);
			Thread.sleep(2000);

			driver.findElement(By.name("interest")).sendKeys(rateOfInterest);
			Thread.sleep(2000);

			WebElement interestUnits = driver.findElement(By.name("interest_units"));

			Select interestUnitsDropdown = new Select(interestUnits);

//			Integer.parseInt(desiredTimeOptionIndex) This line will convert the String to Integer format
			interestUnitsDropdown.selectByIndex(Integer.parseInt(interestUnitOptionIndex));

			Thread.sleep(2000);

			driver.findElement(By.name("desired_time")).sendKeys(desiredTime);
			Thread.sleep(2000);

			WebElement desiredTimeUnits = driver.findElement(By.name("desired_time_units"));
			Select desiredTimeUnitsDropdown = new Select(desiredTimeUnits);
			desiredTimeUnitsDropdown.selectByIndex(Integer.parseInt(desiredTimeOptionIndex));
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("input[type='submit'][value='Find the amount of interest']")).click();
			Thread.sleep(5000);
			driver.quit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
