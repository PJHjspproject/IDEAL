package net.Admin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Admin.db.AdminDao;
import net.InformationUse.db.InformationUseDto;

public class AdminInformationUseList implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminDao adao = new AdminDao();
		
		ArrayList<InformationUseDto> ilist = adao.informationUseList();
		
		request.setAttribute("ilist", ilist);
		
		ActionForward forward = new ActionForward();
		System.out.println("FAQ info 액션");
		forward.setPath("AdminInfoList.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
