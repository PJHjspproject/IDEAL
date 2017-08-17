package net.member.action;

import java.io.PrintWriter;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberPassCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session=request.getSession();
		String email = (String)session.getAttribute("MemberEmail");
		String pass = request.getParameter("pass");
		Boolean result=false;
		System.out.println(email);
		System.out.println(pass);
		
		MemberDAO mdao = new MemberDAO();
		
		result = mdao.UserCheck(email, pass);
		
		if(result==false){
			ActionForward forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./Usercheckview.mf"); 
			return forward;
		}else{
	
			//login.jsp 화면에서 입력한 아이디를 세션객체영역에 저장
			session.setAttribute("MemberEmail", email);
			ActionForward forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./Memberinfo.mf"); 
			return forward;
			
		}
		
	
	}

}
