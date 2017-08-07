package net.Investment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.Card.db.CardDao;
import net.Card.db.CardDto;
import net.Investment.db.InvestmentDao;
import net.Investment.db.InvestmentDto;
import net.investRequest.db.InvestRequestDao;
import net.investRequest.db.InvestRequestDto;


public class investmentAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CardDto cardDto = new CardDto();
		CardDao cardDao = new CardDao();
		InvestRequestDto ivrDto = new InvestRequestDto();
		InvestRequestDao ivrDao = new InvestRequestDao();
		InvestmentDto imDto = new InvestmentDto();
		InvestmentDao imDao = new InvestmentDao();
		HttpSession session = request.getSession();
		
		// 카드정보 비교하기
		
		String cardNum1 = request.getParameter("cardNum1");		
		String cardNum2 = request.getParameter("cardNum2");		
		String cardNum3 = request.getParameter("cardNum3");		
		String cardNum4 = request.getParameter("cardNum4");		
		String cardNum = cardNum1+cardNum2+cardNum3+cardNum4;	
		
		String cardExpiryM = request.getParameter("cardExpiryM");	
		String cardExpiryY = request.getParameter("cardExpiryY");	
		int cardExpiry = Integer.parseInt(cardExpiryY+cardExpiryM); 
		
		int cardPass = Integer.parseInt(request.getParameter("cardPass"));
		String memberBirth = request.getParameter("memberBirth");	
		String cardBank = request.getParameter("cardBank");	
		 
		 
		cardDto.setCardNum(cardNum);		
		cardDto.setCardPass(cardPass); 		
		cardDto.setMemberBirth(memberBirth);
		cardDto.setCardExpiry(cardExpiry);	
		cardDto.setCardBank(cardBank);		
		
		
		int result = cardDao.getOneCard(cardDto);	
		System.out.println(result);
		
		
		int investRequestNum = Integer.parseInt(request.getParameter("InvestRequestNum"));
		String memberEmail = request.getParameter("memberEmail");
		String investName = request.getParameter("investName");
		int investMoney = Integer.parseInt(request.getParameter("InvestMoney"));
		String program = request.getParameter("program");

		
		
		imDto.setInvestRequestNum(investRequestNum);
		imDto.setMemberEmail(memberEmail);
		imDto.setInvestName(investName);
		imDto.setInvestMoney(investMoney);
		imDto.setProgram(program);
		
		
		// 카드 정보 비교하여 입력한 카드정보와 DB 카드정보가 일치하면 result가 1로 변경 됨
		//result가 1일 시 
		if(result==1){
			
			//투자요청 게시물 현재 금액(nowMoney)에 투자금액 증가
			int nowMoney=Integer.parseInt(request.getParameter("InvestMoney"));
			int InvestRequestNum = Integer.parseInt(request.getParameter("InvestRequestNum"));
			
			ivrDto.setNowMoney(nowMoney);

			ivrDao.increaseNowMoney(nowMoney, InvestRequestNum);
			
			//카드 가져 오기
			cardDao.getPayCard(cardNum);
			
			//카드 금액 차감
			
			cardDao.deductBalance(nowMoney, cardNum);
			
			
			
			imDao.InsertInvestment(imDto);
			
			//투자금액 100% 모집시 fundsituation 변경
			
			ivrDao.updateFundsituation(InvestRequestNum);
			
			//투자완료 메세지 띄워주기
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('카드 정보가 일치하지 않습니다');");
			out.print("location.href='index.im';");
			out.print("</script>");
			out.close();
			
			return null;
			//result가 0이면 카드 정보가 일치하지 않습니다 안내문 띄워주기
		}else if(result==0){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('카드 정보가 일치하지 않습니다.\\n카드정보를 다시 확인하여 주세요.');");
			out.print("location.href='investment.im';");
			out.print("</script>");
			out.close();
			return null;
	}
		return null;
		
	
	}
}
