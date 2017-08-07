package net.Investment.action;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.Card.db.CardDao;
import net.Card.db.CardDto;
import net.investRequest.db.InvestRequestDao;
import net.investRequest.db.InvestRequestDto;
import net.member.db.MemberDAO;
import net.member.db.MemberDTO;


public class goInvestmentAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		
		MemberDAO meDao = new MemberDAO();
		MemberDTO meDto = new MemberDTO();
		InvestRequestDto ivrDto = new InvestRequestDto();
		InvestRequestDao ivrDao = new InvestRequestDao();

		HttpSession session = request.getSession();
		String memberEmail= (String)session.getAttribute("memberEmail");
				

			int InvestRequestNum = Integer.parseInt(request.getParameter("investRequestNum"));				

				meDto = meDao.getMember(memberEmail);

				ivrDto = ivrDao.getmoneys(InvestRequestNum);	//successMoney, nowMoney

				request.setAttribute("meDto", meDto);
				request.setAttribute("ivrDto", ivrDto);

//				session.removeAttribute("InvestRequestNum");
				
				ActionForward forward = new ActionForward();
				forward.setPath("main1.jsp?center=investment/investment2.jsp");
				forward.setRedirect(false);
				return forward;


			
	}
}

	
	

