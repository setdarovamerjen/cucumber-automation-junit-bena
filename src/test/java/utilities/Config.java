package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

   private static Properties properties;
    //why we put it in static ?
    //we need it only to run once, once our properties file loaded to filInputStream file,
    // then I only need read from it and that's it. I dont need to run it everytime when I read something from properties file
   static {

       try( FileInputStream fileInputStream= new FileInputStream("src/test/resources/configurations.properties")) {
           properties= new Properties();
           properties.load(fileInputStream);
       } catch (IOException e) {
           System.out.println("File not found");
       }

    }

    public static String getValue(String key){
       return  properties.getProperty(key);
    }


}
