package net.investRequest.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.investRequest.db.InvestRequestDao;
import net.investRequest.db.InvestRequestDto;

public class goViewInvestRequestAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String memberEmail = (String)session.getAttribute("memberEmail");
		String nickName = (String)session.getAttribute("nickName");
		int investRequestNum = Integer.parseInt(request.getParameter("investRequestNum"));
//		System.out.println("memberEmail:"+memberEmail);
//		System.out.println("nickName:"+nickName);
//		System.out.println("investRequestNum:"+investRequestNum);
		
		InvestRequestDao irdao = new InvestRequestDao();
		InvestRequestDto irdto = irdao.getInvestRequest(investRequestNum);
		request.setAttribute("irdto", irdto);
		
		System.out.println("-------goViewInvestRequestAction()에서 irdto값 확인-------");
//		//1페이지
//		System.out.println("memberEmail:"+irdto.getMemberEmail());
//		//irdto.investRequestNum은 auto_increment
//		System.out.println("program:"+irdto.getProgram());
//		System.out.println("dName:"+irdto.getDName());
//		System.out.println("introduce:"+irdto.getIntroduce());
//		System.out.println("category:"+irdto.getCategory());
//		System.out.println("hashtag:"+irdto.getHashtag());
//		System.out.println("startDay:"+irdto.getStartDay());
//		System.out.println("endDay:"+irdto.getEndDay());
//		System.out.println("payDay:"+irdto.getPayDay());
//		System.out.println("successMoney:"+irdto.getSuccessMoney());
//		System.out.println("thumbimage:"+irdto.getThumbImage());
//		//2페이지
//		System.out.println("mainThumb:"+irdto.getMainThumb());
//		System.out.println("mainImage:"+irdto.getMainImage());
//		System.out.println("mainText:"+irdto.getMainText());
//		//3페이지
//		System.out.println("file"+irdto.getFile());
//		//default or DB
//		System.out.println(irdto.getFundsituation());//기본값 1
//		System.out.println(irdto.getPermitChk());
//		System.out.println(irdto.getUpdateChk());
//		System.out.println("----------------------------");		
				
		
		ActionForward forward = new ActionForward();
		forward.setPath("main1.jsp?center=investRequest/viewInvestRequest.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
