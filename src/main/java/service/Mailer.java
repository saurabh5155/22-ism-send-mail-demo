package service;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {
public static void send(String to,String subject,String msg){

final String user="s.a.patel5515@gmail.com";//change accordingly
final String pass="password";

//1st step) Get the session object	
Properties prop = new Properties();
//props.put("Gmail.com", "smtp.gmail.com");//change accordingly
//props.put("mail.smtp.auth", "true");

prop.put("mail.smtp.auth", "true");
prop.put("mail.smtp.starttls.enable", "true");
prop.put("mail.smtp.host", "smtp.gmail.com");
prop.put("mail.smtp.port", "587");
prop.put("mail.smtp.ssl.enable", "false");

System.out.println(prop);
Session session = Session.getDefaultInstance(prop,
 new javax.mail.Authenticator() {
  protected PasswordAuthentication getPasswordAuthentication() {
	  System.out.println("PasswordAuthentication"+ new PasswordAuthentication(user,pass));
   return new PasswordAuthentication(user,pass);
   }
});
//2nd step)compose message
System.out.println(session);
try {
 MimeMessage message = new MimeMessage(session);
 message.setFrom(new InternetAddress(user));
 message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
 message.setSubject(subject);
 message.setText(msg);
 
 //3rd step)send message
 Transport.send(message);

 System.out.println("Done");

 } catch (MessagingException e) {
	throw new RuntimeException(e);
 }
	
}
}
