package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.member.db.MemberDAO;
import net.member.db.MemberDTO;

public class MemberInfoViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		String email = (String)session.getAttribute("MemberEmail");
		
		MemberDAO dao = new MemberDAO();
		
		MemberDTO mdto = dao.InfoMember(email);
		
		request.setAttribute("mdto", mdto);
		
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/UpdateViewpage.mf");
		
		return forward;
	}

}
