package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;

public class Memberdeletequestion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//MemberMyWirteView.jsp����������  num�� �޾ƿͼ� ������ ������ ����
		int num = Integer.parseInt(request.getParameter("num"));
		
		MemberDAO dao = new MemberDAO();
		
	   dao.Memberdeletequestion(num);//1:1���� �� ���� �޼ҵ�
	   
	   //�̵�
	   ActionForward forward = new ActionForward();
	   forward.setPath("./MemberMyWirteView.mf");
	   forward.setRedirect(false);
	
		return forward;
	}

}
