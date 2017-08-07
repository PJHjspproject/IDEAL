package net.investRequest.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.investRequest.db.InvestRequestDao;
import net.investRequest.db.InvestRequestDto;
/**/
public class goViewInvestRequestAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String memberEmail = (String)session.getAttribute("memberEmail");
		String nickName = (String)session.getAttribute("nickName");
		int investRequestNum = Integer.parseInt(request.getParameter("investRequestNum"));
		System.out.println("memberEmail:"+memberEmail);
		System.out.println("nickName:"+nickName);
		System.out.println("investRequestNum:"+investRequestNum);
		
		InvestRequestDao irdao = new InvestRequestDao();
		InvestRequestDto irdto = irdao.getInvestRequest(investRequestNum);
		request.setAttribute("irdto", irdto);
		
		System.out.println("-------goViewInvestRequestAction()-------");

		System.out.println("mainThumb:"+irdto.getMainThumb());
		System.out.println("mainImage:"+irdto.getMainImage());
		System.out.println("mainText:"+irdto.getMainText());

		ActionForward forward = new ActionForward();
		forward.setPath("main1.jsp?center=investRequest/viewInvestRequest.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
