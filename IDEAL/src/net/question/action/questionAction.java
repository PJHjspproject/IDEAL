package net.question.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.question.db.questionDao;
import net.question.db.questionDto;

public class questionAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String nickName = (String)session.getAttribute("nickName");
		
		questionDao dao = new questionDao();
					
		ArrayList<questionDto> arry = new ArrayList<questionDto>();
		
		
		arry = dao.AllQuestion(nickName);
		
		request.setAttribute("arry", arry);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("main1.jsp?center=tap_ex2.jsp&centern=question/question.jsp");
		
		return forward;
	}

}
