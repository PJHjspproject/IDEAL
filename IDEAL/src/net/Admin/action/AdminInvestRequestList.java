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
		//permitChk占쏙옙 true占쏙옙 (占쏙옙청占심삼옙 占싼뱄옙 占쏙옙 占쌍듸옙)
		//占쏙옙占쏙옙 占쌓쇽옙 占쏙옙占쏙옙占쏙옙 占쏙옙 占쏙옙占�
		ArrayList InvestRequestList2 = adao.InvestRequestList(true);
		
		
		request.setAttribute("InvestRequestList", InvestRequestList);
		//AdminInvestRequest.jsp占쏙옙 占싼뤄옙占쌍깍옙 占쏙옙占쏙옙 Attribute占쏙옙 占쏙옙占쏙옙
		request.setAttribute("InvestRequestList2", InvestRequestList2);
		
		ActionForward forward = new ActionForward();
		forward.setPath("AdminInvestRequest.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
