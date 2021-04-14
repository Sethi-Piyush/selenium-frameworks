package org.selenium.iris.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by amura on 17/1/19.
 */
public class ZipUtils {
    public static void zip(){
        byte[] buffer = new byte[1024];
        try{
            FileOutputStream fos = new FileOutputStream("/home/amura/Desktop/iris/target/output-reports/cucumber-extent-report/apartment-booking_peninsula.zip");
            ZipOutputStream zos = new ZipOutputStream(fos);
            ZipEntry ze= new ZipEntry("report.html");
            zos.putNextEntry(ze);
            FileInputStream in = new FileInputStream("/home/amura/Desktop/iris/target/output-reports/cucumber-extent-report/apartment-booking_peninsula.html");

            int len;
            while ((len = in.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
            in.close();
            zos.closeEntry();
            //remember close it
            zos.close();
            System.out.println("File is converted to zip");

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

}
