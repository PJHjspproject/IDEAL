package net.question.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.question.db.questionDao;
import net.question.db.questionDto;

public class updateQuestionAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int num = Integer.parseInt(request.getParameter("num"));
		
		questionDao qdao = new questionDao();
		questionDto qdto = qdao.OneQuestion(num);
		
		request.setAttribute("qdto", qdto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./main1.jsp?center=question/questionUpdate.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
