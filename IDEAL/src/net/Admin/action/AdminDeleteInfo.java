package net.Admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Admin.db.AdminDao;

public class AdminDeleteInfo implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int infoNum = Integer.parseInt(request.getParameter("infoNum"));
		
		AdminDao adao = new AdminDao();
		
		adao.deleteInfo(infoNum);
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("AdminInformationUse.ad");
		forward.setRedirect(true);
		return forward;
	}

}
