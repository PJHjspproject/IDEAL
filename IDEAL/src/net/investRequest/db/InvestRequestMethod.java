package net.investRequest.db;

import java.util.ArrayList;
import java.util.Date;

public interface InvestRequestMethod {

	//투자 요청 현황 뿌려주는 메소드(카테고리와 검색어를 받아서...)
	public ArrayList<InvestRequestDto> ListInvestRequest(String category, String search, int pageSize); 
	
	//투자 요청 메소드
	public int InsertInvestRequst(InvestRequestDto irdto);
	
	//투자 등록된것을 불러오는 메소드
	public InvestRequestDto getInvestRequest(int num);	
	
	//투자 현황 알아보는 메소드 성공? 실패? 진행?
	public int ConditionInvest(int num, Date endDay, int successMoney);
	
	
}
