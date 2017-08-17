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
		
		//세션영역을 할당받기
		HttpSession session = request.getSession();
		//세션 꺼내기
		String nickName = (String)session.getAttribute("nickName");
		//dao 객체 생성
		questionDao dao = new questionDao();
					
		ArrayList<questionDto> arry = new ArrayList<questionDto>();
		
		//닉네임을 매개변수로 자기가 쓴 모든글 불러오는 메소드를 arraylist에 담는다.
		arry = dao.AllQuestion(nickName);
		
		//request영역에 어트리뷰트로 arry키값으로 arry객체 저장
		request.setAttribute("arry", arry);
		
		//이동
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("main1.jsp?center=tap_ex2.jsp&centern=question/question.jsp");
		
		return forward;
	}

}
