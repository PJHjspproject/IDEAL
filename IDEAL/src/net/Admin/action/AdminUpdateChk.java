package net.Admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Admin.db.AdminDao;

public class AdminUpdateChk implements Action {
	
//�����ڰ� ���� ����� ���� ��û�� �׸�
	//AdminPermitChk �̶� AdminUpdateChk �� 
	//��ġ���� �ߴµ� ������ �ָ��Ͽ� ���� ����
	//���Ŀ� �����Ҽ� ������ ���������� ���� ����
	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		AdminDao adao = new AdminDao();
		ActionForward forward = new ActionForward();
		
		String IRnum = req.getParameter("investRequestNum");
		System.out.println(req.getParameter("investRequestNum"));
		
		adao.AdminUpdateCheck(IRnum);
		//AdminDao�� AdminUpdateCheck�޼ҵ� �����Ͽ� 
		//updateChk�� �ٲ�
		return forward;
	}

}
