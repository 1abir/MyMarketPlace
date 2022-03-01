package sms.hu.ozekisms;

import java.io.*;
import java.util.Scanner;

public class TestTcpSms implements Runnable{

	/**
	 * @param args
	 */

	private String message;
	private String mobileNumber;

	public TestTcpSms(String message,String mob) {
		this.message = message;
		this.mobileNumber="+88"+mob;
		Thread thread=new Thread(this);
		thread.start();
	}

	public void run(){

		try {
			Scanner sc = new Scanner(System.in);


			String host = "localhost";
			int port = 9500;
			String username = "admin";
			String password = "abc123";

			/**
			 * Connect to Ozeki NG SMS Gateway and logging in.
			 */

			MyOzSmsClient osc = new MyOzSmsClient(host, port);
			osc.login(username, password);;
			System.out.println("SMS message:");

			/**
			 * If logged in send "Text message" to number "+00112233"
			 */

			if(osc.isLoggedIn()) {

				osc.sendMessage("+8801515261139", message);

			}
			else
			{
				System.out.println("logged out");
				String s= osc.sendMessage(mobileNumber, message);

				System.out.println(s);
				System.out.println(message+mobileNumber);
			}

			//sc.nextLine();
			osc.logout();
			
			/**
			 * Receiving message:
			 * 
			 * If you want to receive messages you can use doOnMessageReceived in MyOzSmsClient.java
			 * That's an event, which runs automatically when a message is received. 
			 */
			
		} catch (IOException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}
}
