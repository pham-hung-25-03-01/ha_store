package com.ha_store.dao;

import android.os.AsyncTask;

import java.util.Properties;

import javax.mail.*;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;


public class SendEmail extends AsyncTask<String, Void, String> {

    public static Boolean send(String recipient, String body)
    {
        // email ID of  Sender.
        String sender = "ha.store@gmail.com";

        // using host as localhost
        String host = "10.0.2.2";

        // Getting system properties
        Properties properties = System.getProperties();

        // Setting up mail server
        properties.setProperty("mail.smtp.host", host);

        // creating session object to get properties
        Session session = Session.getDefaultInstance(properties);

        try
        {
            // MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From Field: adding senders email to from field.
            message.setFrom(new InternetAddress(sender));

            // Set To Field: adding recipient's email to from field.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            // Set Subject: subject of the email
            message.setSubject("QUÊN MẬT KHẨU");

            // set body of the email.
            message.setText(body);

            // Send email.
            Transport.send(message);
            System.out.println("Mail successfully sent");
            return true;
        }
        catch (MessagingException mex)
        {
            mex.printStackTrace();
            return false;
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        send(strings[0], strings[1]);
        return null;
    }
}
