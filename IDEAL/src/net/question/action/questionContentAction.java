package net.question.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.question.db.questionDao;
import net.question.db.questionDto;

public class questionContentAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		questionDao dao = new questionDao();
		
		questionDto qdto = dao.OneQuestion(num);
		
		request.setAttribute("qdto", qdto);
		System.out.println(qdto.getImage());
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("./main1.jsp?center=question/questionContent.jsp");
		
		return forward;
	}

}
