package net.member.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;
import net.member.db.MemberDTO;

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		System.out.println(request.getParameter("memberEmail"));
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("pass"));
		System.out.println(request.getParameter("nickName"));
		System.out.println(request.getParameter("phone"));
		System.out.println(request.getParameter("birth"));			
		

		MemberDTO mdto = new MemberDTO();
		mdto.setMemberEmail(request.getParameter("memberEmail"));
		mdto.setName(request.getParameter("name"));
		mdto.setPass(request.getParameter("pass"));
		mdto.setNickName(request.getParameter("nickName"));
		mdto.setPhone(request.getParameter("phone"));
		mdto.setBirth(request.getParameter("birth"));
		
		boolean result = false;
		
		MemberDAO mdao = new MemberDAO();
		
		result= mdao.JoinMember(mdto);
		System.out.println(result);
		if(result==false){
			System.out.println("회원가입 실패!!");
			return null;
		}
		ActionForward forward = new ActionForward();
		//페이지 이동방식 여부값 true로 저장
		forward.setRedirect(true);
		//경로값 설정
		forward.setPath("index.mf");
		//리턴
		return forward; 
		
	}

}
