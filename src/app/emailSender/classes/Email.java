package app.emailSender.classes;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email
{
    private static String HOST = "smtp.gmail.com";
    private static int PORT = 465;
    private static String AUTHOR;
    private static String PASSWORD;
    private static String RECIPIENT;
    private static String TOPIC;
    private static String CONTENT;

    protected Email(String AUTHOR, String PASSWORD, String RECIPIENT, String TOPIC, String CONTENT) throws MessagingException
    {
        this.AUTHOR = AUTHOR;
        this.PASSWORD = PASSWORD;
        this.RECIPIENT = RECIPIENT;
        this.TOPIC = TOPIC;
        this.CONTENT = CONTENT;
        sendEmail();
    }

    private void sendEmail() throws MessagingException
    {
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.auth", "true");

        Session mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(true);

        MimeMessage message = new MimeMessage(mailSession);
        message.setSubject(TOPIC);
        message.setContent(CONTENT, "text/plain; charset=ISO-8859-2");
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(RECIPIENT));

        Transport transport = mailSession.getTransport();
        transport.connect(HOST, PORT, AUTHOR, PASSWORD);

        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }
}