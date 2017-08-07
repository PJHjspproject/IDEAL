package net.Admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Admin.db.AdminDao;
import net.InformationUse.db.InformationUseDto;

public class AdminGetOneInfo implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int infoNum = Integer.parseInt(request.getParameter("infoNum"));
		
		AdminDao adao = new AdminDao();
		
		InformationUseDto idto = adao.getOneInfo(infoNum);
		
		request.setAttribute("idto", idto);
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("AdminGetOneInfo.jsp");
		
		forward.setRedirect(false);
		
		return forward;
	}

}
