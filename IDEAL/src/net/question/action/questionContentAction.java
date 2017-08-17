package net.question.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.question.db.questionDao;
import net.question.db.questionDto;

public class questionContentAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//글번호 가져오기
		int num = Integer.parseInt(request.getParameter("num"));
		
		questionDao dao = new questionDao();
		
		questionDto qdto = dao.OneQuestion(num);
		
		request.setAttribute("qdto", qdto);
		System.out.println(qdto.getImage());
		//이동
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("./main1.jsp?center=question/questionContent.jsp");
		
		return forward;
	}

}
