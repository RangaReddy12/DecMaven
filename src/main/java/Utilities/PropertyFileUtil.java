package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.Reporter;

public class PropertyFileUtil {
public static String getValueForKey(String key)throws Throwable
{
	
	Properties confingProperties= new Properties();
	confingProperties.load(new FileInputStream("D:\\OJTFrameWorks\\StockAccounting_Maven\\PropertyFile\\Environment.properties"));
	return confingProperties.getProperty(key);
}

}










