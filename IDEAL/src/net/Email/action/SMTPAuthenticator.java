package net.Email.action;

import javax.mail.*;

public class SMTPAuthenticator extends Authenticator{
	String userid = "";
	String userpassword = "";
	public SMTPAuthenticator() {}
	public SMTPAuthenticator(String userid, String userpassword) {
		this.userid = userid;
		this.userpassword = userpassword;
	}
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userid, userpassword);
	}
}
