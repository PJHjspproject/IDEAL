package net.member.action;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;
import net.member.db.MemberDTO;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("memberEmail");
		String pass = request.getParameter("pass");
		String phone = request.getParameter("phone");
		String birth = request.getParameter("birth");
		String name = request.getParameter("name");
		String nick = request.getParameter("nickName");
		MemberDTO dto = new MemberDTO();
		dto.setPass(pass);
		dto.setPhone(phone);
		dto.setMemberEmail(email);
		dto.setName(name);
		dto.setBirth(birth);
		dto.setNickName(nick);
		
		MemberDAO dao = new MemberDAO();
		
		int result = dao.UpdateMember(dto);
		if(result==1){
			
			System.out.println("수정성공");
			ActionForward forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/index.mf");
			return forward;

		}else{
			System.out.println("수정실패");
			ActionForward forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/UpdateViewpage.mf");
			return forward;

		}
		
		
	}

}
