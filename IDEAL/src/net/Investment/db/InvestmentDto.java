package net.Investment.db;

import java.sql.Date;

public class InvestmentDto {
	
	private int investNum;
	private String memberEmail;//�����ڸ���
	private int investMoney;//���ڱݾ�
	private int investRequestNum;//���ڹ�ȣ
	private String program;//���� ���α׷� �̸�
	private String investName;//�������̸�
	private Date investDate;//���ڳ�¥
	
	public Date getInvestDate() {
		return investDate;
	}
	public void setInvestDate(Date investDate) {
		this.investDate = investDate;
	}
	public int getInvestRequestNum() {
		return investRequestNum;
	}
	public int getInvestNum() {
		return investNum;
	}
	public void setInvestNum(int investNum) {
		this.investNum = investNum;
	}
	public int getInvestMoney() {
		return investMoney;
	}
	public void setInvestMoney(int investMoney) {
		this.investMoney = investMoney;
	}
	public String getInvestName() {
		return investName;
	}
	public void setInvestName(String investName) {
		this.investName = investName;
	}
	public void setInvestRequestNum(int investRequestNum) {
		this.investRequestNum = investRequestNum;
	}
	
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	
	
	
	
	
	
	
}
