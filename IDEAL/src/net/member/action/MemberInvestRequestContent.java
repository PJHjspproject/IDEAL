package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MemberInvestRequestContent implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//investRequestNum을 파라미터에서 넘겨받아 parseInt로 스트링형을 정수형으로 바꿔서 정수형변수에 담기
		int investRequestNum = Integer.parseInt(request.getParameter("investRequestNum"));
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		//캠페인 상세보기로 investRequestNum을 같이 넘겨서 이동시키기
		forward.setPath("/viewInvestRequestAction.iR?investRequestNum="+investRequestNum);
		System.out.println(investRequestNum);
		return forward;
	}

}
