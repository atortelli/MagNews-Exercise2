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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class App {
	//GUI elements declaration
	public static Dialog d;
	public static JRadioButton rbSSL,rbtls,rbMultipleCon,rbUniqueConn;
	public static TextField portTextField,hostSmtpTF,senderEmailTF,passwordTF,receiverMailTF,mailSubjectTF,mailSizeTF,
							nEmailTF;
	public static JTable outputTab;
	public static Label msg; 
 public static void main(String[] args) {
	//Constructing the GUI
  d = new Dialog(((Window)null),"Mail benchmark application");
  d.setSize(800, 600);
  d.setLayout(null);
 
  Button b=new Button("Close");
  Button invia=new Button("Send email(s)");
  b.addActionListener(closeEvent);

  b.setSize(100,30);
  b.setLocation(100,570);
  
  invia.setSize(100,30);
  invia.setLocation(0, 570);
  invia.addActionListener(inviaEvent);
  
  msg=new Label("Ready.");
  msg.setLocation(250,570);
  msg.setSize(500,30);
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
  Label l8=new Label("Mail size:(bytes)");
  l8.setLocation(0, 180);
  l8.setSize(110, 30);
  Label l9=new Label("Number of email to send:");
  l9.setLocation(0,210);
  l9.setSize(180, 30);
  
  //radio buttons
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
  
  rbMultipleCon=new JRadioButton();
  rbMultipleCon.setText("Multiple Connections");
  rbMultipleCon.setLocation(0, 240);
  rbMultipleCon.setSize(200,30);
  rbUniqueConn=new JRadioButton();
  rbUniqueConn.setText("Unique connection");
  rbUniqueConn.setLocation(200, 240);
  rbUniqueConn.setSize(200, 30);
  ButtonGroup bgConn=new ButtonGroup();
  bgConn.add(rbMultipleCon);
  bgConn.add(rbUniqueConn);
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
  receiverMailTF.setLocation(110,120);
  mailSubjectTF=new TextField();
  mailSubjectTF.setSize(350,25);
  mailSubjectTF.setLocation(110,150);
  mailSizeTF=new TextField();
  mailSizeTF.setSize(150,25);
  mailSizeTF.setLocation(110, 180);
  nEmailTF=new TextField();
  nEmailTF.setSize(200,25);
  nEmailTF.setLocation(180,210);
  
  //Tabella
  outputTab=new JTable(new DefaultTableModel(new Object[]{"Number of email sent", "Email size(bytes)",
		  "Multiple connection","Mean Time (ms)","Min time (ms)","Max time (ms)"},0));
  JScrollPane jspT=new JScrollPane(outputTab);
  jspT.setSize(700,300);
  jspT.setLocation(50, 270);
  //Add element to Dialog
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
  d.add(l8);
  d.add(l9);
  d.add(portTextField);
  d.add(hostSmtpTF);
  d.add(senderEmailTF);
  d.add(passwordTF);
  d.add(receiverMailTF);
  d.add(mailSubjectTF);
  d.add(mailSizeTF);
  d.add(nEmailTF);
  
  d.add(rbMultipleCon);
  d.add(rbUniqueConn);
  d.add(jspT);
  d.add(msg);
  //JOptionPane.showMessageDialog(d, "Eggs are not supposed to be green.");
  d.setVisible(true);
 }

 /*
  * listener for closing event
  * called every times user click on close button
  */
 private static ActionListener closeEvent = new ActionListener() {

     public void actionPerformed(ActionEvent e) {
    	 System.exit(0);
    	 return;
     }
 };
 /*
  * showBenchmarkResult
  * Show benchmark result on output table
  * @param r object with benchmark results
  */
 public static void showBenchmarkResult(BenchmarkResult r)
 {
	 DefaultTableModel model = (DefaultTableModel) outputTab.getModel();
	  model.addRow(new Object[]{r.nEmail, r.emailSize, (r.multipleConnections)?"Yes":"No",r.mean,r.min,r.max});
 }
 /*
  * listener for send button
  * called every time user click on "send mail" button
  * this function execute the benchmarks
  */
 private static ActionListener inviaEvent = new ActionListener() {

     public void actionPerformed(ActionEvent e) {
    	try{
    		Benchmark b=new Benchmark();
  
    		//loading data from the GUI
    		int size=Integer.parseInt(mailSizeTF.getText());
    		b.email.createBody(size);
    		final String fromEmail = senderEmailTF.getText(); //requires valid gmail id
			final String password = passwordTF.getText(); // correct password for gmail id
			final String toEmail = receiverMailTF.getText(); // can be any email id
    			
    		System.out.println("SSLEmail Start");
    			
    		//set properties of email object to send mails
    		if (true){//if (rbtls.isSelected()){
    			b.email.props.put("mail.smtp.starttls.enable", "true");

    		}else{
    			b.email.props.put("mail.smtp.socketFactory.port", portTextField.getText());
    			b.email.props.put("mail.smtp.socketFactory.class",
    				"javax.net.ssl.SSLSocketFactory");

    		}
    		b.email.props.put("mail.smtp.auth", "true");
    		b.email.props.put("mail.smtp.host",  hostSmtpTF.getText());
    		b.email.props.put("mail.smtp.port", portTextField.getText());
			//create new session object
     		b.email.createNewSession(fromEmail, password);
     		//create new transport object
     		b.email.createNewTransport();
     		BenchmarkResult res;
     		msg.setText("Sending mail(s)...");
     		//Start benchmark and return result on res object
     		if (rbUniqueConn.isSelected()){
     			res=b.doBenchmarkUniqueConnection(Integer.parseInt(nEmailTF.getText()), 
     					fromEmail,toEmail, password, mailSubjectTF.getText());
     		}else{
     			res=b.doBenchmarkMultipleConnection(Integer.parseInt(nEmailTF.getText()), 
     					fromEmail,toEmail, password, mailSubjectTF.getText());
     		}
     		showBenchmarkResult(res);
     		msg.setText("Mail(s) sent.Result showed on the output table.");

    	}catch(Exception ex){
    		//error
			 ex.printStackTrace();
			 msg.setText("An error occured. See standard output for details");
		}
    		}
     };
 
}
 
 