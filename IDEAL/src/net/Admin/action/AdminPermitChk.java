package net.Admin.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Admin.db.AdminDao;


public class AdminPermitChk implements Action{

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		AdminDao adao = new AdminDao();
		ActionForward forward = new ActionForward();
		
		String IRnum = req.getParameter("investRequestNum");
		System.out.println(req.getParameter("investRequestNum"));

		adao.AdminPermitCheck(IRnum);

		return forward;
	}

}
