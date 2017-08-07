package net.Admin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Admin.db.AdminDao;



public class AdminInvestRequestList implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminDao adao = new AdminDao();
		ArrayList InvestRequestList = adao.InvestRequestList();
		//permitChk가 true인 (=1) (관리자가 승인한)
		//프로젝트 정보를 뿌려줌
		ArrayList InvestRequestList2 = adao.InvestRequestList(true);
		
		
		request.setAttribute("InvestRequestList", InvestRequestList);

		request.setAttribute("InvestRequestList2", InvestRequestList2);
		
		ActionForward forward = new ActionForward();
		forward.setPath("AdminInvestRequest.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
