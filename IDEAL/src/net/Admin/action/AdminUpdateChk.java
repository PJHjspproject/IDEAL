package net.Admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Admin.db.AdminDao;

public class AdminUpdateChk implements Action {
	
//관리자가 수정 허락한 투자 요청된 항목
	//AdminPermitChk 이랑 AdminUpdateChk 랑 
	//합치려구 했는데 구분이 애매하여 따로 만듬
	//추후에 구분할수 있으면 한페이지로 통합 예정
	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		AdminDao adao = new AdminDao();
		ActionForward forward = new ActionForward();
		
		String IRnum = req.getParameter("investRequestNum");
		System.out.println(req.getParameter("investRequestNum"));
		
		adao.AdminUpdateCheck(IRnum);
		//AdminDao의 AdminUpdateCheck메소드 실행하여 
		//updateChk값 바꿈
		return forward;
	}

}
