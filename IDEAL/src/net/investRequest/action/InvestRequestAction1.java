package net.investRequest.action;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.investRequest.db.InvestRequestDto;

public class InvestRequestAction1 implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("InvestRequestAction1 ����");
		System.out.println("request getContentType : " + request.getContentType());
		
		//�ѱ� ó��
		request.setCharacterEncoding("utf-8");
		
		
		
		//session ������ ����ϱ� ���ؼ� session���� �޾ƿ���
		HttpSession session = request.getSession();
		String memberEmail = (String)session.getAttribute("memberEmail");
		
		//���ε��� ������ ���//�����
		String realFolder = request.getServletContext().getRealPath("image\\"+memberEmail);
		
//		File folder = new File("D:\\jsp\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\test2\\image"+"\\"+memberEmail);//������ ������Ʈ ������ ����� �г������� ���� ����
//		File folder = new File("D:\\workspace_jquerymobile\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\test2\\image\\"+memberEmail);
		File folder = new File(realFolder);
		if(!folder.exists()){//�ش� ������ �ִ��� üũ
			folder.mkdirs();//������ ���ٸ� ���� ����
			System.out.println(memberEmail+"������ ������ �����մϴ�.");
		}
		
		
		//���ε� ������ �ִ� ũ�� -> ��Ͽ� ����Ǵ� ����� �̹��� ũ�� ���� 1mb
		int max = 10*1024*1024;//10MB
		
		InvestRequestDto irdto = new InvestRequestDto();
		MultipartRequest multi = new MultipartRequest(request, realFolder, max, "UTF-8", new DefaultFileRenamePolicy());
		
		String file = "";//�ӽ÷� ������ ������ �̹��� �̸�(����)
		Enumeration files = multi.getFileNames();
		String thumbimage = "";//DB�� ����� ������ �̹��� �̸�
		if(files.hasMoreElements()){
			file = (String) files.nextElement();
			String src = realFolder+"\\"+multi.getFilesystemName(file);
			thumbimage += multi.getFilesystemName(file);
			
			
			System.out.println("--------iRA1<������ ���� üũ>--------");
			System.out.println("src:"+src);
			System.out.println("filename:"+multi.getFilesystemName(file));
			System.out.println("---------------------------------");
		}
		String program = multi.getParameter("program");
		String dName = multi.getParameter("dName");
		String introduce = multi.getParameter("introduce");
		String category = multi.getParameter("category");
		String hashtag = multi.getParameter("hashtag");
		String startDay = multi.getParameter("startDay");
		String endDay = multi.getParameter("endDay");
		String payDay = multi.getParameter("payDay");
		String sMoney = multi.getParameter("successMoney");
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		//investRequestDto�� �տ��� �ʿ��� �� ����ֱ�
		irdto.setMemberEmail(memberEmail);
		irdto.setThumbImage(thumbimage);
		irdto.setProgram(program);
		irdto.setDName(dName);
		irdto.setIntroduce(introduce);
		irdto.setCategory(category);
		irdto.setHashTag(hashtag);
		date = fmt.parse(startDay);//
		irdto.setStartDay(date);
//		irdto.setStartDay(startDay);
		date = fmt.parse(endDay);//
		irdto.setEndDay(date);
//		irdto.setEndDay(endDay);
		date = fmt.parse(payDay);//
		irdto.setPayDay(date);
//		irdto.setPayDay(payDay);
		int successMoney = Integer.parseInt(sMoney.replaceAll(",", ""));	
		irdto.setSuccessMoney(successMoney);
//		System.out.println("sMoney:"+sMoney+" : successMoney:"+successMoney);
		
//		System.out.println(irdto.getThumbimage());
//		System.out.println(irdto.getProgram());
//		System.out.println(irdto.getDName());
//		System.out.println(irdto.getIntroduce());
//		System.out.println(irdto.getCategory());
//		System.out.println(irdto.getHashtag());
//		System.out.println(irdto.getStartDay());
//		System.out.println(irdto.getEndDay());
//		System.out.println(irdto.getPayDay());
//		System.out.println(irdto.getSuccessMoney());
		
		request.setAttribute("irdto", irdto);//request����
		session.setAttribute("irdto", irdto);//session����
		
		ActionForward forward = new ActionForward();
		forward.setPath("main1.jsp?center=investRequest/investRequest2.jsp");
		forward.setRedirect(false);
//		return forward;
//		PrintWriter out =  response.getWriter();
//		out.println("<script>");
//		out.println("location.href='main1.jsp?center=investRequest/investRequest2.jsp';");
//		out.println("</script>");
		
		
		return forward;
	}

}
