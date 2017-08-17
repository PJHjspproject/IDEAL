package net.Admin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Admin.db.AdminDao;
import net.question.db.questionDto;

public class AdminQuestionList implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminDao adao = new AdminDao();
		String nickName = request.getParameter("nickName");
		
		if(nickName == null){
		ArrayList<questionDto> questionList = adao.QuestionList();
		ArrayList<questionDto> cquestionList = adao.CompleteQuestionList();
		request.setAttribute("questionList", questionList);
		}else{
			ArrayList<questionDto> questionList = adao.QuestionList(nickName);
			ArrayList<questionDto> cquestionList = adao.CompleteQuestionList(nickName);
			request.setAttribute("questionList", questionList);
			request.setAttribute("cquestionList", cquestionList);
		}
		ActionForward forward = new ActionForward();
		forward.setPath("AdminQuestionList.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
