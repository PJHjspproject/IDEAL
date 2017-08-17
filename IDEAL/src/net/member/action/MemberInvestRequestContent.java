package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MemberInvestRequestContent implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//investRequestNum�� �Ķ���Ϳ��� �Ѱܹ޾� parseInt�� ��Ʈ������ ���������� �ٲ㼭 ������������ ���
		int investRequestNum = Integer.parseInt(request.getParameter("investRequestNum"));
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		//ķ���� �󼼺���� investRequestNum�� ���� �Ѱܼ� �̵���Ű��
		forward.setPath("/viewInvestRequestAction.iR?investRequestNum="+investRequestNum);
		System.out.println(investRequestNum);
		return forward;
	}

}
