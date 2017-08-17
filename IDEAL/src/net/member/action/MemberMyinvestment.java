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
		
		HttpSession session = request.getSession();//���� ��ü ����
		String email = (String)session.getAttribute("MemberEmail");//MemberEmail�� ���ǿ��� ����� ��Ʈ�������� ���
		
		MemberDAO dao = new MemberDAO();
		
		ArrayList<InvestmentDto> array = new ArrayList<InvestmentDto>();//ArrayList����
		array = dao.MemberMyinvestment(email);//email�� �Ű������� ���ڸ�� �ѷ��ִ� �޼ҵ� ���� ArrayList�� ���
		
		request.setAttribute("array", array);//��Ʃ����Ʈ�� Ű���� vales���
		
		//�̵�
		ActionForward forward = new ActionForward();
		forward.setPath("main1.jsp?center=member/MemberMyinvestment.jsp");
		forward.setRedirect(false);
		return forward;
		
		
	}

}
