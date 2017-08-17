package net.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.Investment.db.InvestmentDto;
import net.member.db.MemberDAO;

public class MemberMyinvestment implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();//세션 객체 선언
		String email = (String)session.getAttribute("MemberEmail");//MemberEmail을 세션에서 꺼내어서 스트링변수에 담기
		
		MemberDAO dao = new MemberDAO();
		
		ArrayList<InvestmentDto> array = new ArrayList<InvestmentDto>();//ArrayList선언
		array = dao.MemberMyinvestment(email);//email을 매개변수로 투자목록 뿌려주는 메소드 만들어서 ArrayList에 담기
		
		request.setAttribute("array", array);//어튜리부트에 키값과 vales담기
		
		//이동
		ActionForward forward = new ActionForward();
		forward.setPath("main1.jsp?center=member/MemberMyinvestment.jsp");
		forward.setRedirect(false);
		return forward;
		
		
	}

}
