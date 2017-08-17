package net.member.action;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.investRequest.db.InvestRequestDto;
import net.member.db.MemberDAO;

public class MemberOutAction implements Action {

	//Memberinfo.jsp���� ȸ��Ż�� ������ ��Ʈ�ѷ��� ���ļ� �� �������� �´�.
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session=request.getSession();//���� ���� ��������
		String email = (String)session.getAttribute("MemberEmail");//MemberEmail�� ���ǿ��� �����ͼ� ��Ʈ�� ������ ����
		
		MemberDAO dao = new MemberDAO();
		
		int result = 0; //���ڿ�û ���¿� ���� �޼ҵ带 �޸� �ϱ� ������ �װ� ������ ��Ʈ�� ���� ����
		
		//email�� �Ű������� �ڽ��� ���ڿ�û�� ����Ʈ �˻��ؼ� ���ο������� ������ �� üũ�ϴ� �޼ҵ带 ���� fundsituation�� ���ϱ� ����
		//���� �����ؼ� ��Ʈ�� ������ ����ش�.
		result = dao.checkInvestReqDate(email); 
		
		System.out.println(result);
		
		//���� ���� ���� ������ �޼ҵ带 �޸��� �ش�.
		
		if(result == -1){//�������� ������Ʈ�� ������, �Ⱓ�ϷἺ��(fundsituation = 4) �Ǵ�
		    			//���⼺��(fundsituation = 3) �Ǿ��� ��� �ش� ķ������ ���ο�����(payDay)�� �����ٸ�
			
			int resultRe = dao.checkInvestMent(email);//�����ϱ� �ݵ���߿��̼� üũ �޼ҵ�
			
			if(resultRe == -1){//���̵��̰� ���� ��� ���� �޼ҵ� ����
				System.out.println(resultRe);
				
				dao.MemberoutAction(email);//ȸ��Ż�� �޼ҵ�
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('����Ʈ�� �̿����ּż� �����մϴ�.');");
				out.print("location.href='./MemberLogoutAction.mf'");
				out.println("</script>");
				out.close();
				return null;
				
				
			}else if (resultRe == 2) {//���̵��̰� ������ �������
				System.out.println(resultRe);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('���γ�¥�� ������ �ʾҾ��. �ٽ� Ȯ���� �ּ���');");
				out.print("location.href='./Memberinfo.mf'");
				out.println("</script>");
				out.close();
				return null;
			}else if (resultRe == 1) {//���ڰ� ������ ���
				System.out.println(resultRe);
				dao.MemberoutAction(email);//ȸ��Ż�� �޼ҵ�
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('����Ʈ�� �̿����ּż� �����մϴ�.');");
				out.print("location.href='./MemberLogoutAction.mf'");
				out.println("</script>");
				out.close();
				return null;
			}else if (resultRe == 0) {//������Ʈ�� �������̶��..
				System.out.println(resultRe);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('������Ʈ�� ������ �Դϴ�. �ٽ� Ȯ���� �ּ���');");
				out.print("location.href='./Memberinfo.mf'");
				out.println("</script>");
				out.close();
				return null;
			}
		    
			
		}else if (result == 1) {//���ڿ�û�� �������� ��� ������ ����Ʈ���� ���̵��� �˻��Ѵ�.
			
			int resultRe = dao.checkInvestMent(email);//�����ϱ� �ݵ���߿��̼� üũ �޼ҵ�
			System.out.println(resultRe);
			if(resultRe == -1){//���̵��̰� ���� ��� ���� �޼ҵ� ����
				System.out.println(resultRe);
				dao.MemberoutAction(email);//ȸ��Ż�� �޼ҵ�
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('����Ʈ�� �̿����ּż� �����մϴ�.');");
				out.print("location.href='./MemberLogoutAction.mf'");
				out.println("</script>");
				out.close();
				return null;
			}else if (resultRe == 2) {//���̵��̰� ������ �������
				System.out.println(resultRe);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('���γ�¥�� ������ �ʾҾ��. �ٽ� Ȯ���� �ּ���');");
				out.print("location.href='./Memberinfo.mf'");
				out.println("</script>");
				out.close();
				return null;
				
			}else if (resultRe == 1) {//���ڰ� ������ ���
				System.out.println(resultRe);
				dao.MemberoutAction(email);//ȸ��Ż�� �޼ҵ�
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('����Ʈ�� �̿����ּż� �����մϴ�.');");
				out.print("location.href='./MemberLogoutAction.mf'");
				out.println("</script>");
				out.close();
				return null;
			}else if (resultRe == 0) {//������Ʈ�� �������̶��..
				System.out.println(resultRe);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('������Ʈ�� ������ �Դϴ�. �ٽ� Ȯ���� �ּ���');");
				out.print("location.href='./Memberinfo.mf'");
				out.println("</script>");
				out.close();
				return null;
			}
		    
			
		}else if (result == 0) {//���ڿ�û�� ������Ʈ�� �������̶��
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('������Ʈ�� ������ �Դϴ�. �ٽ� Ȯ���� �ּ���');");
			out.print("location.href='./Memberinfo.mf'");
			out.println("</script>");
			out.close();
			return null;
			
		}else if (result == 2) {//������Ʈ�� �������� ���γ�¥�� ������ �ʾҴٸ�?
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('���γ�¥�� ������ �ʾҾ��. �ٽ� Ȯ���� �ּ���');");
			out.print("location.href='./Memberinfo.mf'");
			out.println("</script>");
			out.close();
			return null;
		}
		
		
		
		
		
		
		return null;
		
		
		
		
			
	}

}
