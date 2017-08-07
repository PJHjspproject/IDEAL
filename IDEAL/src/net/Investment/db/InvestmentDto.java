package net.Investment.db;

public class InvestmentDto {
	
	private int investNum;
	private String memberEmail;//투자자메일
	private int investMoney;//투자금액
	private int investRequestNum;//투자번호
	private String program;//투자 프로그램 이름
	private String investName;
	
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
