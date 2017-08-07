package net.Card.db;

public interface CardMethod {

	//카드등록 메소드
	public int InsertCard(CardDto cdto);
	
	//등록된 카드에서 금액을 결제하는 메소드
	public void paymentCard(int CardNum, int price);
	
}
