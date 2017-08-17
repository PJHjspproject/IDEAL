package net.Investment.db;

import java.util.ArrayList;
import java.util.Date;


import net.investRequest.db.InvestRequestDto;

public interface InvestmentMethod {
	
	//투자자 등록 메소드
	public void InsertInvestment(InvestmentDto imDto);
	
	//투자 변경 메소드
	public int UpdateInvestment(InvestmentDto idto);
	
	//투자자 리스트 메소드
	public ArrayList<InvestmentDto> ListInvestment(InvestmentDto idto);
	
	//투자 진행률 메소드
	public int PrograssRateInvest(InvestmentDto idto, InvestRequestDto irdto);
	
}
