package org.selenium.iris.config;

import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * Created by amura on 17/1/19.
 */
public class Email {
    public static void sendMail(String emailFrom, String emailTo) throws IOException {
        // Create object of Property file
        Properties props = new Properties();

        props = System.getProperties();

        String [] to = new String[2];


        // this will set host of server- you can change based on your requirement
        props.setProperty("mail.smtp.host", "smtp.gmail.com");

        // set the port of socket factory
        props.put("mail.smtp.socketFactory.port", "465");

        // set socket factory
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

        // set the authentication to true
        props.put("mail.smtp.auth", "true");

        // set the port of SMTP server
        props.put("mail.smtp.port", "465");

        // This will handle the complete authentication
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("piyush@amuratech.com","yqouevfvghaqjybg");
                    }
                });
        try {
            // Create object of MimeMessage class
            Message message = new MimeMessage(session);

            // Set the from address
            message.setFrom(new InternetAddress(emailFrom));

            // Set the recipient address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
//            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(emailCC) );

            // Add the subject link
            message.setSubject("Testing report- 'Add Booking by a Customer'");

            // Create object to add multimedia type content
            BodyPart messageBodyPart1 = new MimeBodyPart();

            // Set the body of email
            messageBodyPart1.setText("Please find below the testing report for 'Add Booking'");

            // Create another object to add another content
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();

            // Mention the file which you want to send
            String filename = "/home/amura/Desktop/iris/target/output-reports/cucumber-extent-report/apartment-booking_peninsula.zip";

            // Create data source and pass the filename
            DataSource source = new FileDataSource(filename);

            // set the handler
            messageBodyPart2.setDataHandler(new DataHandler(source));

            // set the file
            messageBodyPart2.setFileName(source.getName());
//            messageBodyPart2.setFileName(filename);

            // Create object of MimeMultipart class
            Multipart multipart = new MimeMultipart();

            // add body part 1
            multipart.addBodyPart(messageBodyPart2);

            // add body part 2
            multipart.addBodyPart(messageBodyPart1);

            // set the content
            message.setContent(multipart);

            // finally send the email
            Transport.send(message);
            System.out.println("\n=====Email Sent=====");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
