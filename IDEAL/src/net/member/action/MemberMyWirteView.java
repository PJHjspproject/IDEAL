package net.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.db.BoardDto;
import net.member.db.MemberDAO;
import net.question.db.questionDto;

public class MemberMyWirteView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();//������ü ����
		String nickName = (String)session.getAttribute("nickName");//nickName�� ���ǿ��� �����ͼ� ��Ʈ�������� ���
		
		MemberDAO dao = new MemberDAO();
		
		ArrayList<BoardDto> arrayb = new ArrayList<BoardDto>();//ArrayList����
		
		arrayb = dao.MemberMyWirteBoardView(nickName);//nickName�� �Ű������� ģ��Խ����� ��� �ѷ��ִ� �޼ҵ� ����� ��̸���Ʈ�� ���
		
		request.setAttribute("arrayb", arrayb);//Ű���� value ��Ʃ����Ʈ�� ����
		
		ArrayList<questionDto> arrayq = new ArrayList<questionDto>();//ArrayList����
		
		arrayq = dao.MemberMyWirteQuestionView(nickName);//nickName�� �Ű������� 1:1���� ��� �ѷ��ִ� �޼ҵ� ����� ��̸���Ʈ�� ���
		
		request.setAttribute("arrayq", arrayq);//Ű���� value ��Ʃ����Ʈ�� ����
		
		//�̵� 
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("main1.jsp?center=member/MemberMyWirteView.jsp");
				
		return forward;
	}

}
