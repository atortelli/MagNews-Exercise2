package Alessandro.smtpApp;

import java.awt.Dimension;

/*
 * HelloWorldSwing.java requires no other files. 
 */
       

import java.time.Duration;
import java.time.Instant;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class App {
	public static Dialog d;
	public static JRadioButton rbSSL,rbtls;
	public static TextField portTextField,hostSmtpTF,senderEmailTF,passwordTF,receiverMailTF,mailSubjectTF;
 public static void main(String[] args) {
	 
  d = new Dialog(((Window)null),"Mail benchmark application");
  d.setSize(800, 600);
  d.setLayout(null);
 
  Button b=new Button("Chiudi");
  Button invia=new Button("Invia");
  b.addActionListener(closeEvent);

  b.setSize(100,30);
  b.setLocation(100,570);
  
  invia.setSize(100,30);
  invia.setLocation(0, 570);
  invia.addActionListener(inviaEvent);
  
  //Labels
  Label l1=new Label("Authentication:");
  l1.setText("Authentication:");
  l1.setSize(100,30);
  l1.setLocation(0, 30);
  Label l2=new Label("Port:");
  l2.setSize(50,30);
  l2.setLocation(0,60);
  Label l3=new Label("Smtp Host:");
  l3.setSize(80,30);
  l3.setLocation(100,60);
  Label l4=new Label("Sender email:");
  l4.setLocation(330, 60);
  l4.setSize(100,30);
  
  Label l5=new Label("Sender Password:");
  l5.setLocation(330, 90);
  l5.setSize(115,30);
  Label l6=new Label("Receiver email:");
  l6.setLocation(0, 120);
  l6.setSize(95,30);
  Label l7=new Label("Mail Subject:");
  l7.setLocation(0, 150);
  l7.setSize(95,30);
  
  //radio button
  rbSSL=new JRadioButton();
  rbSSL.setText("SSL");
  rbSSL.setLocation(100,30);
  rbSSL.setSize(100,30);
  rbtls=new JRadioButton();
  rbtls.setText("tls");
  rbtls.setLocation(200,30);
  rbtls.setSize(100,30);
  ButtonGroup bg=new ButtonGroup();
  bg.add(rbSSL);
  bg.add(rbtls);
  
  //TextFields
  portTextField=new TextField();
  portTextField.setSize(50,25);
  portTextField.setLocation(50, 60);
  portTextField.setText("587");
  hostSmtpTF=new TextField();
  hostSmtpTF.setSize(150,25);
  hostSmtpTF.setLocation(180,60);
  hostSmtpTF.setText("smtp-mail.outlook.com");
  senderEmailTF=new TextField();
  senderEmailTF.setSize(350,25);
  senderEmailTF.setLocation(450,60);
  senderEmailTF.setText("aletort@hotmail.it");
  passwordTF=new TextField();
  passwordTF.setSize(100, 25);
  passwordTF.setLocation(450, 90);
  passwordTF.setEchoChar('*');
  receiverMailTF=new TextField();
  receiverMailTF.setSize(350, 25);
  receiverMailTF.setLocation(95,120);
  mailSubjectTF=new TextField();
  mailSubjectTF.setSize(350,25);
  mailSubjectTF.setLocation(95,150);
  
  //hostSmtpTF.setEchoChar('*');
  d.add(b);
  d.add(invia);
  d.add(rbSSL);
  d.add(rbtls);
  d.add(l1);
  d.add(l2);
  d.add(l3);
  d.add(l4);
  d.add(l5);
  d.add(l6);
  d.add(l7);
  d.add(portTextField);
  d.add(hostSmtpTF);
  d.add(senderEmailTF);
  d.add(passwordTF);
  d.add(receiverMailTF);
  d.add(mailSubjectTF);
  //JOptionPane.showMessageDialog(d, "Eggs are not supposed to be green.");
  d.setVisible(true);
 }


 private static ActionListener closeEvent = new ActionListener() {

     public void actionPerformed(ActionEvent e) {
    	 System.exit(0);
    	 return;
     }
 };
 private static ActionListener inviaEvent = new ActionListener() {

     public void actionPerformed(ActionEvent e) {
    	 EmailUtil myEmail=new EmailUtil();
    	 /*
    	final String fromEmail = "alessandro.tortelli@gmail.com"; //requires valid gmail id
    	final String toEmail = "aletort@hotmail.it"; // can be any email id 
    		*/	
    	 Instant t1=Instant.now();
    	 
    	 final String fromEmail = senderEmailTF.getText(); //requires valid gmail id
			final String password = passwordTF.getText(); // correct password for gmail id
			final String toEmail = receiverMailTF.getText(); // can be any email id
    			
    			System.out.println("SSLEmail Start");
    			
    			
    			if (true){//if (rbtls.isSelected()){
    				myEmail.props.put("mail.smtp.starttls.enable", "true");

    			}else{
    				myEmail.props.put("mail.smtp.socketFactory.port", portTextField.getText());
    				myEmail.props.put("mail.smtp.socketFactory.class",
    						"javax.net.ssl.SSLSocketFactory");

    			}
    			myEmail.props.put("mail.smtp.auth", "true");
    			myEmail.props.put("mail.smtp.host",  hostSmtpTF.getText());
    			myEmail.props.put("mail.smtp.port", portTextField.getText());
				
     			myEmail.createNewSession(fromEmail, password);
    			
    			/*
    			 Properties props = new Properties();
    			 props.put("mail.smtp.host", "smtp.gmail.com");
     			 props.put("mail.smtp.port", "587");
    			 props.put("mail.imap.ssl.enable", "true"); // required for Gmail
    			 props.put("mail.imap.auth.mechanisms", "XOAUTH2");
    			 Session session = Session.getInstance(props);
    			 try{
    			 Store store = session.getStore("imap");
    			 store.connect("imap.gmail.com", "alessandro.tortelli", "btzupp");
    			 }catch(Exception ex){
    				 ex.printStackTrace();
    			 }
    			*/
     			System.out.println("Session created");
     			try{
     				myEmail.sendEmail(toEmail, mailSubjectTF.getText(),"nuova speranza");
     			 }catch(Exception ex){
    				 ex.printStackTrace();
    			 }
    			
    			//Session session = Session.getDefaultInstance(props, auth);
    			
    		        //.sendEmail(session, toEmail,"SSLEmail Testing Subject", "SSLEmail Testing Body");

    		        /*EmailUtil.sendAttachmentEmail(session, toEmail,"SSLEmail Testing Subject with Attachment", "SSLEmail Testing Body with Attachment");

    		        EmailUtil.sendImageEmail(session, toEmail,"SSLEmail Testing Subject with Image", "SSLEmail Testing Body with Image");
					*/
    		    Instant t2=Instant.now();
    		    System.out.println(Duration.between(t1, t2).toMillis()+" ms");
    		}
     };
 
}
 
 