package net.Admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Admin.db.AdminDao;
import net.InformationUse.db.InformationUseDto;

public class AdminUpdateInfo implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		InformationUseDto idto = new InformationUseDto();
		
		idto.setInfoContent(request.getParameter("content"));
		idto.setInfoImage(request.getParameter("image"));
		idto.setInfoNum(Integer.parseInt(request.getParameter("num")));
		idto.setInfoSubject(request.getParameter("subject"));
		idto.setInfoTitle(request.getParameter("title"));
		
		AdminDao adao = new AdminDao();
		
		adao.UpdateInfo(idto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("AdminGetOneInfo.ad?infoNum="+request.getParameter("num"));
		forward.setRedirect(false);
		return forward;
	}

}
