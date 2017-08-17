package net.investRequest.db;

import java.util.ArrayList;
import java.util.Date;

public interface InvestRequestMethod {

	//���� ��û ��Ȳ �ѷ��ִ� �޼ҵ�(ī�װ��� �˻�� �޾Ƽ�...)
	public ArrayList<InvestRequestDto> ListInvestRequest(String category, String search, int pageSize); 
	
	//���� ��û �޼ҵ�
	public int InsertInvestRequst(InvestRequestDto irdto);
	
	//���� ��ϵȰ��� �ҷ����� �޼ҵ�
	public InvestRequestDto getInvestRequest(int num);	
	
	//���� ��Ȳ �˾ƺ��� �޼ҵ� ����? ����? ����?
	public int ConditionInvest(int num, Date endDay, int successMoney);
	
	
}
