package net.Card.db;

public class CardDto {

	private String cardNum;//ī���ȣ
	private int cardPass;//ī���й�ȣ(�յ��ڸ�)
	private int cardBalance;//ī�忡 ���� �ܾ�
	private String memberEmail;//ȸ�� �̸���
	private String cardBank;//ī�������
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
