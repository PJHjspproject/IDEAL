package net.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.investRequest.db.InvestRequestDto;
import net.member.db.MemberDAO;



public class MemberMyinvestRequest implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();//세션 객체 선언
		String email = (String)session.getAttribute("MemberEmail");//MemberEmail을 세션에서 꺼내와서 스트링변수에 담기
		
		MemberDAO dao = new MemberDAO();
		
		ArrayList<InvestRequestDto> array = new ArrayList<InvestRequestDto>();//ArrayList객체 선언(제네럴에 InvestRequestDto만 담기)
		
		array = dao.MemberMyinvestRequest(email);//email을 매개변수로 MemberDAO에 캠페인 목록 뿌려주는 메소드 만들어서 ArrayList에 담기
		
		request.setAttribute("array", array);//어튜리부트에 키값과 values 담기
		
		//이동 
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("main1.jsp?center=member/MemberMyinvestRequest.jsp");
		
		return forward;
	}

}
