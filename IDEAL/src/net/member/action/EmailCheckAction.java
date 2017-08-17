package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;

public class EmailCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		MemberDAO dao = new MemberDAO();
		boolean result = dao.CheckMember(id);
		request.setAttribute("id", id);
		request.setAttribute("result", result);
		response.setContentType("text/html;charset=euc-kr");
		
		
        PrintWriter out = response.getWriter();
        ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./EmailMoveAction.mf");
		
		return forward;
	        
	}

}
