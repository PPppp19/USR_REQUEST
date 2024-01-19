/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.app.get;

/**
 *
 * @author Monthon
 */
import com.br.api.app.connect.ConnectDB2;
import java.io.File;
import java.io.OutputStream;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
//import static javafx.scene.text.Font.font;
//import static javafx.scene.text.Font.font;
//import static javafx.scene.text.Font.font;
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import static java.lang.System.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Send_mail {

    public static void Sendmail_ICT(String Emailict, String LinkCreate, String SubjectEmail) {

        final String auth_host = "mail.br-bangkokranch.com";
        final String auth_email = "itcenter@br-bangkokranch.com";
        final String auth_password = "10052508";

        Properties props = new Properties();
        props.put("mail.smtp.host", auth_host);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");

        try {

            Session mailSession = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication
                        getPasswordAuthentication() {
                    return new PasswordAuthentication(auth_email, auth_password);
                }
            });

            Message message = new MimeMessage(mailSession);

            message.setFrom(new InternetAddress(auth_email)); // From

            /**
             * * Recipient **
             */
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(Emailict)); // To
            message.setSubject(SubjectEmail);
            message.setText(LinkCreate);
            message.setContent(LinkCreate, "text/html; charset=utf-8");

            Transport.send(message);

//            Updateheadrequest(reqno, DATENOW, cono, divi, Appname);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void Sendmail_User(String Emailuser, String LinkAcknow, String SubjectEmail, String Datenow, String USREQNO, String USLINE, String USCREBY) {

        final String auth_host = "mail.br-bangkokranch.com";
        final String auth_email = "itcenter@br-bangkokranch.com";
        final String auth_password = "10052508";

        Properties props = new Properties();
        props.put("mail.smtp.host", auth_host);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");

        try {

            Session mailSession = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication
                        getPasswordAuthentication() {
                    return new PasswordAuthentication(auth_email, auth_password);
                }
            });

            Message message = new MimeMessage(mailSession);

            message.setFrom(new InternetAddress(auth_email)); // From
            /**
             * * Recipient **
             */
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(Emailuser)); // To
            message.setSubject(SubjectEmail);
            message.setText(LinkAcknow);
            message.setContent(LinkAcknow, "text/html; charset=utf-8");

            Transport.send(message);

            UpdateAcknowledge(USREQNO, USLINE, Datenow, USCREBY);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    private static void UpdateAcknowledge(String USREQNO, String USLINE, String Datenow, String USCREBY) {
        String Date = Datenow.replaceAll("-", "");

        try {

            Connection conn = ConnectDB2.ConnectionDB();
            Statement s = conn.createStatement();
            String sql = "UPDATE BRLDTA0100.USR_REQUEST\n"
                    + "SET USCREBY = '" + USCREBY + "', USCREDT = '" + Date + "', USSTAT = '15'\n"
                    + "WHERE USREQNO = '" + USREQNO.trim() + "' \n"
                    + "AND USLINE = '" + USLINE.trim() + "'";

            s.executeUpdate(sql);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            out.println(e.getMessage());

        }

    }

}
