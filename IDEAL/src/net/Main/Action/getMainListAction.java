package net.Main.Action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.investRequest.db.InvestRequestDao;
import net.investRequest.db.InvestRequestDto;

public class getMainListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		/*
			메인페이지 컨트롤러에서 goMain.ma 요청을 받아 실행되는 Action이다.
			여기서는 메인페이지 즉, center1.jsp에서 뿌려줘야할 모든 데이터를 가져와야한다.
			그리고 앞서 이야기 했던 것 처럼 사이트 접속시에 모든 fundSituation값이 1인 것들을 읽어와서
			시스템의 오늘 날짜와 비교해서 날짜가 같거나 지난것들은 모두 fundSituation값을 2(투자금 모금 실패), 4(투자금 모금 80%이상 달성, 성공)
			으로 바꿔준다.
		*/
		
		//세션영역을 사용하기위해 세션을 받아온다
		HttpSession session = request.getSession();
		
		
		//캠페인 관련 메소드를 사용하기위해서 InvestRequestDao객체 생성
		InvestRequestDao irdao = new InvestRequestDao();
		
		//캠페인 내용을 뿌려주기위한 데이터를 가져오기전에 날짜체크를 실행한다
		irdao.checkInvestRequest();

		//캠페인 내용을 뿌려주기위해 목록을 가져온다
		ArrayList<InvestRequestDto> irlist = irdao.ListInvestRequest();
		
		//로그인후 페이지에 뿌려질 값들을 담을 리스트 생성
		ArrayList<InvestRequestDto> lirlist = new ArrayList<InvestRequestDto>();
		lirlist.add(irdao.getImminentChampain());
		lirlist.add(irdao.getHighestNowMoneyChampain());
		
		//등록 예정 캠페인 리스트
		ArrayList<InvestRequestDto> eirlist = irdao.expectedIr();
		
		//session영역에 값을 올린다
		session.setAttribute("irlist", irlist);
		session.setAttribute("lirlist", lirlist);
		session.setAttribute("eirlist", eirlist);
		
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("location.href='./Main.ma'");
		out.println("</script>");
		out.close();
		return forward;
	}

}
