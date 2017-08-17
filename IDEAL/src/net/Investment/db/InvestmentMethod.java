package net.Investment.db;

import java.util.ArrayList;
import java.util.Date;


import net.investRequest.db.InvestRequestDto;

public interface InvestmentMethod {
	
	//������ ��� �޼ҵ�
	public void InsertInvestment(InvestmentDto imDto);
	
	//���� ���� �޼ҵ�
	public int UpdateInvestment(InvestmentDto idto);
	
	//������ ����Ʈ �޼ҵ�
	public ArrayList<InvestmentDto> ListInvestment(InvestmentDto idto);
	
	//���� ����� �޼ҵ�
	public int PrograssRateInvest(InvestmentDto idto, InvestRequestDto irdto);
	
}
