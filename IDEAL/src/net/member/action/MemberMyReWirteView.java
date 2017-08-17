package net.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.db.CommentDto;
import net.member.db.MemberDAO;

public class MemberMyReWirteView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();//세션 객체 선언
		String nickName = (String)session.getAttribute("nickName");//nickName을 세션에서 꺼내와서 스트링형 변수에 담기
		
		MemberDAO dao = new MemberDAO();//dao 선언
		
		ArrayList<CommentDto> array = new ArrayList<CommentDto>();//ArrayList객체 선언
		
		array = dao.MemberMyReWirteView(nickName);//nickName을 매개변수로 내가쓴 댓글보는 메소드 만들어 어레이리스트에 담기
		
		request.setAttribute("array", array);//키값과 value를 어튜리 부트에 담기
		
		
		//이동 
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("main1.jsp?center=member/MemberMyReWirteView.jsp");
						
		return forward;
	}

}
