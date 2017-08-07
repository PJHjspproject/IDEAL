package net.Admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Admin.db.AdminDao;
import net.question.db.questionDto;

public class AdminGetOneQuestion implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminDao adao = new AdminDao();
		int num = Integer.parseInt(request.getParameter("num"));
		questionDto qdto = adao.getOneQuestion(num);
		request.setAttribute("qdto", qdto);
		ActionForward forward = new ActionForward();
		forward.setPath("AdminGetOneQuestion.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
