package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;

public class Memberdeletequestion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//MemberMyWirteView.jsp페이지에서  num값 받아와서 정수형 변수에 저장
		int num = Integer.parseInt(request.getParameter("num"));
		
		MemberDAO dao = new MemberDAO();
		
	   dao.Memberdeletequestion(num);//1:1문의 글 삭제 메소드
	   
	   //이동
	   ActionForward forward = new ActionForward();
	   forward.setPath("./MemberMyWirteView.mf");
	   forward.setRedirect(false);
	
		return forward;
	}

}
