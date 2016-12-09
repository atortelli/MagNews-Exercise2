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
/* 
 * this class is an utility to create and send mail
 */
	public class EmailUtil {

		public Transport transport=null;
		public Session session=null;
		public Properties props;
		public String fromMail;
		public String body;
		public String user;
		public String password;
		/* 
		 * Create the body of the mail to send
		 * @param bodySize size of the body of mail in bytes
		 */
		void createBody(int bodySize)
		{
			body="";
			String strToShow=new String("This email is for benchmark purpose.\n");
			int i=0;
			for (i=0;i<(bodySize-strToShow.length());i+=strToShow.length()){
				body=body+strToShow;
			}
			body=body+strToShow.substring(0, (bodySize-body.length()));
		}
		/*
		 * create transport object used to send mails
		 */
		void createNewTransport() throws Exception
		{
			transport = session.getTransport("smtp");
		}
		/*
		 * Constructor
		 * inizialize props object
		 */
		public EmailUtil()
		{
			props=new Properties();
		}
		
		/**
		 * sendEmail
		 * send a mail
		 * session and transport object must be inizialized in a correct way  
		 * @param toEmail email of the receiver
		 * @param subject subject of the email
		 */
		public void sendEmail(String toEmail, String subject) throws Exception
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
		    
		      transport.send(msg);
		      System.out.println("EMail Sent Successfully!!");
		    
		}
		/*
		 * create session object
		 * @input email mail address of the receiver
		 * @input password password associated with mail address
		 */
		public void createNewSession(final String email,final String password){
			fromMail=email;
			session = Session.getInstance(props, new javax.mail.Authenticator() {
			    protected PasswordAuthentication getPasswordAuthentication() {
			        return new PasswordAuthentication(email, password);
			    }
			});
			
		}
	}
