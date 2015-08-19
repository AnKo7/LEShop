package main.java.setup;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;

public class LEShopHelper {

    public static void getScreenshot(WebDriver driver) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String fileName = "OSX\\Users\\chumak\\LEShop\\screenshots\\img" + Calendar.getInstance().getTimeInMillis() + ".png";
            FileUtils.copyFile(scrFile, new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveToDb(scrFile);
    }

    public static void saveToDb(File scrFile) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        java.sql.Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/main.java.test", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        java.sql.PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement ("INSERT INTO img (img) VALUES (?)");

            pstmt.setInt (1, 100);
            FileInputStream is = null;
            try {
                is = new FileInputStream( scrFile );
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            pstmt.setBinaryStream (1, is, (int) scrFile.length() );
            pstmt.execute ();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

