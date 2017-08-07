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
			
		InvestRequestDao irdao = new InvestRequestDao();
		String category = request.getParameter("category");
		String search = request.getParameter("search");
		ArrayList<InvestRequestDto> irlist = null;
		int count = 0;
		//카테고리가 null일경우 카테로리를 ALL로
		if(category == null) category = "All";
		irlist = irdao.ListInvestRequest(category, search);
		count = irdao.getCountInvestRequestList(category, search);
		
		HttpSession session = request.getSession();
		session.setAttribute("irlist", irlist);
		session.setAttribute("category", category);
		session.setAttribute("count", count);
		System.out.println("irslist :"+irlist);
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("location.href='./SearchList.iR'");
		out.println("</script>");
		out.close();
		return forward;
	}
	

}
