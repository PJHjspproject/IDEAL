package net.Admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Admin.db.AdminDao;

public class AdminUpdateQuestion implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		System.out.println("답변");
		String retext = request.getParameter("retext");
		int num = Integer.parseInt(request.getParameter("num"));
		
		AdminDao adao = new AdminDao();
		
		adao.UpdateQuestion(num, retext);
		
		ActionForward forward = new ActionForward();
		request.setAttribute("num", num);
		forward.setPath("AdminGetOneQuestion.ad");
		forward.setRedirect(false);
		
		return forward;
	}

}
