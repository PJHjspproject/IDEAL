package net.Card.db;

public interface CardMethod {

	//ī���� �޼ҵ�
	public int InsertCard(CardDto cdto);
	
	//��ϵ� ī�忡�� �ݾ��� �����ϴ� �޼ҵ�
	public void paymentCard(int CardNum, int price);
	
}
