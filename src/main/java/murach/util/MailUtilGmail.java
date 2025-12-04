package murach.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailUtilGmail {

    public static void sendMail(String to, String from,
                                String subject, String body, boolean bodyIsHTML)
            throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.mailersend.net");
        props.put("mail.smtp.port", "2525");   // ✔ Mailersend dùng 587

        final String username = "MS_w4beep@test-3m5jgrom5pmgdpyo.mlsender.net";     // ✔ chỉ dùng MS_xxxxx, KHÔNG phải email
        final String password = "mssp.LvZUL7A.x2p034711j74zdrn.3m09FmN"; // ✔ API key SMTP thật

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        session.setDebug(true);

        Message message = new MimeMessage(session);
        message.setSubject(subject);

        if (bodyIsHTML) {
            message.setContent(body, "text/html; charset=UTF-8");
        } else {
            message.setText(body);
        }

        // ✔ FROM PHẢI LÀ DOMAIN SANDBOX MAILERSEND
        message.setFrom(new InternetAddress(from));

        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

        Transport.send(message);
    }
}
