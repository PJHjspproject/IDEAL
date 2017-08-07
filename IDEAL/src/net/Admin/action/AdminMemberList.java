package net.Admin.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Admin.db.AdminDao;

public class AdminMemberList implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminDao adao = new AdminDao();
		
		ArrayList MemberList = adao.MemberList();
		
		request.setAttribute("MemberList", MemberList);
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("AdminMemberList.jsp");
		
		forward.setRedirect(false);
		
		return forward;
		
	}

}
