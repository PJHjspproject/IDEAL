package net.Admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Admin.db.AdminDao;
import net.InformationUse.db.InformationUseDto;

public class AdminInsertInfo implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		InformationUseDto iudto = new InformationUseDto();
		
		iudto.setInfoContent(request.getParameter("content"));
		iudto.setInfoImage(request.getParameter("image"));
		iudto.setInfoSubject(request.getParameter("subject"));
		iudto.setInfoTitle(request.getParameter("title"));
		
		AdminDao adao = new AdminDao();
		
		adao.InsertInformation(iudto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("AdminInformationUse.ad");
		forward.setRedirect(true);
		return forward;
	}

}
