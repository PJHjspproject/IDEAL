package net.Card.db;

public class CardDto {

	private String cardNum;//카드번호
	private int cardPass;//카드비밀번호(앞두자리)
	private int cardBalance;//카드에 남은 잔액
	private String memberEmail;//회원 이메일
	private String cardBank;//카드은행사
	private String memberBirth;
	public String getMemberBirth() {
		return memberBirth;
	}
	public void setMemberBirth(String memberBirth) {
		this.memberBirth = memberBirth;
	}
	public int getCardExpiry() {
		return cardExpiry;
	}
	public void setCardExpiry(int cardExpiry) {
		this.cardExpiry = cardExpiry;
	}
	private int cardExpiry;
	
	public String getCardBank() {
		return cardBank;
	}
	public void setCardBank(String cardBank) {
		this.cardBank = cardBank;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public int getCardPass() {
		return cardPass;
	}
	public void setCardPass(int cardPass) {
		this.cardPass = cardPass;
	}
	public int getCardBalance() {
		return cardBalance;
	}
	public void setCardBalance(int cardBalance) {
		this.cardBalance = cardBalance;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	
	
	
	
}
