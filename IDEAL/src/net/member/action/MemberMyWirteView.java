package net.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.db.BoardDto;
import net.member.db.MemberDAO;
import net.question.db.questionDto;

public class MemberMyWirteView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();//세선객체 선언
		String nickName = (String)session.getAttribute("nickName");//nickName을 세션에서 꺼내와서 스트링변수에 담기
		
		MemberDAO dao = new MemberDAO();
		
		ArrayList<BoardDto> arrayb = new ArrayList<BoardDto>();//ArrayList선언
		
		arrayb = dao.MemberMyWirteBoardView(nickName);//nickName을 매개변수로 친목게시판의 목록 뿌려주는 메소드 만들어 어레이리스트에 담기
		
		request.setAttribute("arrayb", arrayb);//키값과 value 어튜리부트에 저장
		
		ArrayList<questionDto> arrayq = new ArrayList<questionDto>();//ArrayList선언
		
		arrayq = dao.MemberMyWirteQuestionView(nickName);//nickName을 매개변수로 1:1문의 목록 뿌려주는 메소드 만들어 어레이리스트에 담기
		
		request.setAttribute("arrayq", arrayq);//키값과 value 어튜리부트에 저장
		
		//이동 
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("main1.jsp?center=member/MemberMyWirteView.jsp");
				
		return forward;
	}

}
