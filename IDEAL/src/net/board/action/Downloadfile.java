package net.board.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Downloadfile implements Action {

	private String getBrowser(HttpServletRequest request) {
	    String header =request.getHeader("User-Agent");
	    if (header.contains("MSIE")) {
	           return "MSIE";
	    } else if(header.contains("Chrome")) {
	           return "Chrome";
	    } else if(header.contains("Opera")) {
	           return "Opera";
	    }
	    return "Firefox";
	}
	
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		HttpSession session = request.getSession();
		String path = request.getParameter("path");
		String name = request.getParameter("name");
		String nickName = (String)session.getAttribute("nickName");
		String realPath = request.getServletContext().getRealPath("image\\"+nickName);
		String header = getBrowser(request);
		
		response.setContentType("Application/x-msdownload");
		
		File file = new File(realPath + "/"+name);
		
		System.out.println(file);
		
		if(header.contains("MSIE")){
			String docName = URLEncoder.encode(name,"UTF-8").replaceAll("\\", "%20");
			response.setHeader("Content-Disposition", "attachment; filename=" + docName + ";");
			
		}else{
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(name,"UTF-8") + ";");
		}
		byte[] data = new byte[1024];
		
		
		if (file.isFile()) {
			
			try {
				/*������ �ִ� �ٿ�ε��� ������ ������ �о� ���̱� ���� ��� �غ� */
				// new FileInputStream(file) : 
				//�ٿ�ε��� ���� ���ϰ�ü �� ����Ű�� ������ 1����Ʈ�� �о� ���̱� ���� ��Ʈ�� ��� �����
				// new BufferedInputStream(new FileInputStream(file)) : 
				//���ϰ�ü�� ����Ű�� ���� ������ ��θ� 1����Ʈ�� �о� �鿩��..
				//���� ���ι��� ������ ��Ƶξ��ٰ�....
				//�Ѳ����� ������ü�� �о� ���̱� ���� ��� ����� 
				BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));

				/*����ڿ��� ������ ���, ��������(�ٿ�ε�)�ϱ����� ��� �غ�*/
				BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream());

				int read;

				while ((read = input.read(data)) != -1) {//read()�޼ҵ��� ��ȯ���� �о���̱� ������ ����Ʈ���� ��ȯ
					//��ü data�迭�� 0���� 1024�� ����Ʈ�� ��� ��� ���ۿ� ��������.
					output.write(data, 0, read);
				}
				//��¹��� ������ ���� ���� �ʾƵ� ���ϳ����� ���������� ������� ȭ�鿡 �ѷ��ִ� ����� �����Ѵ�.
				output.flush(); // ������ ��¹��ۿ��� ������ ���� 
				output.close(); //�ڿ� �ݱ�
				input.close(); //�ڿ� �ݱ�

			} catch (Exception err) {
				err.printStackTrace();
			}
	}//if end
		
		ActionForward forward =null;
		
		return forward;

		

	}
}
