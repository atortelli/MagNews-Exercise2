package Alessandro.smtpApp;

	import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

	import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
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

	public class EmailUtil {

		public Session session=null;
		public Properties props;
		public String fromMail;
		public EmailUtil()
		{
			props=new Properties();
		}
		
		/**
		 * Utility method to send simple HTML email
		 * @param session
		 * @param toEmail
		 * @param subject
		 * @param body
		 */
		public void sendEmail(String toEmail, String subject, String body) throws Exception
		{
		    if (session==null){
		    	throw new Exception("session==null!");
		    }
			MimeMessage msg = new MimeMessage(session);
		      //set message headers
		      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
		      msg.addHeader("format", "flowed");
		      msg.addHeader("Content-Transfer-Encoding", "8bit");
		      msg.setSubject(subject, "UTF-8");

		      msg.setText(body, "UTF-8");

		      msg.setSentDate(new Date());
		      msg.setFrom(fromMail);

		      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
		      System.out.println("Message is ready");
	    	  Transport.send(msg);  

		      System.out.println("EMail Sent Successfully!!");
		    
		}
		public void createNewSession(final String email,final String password){
			fromMail=email;
			session = Session.getInstance(props, new javax.mail.Authenticator() {
			    protected PasswordAuthentication getPasswordAuthentication() {
			        return new PasswordAuthentication(email, password);
			    }
			});
			
		}
	}
