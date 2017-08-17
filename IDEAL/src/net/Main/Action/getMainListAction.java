package net.Main.Action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.investRequest.db.InvestRequestDao;
import net.investRequest.db.InvestRequestDto;

public class getMainListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		/*
			���������� ��Ʈ�ѷ����� goMain.ma ��û�� �޾� ����Ǵ� Action�̴�.
			���⼭�� ���������� ��, center1.jsp���� �ѷ������ ��� �����͸� �����;��Ѵ�.
			�׸��� �ռ� �̾߱� �ߴ� �� ó�� ����Ʈ ���ӽÿ� ��� fundSituation���� 1�� �͵��� �о�ͼ�
			�ý����� ���� ��¥�� ���ؼ� ��¥�� ���ų� �����͵��� ��� fundSituation���� 2(���ڱ� ��� ����), 4(���ڱ� ��� 80%�̻� �޼�, ����)
			���� �ٲ��ش�.
		*/
		
		//���ǿ����� ����ϱ����� ������ �޾ƿ´�
		HttpSession session = request.getSession();
		
		
		//ķ���� ���� �޼ҵ带 ����ϱ����ؼ� InvestRequestDao��ü ����
		InvestRequestDao irdao = new InvestRequestDao();
		
		//ķ���� ������ �ѷ��ֱ����� �����͸� ������������ ��¥üũ�� �����Ѵ�
		irdao.checkInvestRequest();

		//ķ���� ������ �ѷ��ֱ����� ����� �����´�
		ArrayList<InvestRequestDto> irlist = irdao.ListInvestRequest();
		
		//�α����� �������� �ѷ��� ������ ���� ����Ʈ ����
		ArrayList<InvestRequestDto> lirlist = new ArrayList<InvestRequestDto>();
		lirlist.add(irdao.getImminentChampain());
		lirlist.add(irdao.getHighestNowMoneyChampain());
		
		//��� ���� ķ���� ����Ʈ
		ArrayList<InvestRequestDto> eirlist = irdao.expectedIr();
		
		//session������ ���� �ø���
		session.setAttribute("irlist", irlist);
		session.setAttribute("lirlist", lirlist);
		session.setAttribute("eirlist", eirlist);
		
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("location.href='./Main.ma'");
		out.println("</script>");
		out.close();
		return forward;
	}

}
