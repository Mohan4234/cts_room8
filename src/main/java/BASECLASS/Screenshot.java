package BASECLASS;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {

	WebDriver dr;
	
	public Screenshot(WebDriver dr) {
		this.dr=dr;
	}
public void Take_screenshot() throws IOException {
	
	
	//taking screenshot
	File source = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
   
    //setting path to the screenshot storing
    FileUtils.copyFile(source, new File("C:\\Users\\Hp\\eclipse-workspace\\JPET\\Screenshot"));
}
}



