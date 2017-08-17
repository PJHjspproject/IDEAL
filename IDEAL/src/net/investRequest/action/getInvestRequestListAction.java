package net.investRequest.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale.Category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.investRequest.db.InvestRequestDao;
import net.investRequest.db.InvestRequestDto;

public class getInvestRequestListAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
//		System.out.println("getInvestRequestListAction()메소드 확인");
		InvestRequestDao irdao = new InvestRequestDao();
		//검색창에서 또는 투자하기 버튼을 클릭했을때 넘겨받은 category값과 search값
		String category = request.getParameter("category");
		String search = request.getParameter("search");
		//투자 가능한 캠페인 목록을 담기위한 ArrayList객체
		ArrayList<InvestRequestDto> irlist = null;
		//product.jsp페이지 페이징을 위한 count값 받기위한 변수 선언
		int count = 0;
		if(category == null) category = "All";
		irlist = irdao.ListInvestRequest(category, search);
		count = irdao.getCountInvestRequestList(category, search);
		
		HttpSession session = request.getSession();
		session.setAttribute("irlist", irlist);
		session.setAttribute("category", category);
		session.setAttribute("count", count);
//		System.out.println("irslist 세션 세팅 :"+irlist);
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("location.href='./SearchList.iR'");
		out.println("</script>");
		out.close();
		return forward;
	}
	

}
