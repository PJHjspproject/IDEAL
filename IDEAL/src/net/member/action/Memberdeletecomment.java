package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;

public class Memberdeletecomment implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//MemberMyReWirteView.jsp에서 cNum을 넘겨받아 정수형 변수에 담기
		int cNum = Integer.parseInt(request.getParameter("cNum"));
		
		MemberDAO dao = new MemberDAO();
		
		dao.Memberdeletecomment(cNum);//cNum을 매개변수로 내가 쓴 댓글 삭제 메소드 만들기
	   
		//이동
		ActionForward forward = new ActionForward();
		forward.setPath("./MemberMyReWirteView.mf");
		forward.setRedirect(false);
	
		return forward;
	}

}
