package Alessandro.smtpApp;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
/*
 * this class is used to benchmarks of mail sending 
 */
public class Benchmark {
	//initialize email object
	public EmailUtil email=new EmailUtil();
	/*
	 * do benchmark by sending multiple email to a receiver
	 * use an unique connection for all mails sent
	 * email object must be iniialized in the right way
	 * nMail number of mails to send
	 * fromMail mail address of the sender
	 * toMail mail address of the receiver
	 * password password of mail account of the sender
	 * subject Subject of the mails
	 */
	public BenchmarkResult doBenchmarkUniqueConnection(int nMail,String fromMail,String toMail,String password,
			String subject) 
			throws Exception
	{
		BenchmarkResult r1;
		r1=new BenchmarkResult();
		ArrayList<Long> times=new ArrayList<Long>();
		//connect to server
		email.transport.connect(fromMail, password);
		//send mails for benchmark
		for (int i=0;i<nMail;i++){
			Instant t1= Instant.now();
			email.sendEmail(toMail, subject);
			Instant t2=Instant.now();
			times.add(Duration.between(t1, t2).toMillis());
			System.out.println(times.get(i)+" ms");
		}
		//close connection
		email.transport.close();
		
		//computing mean max min
		long tot=0;
		r1.max=times.get(0);
		r1.min=times.get(0);
		for (int i=0;i<nMail;i++){
			r1.max=(times.get(i)>r1.max)?times.get(i):r1.max;
			r1.min=(times.get(i)<r1.min)?times.get(i):r1.min;
			tot+=times.get(i);
			
		}
		//put result of benchmark on the output object
		r1.mean=(tot/nMail);
		r1.multipleConnections=false;
		r1.emailSize=email.body.length();
		r1.nEmail=nMail;
		return r1;
	}
	/*
	 * do benchmark by sending multiple email to a receiver
	 * use an multiple connections one for every mail sent
	 * email object must be initialized in the right way
	 * nMail number of mails to send
	 * fromMail mail address of the sender
	 * toMail mail address of the receiver
	 * password password of mail account of the sender
	 * subject Subject of the mails
	 */
	public BenchmarkResult doBenchmarkMultipleConnection(int nMail,String fromMail,String toMail,String password,
			String subject) 
			throws Exception
	{
		BenchmarkResult r1;
		r1=new BenchmarkResult();
		ArrayList<Long> times=new ArrayList<Long>();
		//do benchmark by sending mails 
		for (int i=0;i<nMail;i++){
			Instant t1= Instant.now();
			//connect to server
			email.transport.connect(fromMail, password);
			//send mail
			email.sendEmail(toMail, subject);
			//close connection
			email.transport.close();
			Instant t2=Instant.now();
			times.add(Duration.between(t1, t2).toMillis());
			System.out.println(times.get(i)+" ms");
		}
		
		
		//computing mean max min
		long tot=0;
		r1.max=times.get(0);
		r1.min=times.get(0);
		for (int i=0;i<nMail;i++){
			r1.max=(times.get(i)>r1.max)?times.get(i):r1.max;
			r1.min=(times.get(i)<r1.min)?times.get(i):r1.min;
			tot+=times.get(i);
			
		}

		//put result of benchmark on the output object
		r1.mean=(tot/nMail);
		r1.multipleConnections=true;
		r1.emailSize=email.body.length();
		r1.nEmail=nMail;
		return r1;
	}
};
