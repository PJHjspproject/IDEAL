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
		
		String cardNum1 = request.getParameter("cardNum1");		// 카드정보 첫번째 자리 받아오기
		String cardNum2 = request.getParameter("cardNum2");		// 카드정보 두번째 자리 받아오기
		String cardNum3 = request.getParameter("cardNum3");		// 카드정보 세번째 자리 받아오기
		String cardNum4 = request.getParameter("cardNum4");		// 카드정보 네번째 자리 받아오기
		String cardNum = cardNum1+cardNum2+cardNum3+cardNum4;	// 받아온 카드 번호 cardNum에 통합하여 담기
		
		String cardExpiryM = request.getParameter("cardExpiryM");	//카드 유효기간 월 받아오기
		String cardExpiryY = request.getParameter("cardExpiryY");	//카드 유효기간 년 받아오기
		int cardExpiry = Integer.parseInt(cardExpiryY+cardExpiryM); //받아온 카드 유효기간 통합하기
		
		int cardPass = Integer.parseInt(request.getParameter("cardPass")); //카드 비밀번호 받아오기
		String memberBirth = request.getParameter("memberBirth");	//멤버 생년월일 받아오기
		String cardBank = request.getParameter("cardBank");	// 카드 은행정보 받아오기
		 
		 
		cardDto.setCardNum(cardNum);		//카드번호 DTO에 세팅
		cardDto.setCardPass(cardPass); 		//카드 비밀번호 2자리 DTO에 세팅
		cardDto.setMemberBirth(memberBirth);//카드 소유주 생년월일 DTO에 세팅
		cardDto.setCardExpiry(cardExpiry);	//카드 유효기간 DTO에 세팅
		cardDto.setCardBank(cardBank);		//카드 은행 DTO에 세팅
		
		
		int result = cardDao.getOneCard(cardDto);	//카드 정보 비교하기 메소드
		System.out.println(result);
		
		//investment 정보 받아오기
		int investRequestNum = Integer.parseInt(request.getParameter("InvestRequestNum"));
		String memberEmail = request.getParameter("memberEmail");
		String investName = request.getParameter("investName");
		int investMoney = Integer.parseInt(request.getParameter("InvestMoney"));
		String program = request.getParameter("program");
		
//		System.out.println("investment 정보 세팅 확인" + investRequestNum);
//		System.out.println("investment 정보 세팅 확인" + memberEmail);
//		System.out.println("investment 정보 세팅 확인" + investName);
//		System.out.println("investment 정보 세팅 확인" + investMoney);
//		System.out.println("investment 정보 세팅 확인" + program);
		
		//investment 정보 세팅하기
		
		imDto.setInvestRequestNum(investRequestNum);
		imDto.setMemberEmail(memberEmail);
		imDto.setInvestName(investName);
		imDto.setInvestMoney(investMoney);
		imDto.setProgram(program);
		
//		System.out.println("investment 정보 세팅 확인" + imDto.getInvestRequestNum());
//		System.out.println("investment 정보 세팅 확인" + imDto.getMemberEmail());
//		System.out.println("investment 정보 세팅 확인" + imDto.getInvestName());
//		System.out.println("investment 정보 세팅 확인" + imDto.getInvestMoney());
//		System.out.println("investment 정보 세팅 확인" + imDto.getProgram());
		
		
		
		
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
			
			//카드 금액 차감 시 investment DB에 내역 올리기
			
			imDao.InsertInvestment(imDto);
			
			//투자금액 100% 모집시 fundsituation 변경
			
			ivrDao.updateFundsituation(InvestRequestNum);
			
			//투자완료 메세지 띄워주기
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('투자해 주셔서 감사합니다.');");
			out.print("location.href='index.im';");
			out.print("</script>");
			out.close();
			
			return null;
		//result가 0이면 카드 정보가 일치하지 않습니다 안내문 띄워주기
		}else if(result==0){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('카드 정보가 일치 하지 않습니다.\\n카드정보를 다시 확인하여 주세요.');");
			out.print("location.href='investment.im?investRequestNum="+investRequestNum+"';");
			out.print("</script>");
			out.close();
			return null;
	}
		return null;
		
	
	}
}
