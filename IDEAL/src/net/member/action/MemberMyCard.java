package net.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.Card.db.CardDto;
import net.member.db.MemberDAO;

public class MemberMyCard implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();//세션객체 선언
		String email = (String)session.getAttribute("MemberEmail");//세션에서 MemberEmail을 꺼내와서 스트링변수에 담기
		
		MemberDAO dao = new MemberDAO();
		
		ArrayList<CardDto> array = new ArrayList<CardDto>();//ArrayList 객체 선언
		
		array = dao.MemberMyCard(email);//email을 매개변수로 하는 카드잔액 보는 메소드 만들어 ArrayList에 담기
		
		request.setAttribute("array", array);//키값과 value를 어투리뷰트에 담기
		
		//이동
		ActionForward forward = new ActionForward();
		forward.setPath("main1.jsp?center=member/MemberMyCard.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
