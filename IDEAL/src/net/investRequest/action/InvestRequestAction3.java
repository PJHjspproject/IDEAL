package net.investRequest.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.investRequest.db.InvestRequestDao;
import net.investRequest.db.InvestRequestDto;

public class InvestRequestAction3 implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("InvestRequestAction3 ����");
		System.out.println("request getContentType : " + request.getContentType());
		//�ѱ� ó��
		request.setCharacterEncoding("utf-8");
		//session ������ ����ϱ� ���ؼ� session���� �޾ƿ���
		HttpSession session = request.getSession();
		//session �������� memberEmail ��������(���� session�� ������ request���� ��������)
		String memberEmail = (String)session.getAttribute("memberEmail");
		if(memberEmail==null) memberEmail = request.getParameter("memberEmail");
		//session �������� irdto ��������(���� session�� ������ request���� ��������)
		InvestRequestDto irdto = (InvestRequestDto)session.getAttribute("irdto");
		if(irdto == null) irdto = (InvestRequestDto)request.getAttribute("irdto");
		//���� session���� request���� ������ ������irdto���� ��������
		if(memberEmail==null) memberEmail = irdto.getMemberEmail();
		
		System.out.println("memberEmail:"+memberEmail);
		
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
		
		MultipartRequest multi = new MultipartRequest(request, realFolder, max, "UTF-8", new DefaultFileRenamePolicy());
		
		
		String savefile = "";
		Enumeration files = multi.getFileNames();
		ArrayList file = new ArrayList();
		while(files.hasMoreElements()){
			file.add(files.nextElement());
		}
		for(int i=0;i<file.size();i++){
			if(file.get(i)!=null && !file.get(i).equals("") && !file.get(i).equals("null")){
				if(i==0) savefile+=multi.getFilesystemName((String) file.get(i));
				else savefile+= "$"+multi.getFilesystemName((String) file.get(i));
			}
		}
		
		irdto.setFile(savefile);
		
		
		System.out.println("---ir1���� �Ѿ������+ir2 Ȯ��----");
		//1������
		System.out.println("memberEmail:"+irdto.getMemberEmail());
		//irdto.investRequestNum�� auto_increment
		System.out.println("program:"+irdto.getProgram());
		System.out.println("dName:"+irdto.getDName());
		System.out.println("introduce:"+irdto.getIntroduce());
		System.out.println("category:"+irdto.getCategory());
		System.out.println("hashtag:"+irdto.getHashTag());
		System.out.println("startDay:"+irdto.getStartDay());
		System.out.println("endDay:"+irdto.getEndDay());
		System.out.println("payDay:"+irdto.getPayDay());
		System.out.println("successMoney:"+irdto.getSuccessMoney());
		System.out.println("thumbimage:"+irdto.getThumbImage());
		//2������
		System.out.println("mainThumb:"+irdto.getMainThumb());
		System.out.println("mainImage:"+irdto.getMainImage());
		System.out.println("mainText:"+irdto.getMainText());
		//3������
		System.out.println(irdto.getFile());
		//default or DB
//		System.out.println(irdto.getFundsituation());//�⺻�� 1
//		System.out.println(irdto.getPermitChk());
//		System.out.println(irdto.getUpdateChk());
		System.out.println("----------------------------");
		
		//DBó���޼ҵ�
		InvestRequestDao irdao = new InvestRequestDao();
		int check = irdao.InsertInvestRequst(irdto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./goMain.ma");
		forward.setRedirect(true);
		return forward;
	}

}
