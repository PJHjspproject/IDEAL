package net.Admin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Admin.db.AdminDao;
import net.member.db.MemberDTO;


public class AdminMemberAbout implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		AdminDao adao = new AdminDao();
		MemberDTO mdto = adao.getOneMember(request.getParameter("memberEmail"));
		ArrayList blist = adao.BoardList();
		ArrayList qlist = adao.QuestionList();
		ArrayList irlist = adao.InvestRequestList();
		ArrayList imlist = adao.InvestMentList(request.getParameter("memberEmail"));
		ArrayList rlist = adao.commentListName(request.getParameter("nickName"));
		request.setAttribute("mdto", mdto);
		request.setAttribute("blist", blist);
		request.setAttribute("qlist", qlist);
		request.setAttribute("irlist", irlist);
		request.setAttribute("imlist", imlist);
		request.setAttribute("rlist", rlist);
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("AdminMemberAbout.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
