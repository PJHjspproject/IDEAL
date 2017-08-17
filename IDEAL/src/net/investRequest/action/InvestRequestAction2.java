package net.investRequest.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.investRequest.db.InvestRequestDto;

public class InvestRequestAction2 implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("InvestRequestAction2 ����");
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
		int max = 100*1024*1024;//100MB
		
		//multipart�� ����ó���� ���� ��ü ����	
		MultipartRequest multi = new MultipartRequest(request, realFolder, max, "UTF-8", new DefaultFileRenamePolicy());
		
		System.out.println("folderpath:"+realFolder);
		
		
//		Enumeration files = multi.getFileNames();
		Iterator files = (Iterator)multi.getFileNames();
		
		StringTokenizer st = null;
		ArrayList<String> mtc_arr = new ArrayList<String>();
		ArrayList<String> mfc_arr = new ArrayList<String>();
		String mtc = multi.getParameter("mainthumbcheck");
		String mfc = multi.getParameter("mainimagecheck");
		System.out.println("mtc:"+mtc);
		System.out.println("mfc:"+mfc);
		
		//mainthumbcheck�����Ͽ� arraylist�� �ֱ�
		st = new StringTokenizer(mtc, "$");
		if(st!=null){
			while(st.hasMoreTokens()){
				mtc_arr.add(st.nextToken());
			}
		}
		st = new StringTokenizer(mfc, "$");
		if(st!=null){
			while(st.hasMoreTokens()){
				mfc_arr.add(st.nextToken());
			}
		}
		
//		multi.getFilesystemName(arg0);//������ ���ε�� ��¥ �̸�.
//		multi.getOriginalFileName(arg0);//input�±׿� ǥ��������̸� 
		String src = "";
		ArrayList<String> file_arr = new ArrayList<String>();
		System.out.println("-------ir2's file list--------");
		String file = "";
		String mainThumb = "";
		String mainImage = "";
		String mainText = "";
		ArrayList<String> mt = new ArrayList<String>();
		while(files.hasNext()){
			file = (String) files.next();
			file_arr.add(file);
//			src = realFolder+"\\"+multi.getFilesystemName(file);
//			System.out.println(src);
//			System.out.println("multi.getFilesystemName(file):"+multi.getFilesystemName(file));
//			System.out.println("multi.getOriginalFileName(file):"+multi.getOriginalFileName(file));
//			for(int m=0;m<mtc_arr.size();m++){
//				if(multi.getOriginalFileName(file)!=null && multi.getOriginalFileName(file).equals(mtc_arr.get(m))){
////					System.out.println("["+str+"]["+multi.getOriginalFileName(file)+"]");
////					System.out.println("mainThumb �̹��� Ȯ��");
//					if(mainThumb.equals("")) mainThumb += multi.getFilesystemName(file);
//					else mainThumb += "$"+multi.getFilesystemName(file);
//				}
//			}
//			for(int m=0;m<mfc_arr.size();m++){
//				if(multi.getOriginalFileName(file)!=null && multi.getOriginalFileName(file).equals(mfc_arr.get(m))){
////					System.out.println("["+str+"]["+multi.getOriginalFileName(file)+"]");
////					System.out.println("mainImage �̹��� Ȯ��");
//					if(mainImage.equals("")) mainImage += multi.getFilesystemName(file);
//					else mainImage += "$"+multi.getFilesystemName(file);
//				}
//			}
		}
		
		System.out.println("file_arr list");
		for(String str:file_arr){
			System.out.println(str);
		}
		System.out.println("-----------------------------------");
		for(int i=0;i<mtc_arr.size();i++){
			for(int j=0;j<file_arr.size();j++){
				if(multi.getOriginalFileName(file_arr.get(j))!=null && multi.getOriginalFileName(file_arr.get(j)).equals(mtc_arr.get(i))){
					if(mainThumb.equals("")) mainThumb += multi.getFilesystemName(file_arr.get(j));
					else mainThumb += "$"+multi.getFilesystemName(file_arr.get(j));
				}
			}
		}
		for(int i=0;i<mfc_arr.size();i++){
			for(int j=0;j<file_arr.size();j++){
				if(multi.getOriginalFileName(file_arr.get(j))!=null && multi.getOriginalFileName(file_arr.get(j)).equals(mfc_arr.get(i))){
					if(mainImage.equals("")) mainImage += multi.getFilesystemName(file_arr.get(j));
					else mainImage += "$"+multi.getFilesystemName(file_arr.get(j));
				}
			}
		}
		
		
		mt.add(multi.getParameter("maintext1"));
		mt.add(multi.getParameter("maintext2"));
		mt.add(multi.getParameter("maintext3"));
		for(int i=0;i<mt.size();i++){
			if(i==0)mainText+=mt.get(i);
			else mainText+="$"+mt.get(i);
		}
		
		irdto.setMainThumb(mainThumb);
		irdto.setMainImage(mainImage);
		irdto.setMainText(mainText);
		
//		System.out.println("---ir1���� �Ѿ������+ir2 Ȯ��----");
		//1������
//		System.out.println("memberEmail:"+irdto.getMemberEmail());
		//irdto.investRequestNum�� auto_increment
//		System.out.println("program:"+irdto.getProgram());
//		System.out.println("dName:"+irdto.getdName());
//		System.out.println("introduce:"+irdto.getIntroduce());
//		System.out.println("category:"+irdto.getCategory());
//		System.out.println("hashtag:"+irdto.getHashtag());
//		System.out.println("startDay:"+irdto.getStartDay());
//		System.out.println("endDay:"+irdto.getEndDay());
//		System.out.println("payDay:"+irdto.getPayDay());
//		System.out.println("successMoney:"+irdto.getSuccessMoney());
//		System.out.println("thumbimage:"+irdto.getThumbImage());
		//2������
//		System.out.println("mainThumb:"+irdto.getMainThumb());
//		System.out.println("mainImage:"+irdto.getMainImage());
//		System.out.println("mainText:"+irdto.getMainText());
		//3������
//		System.out.println(irdto.getFile());
		//default or DB
//		System.out.println(irdto.getFundsituation());//�⺻�� 1
//		System.out.println(irdto.getPermitChk());
//		System.out.println(irdto.getUpdateChk());
//		System.out.println("----------------------------");
		
		request.setAttribute("irdto", irdto);//request������ irdto �ø���
		session.setAttribute("irdto", irdto);//session������ irdto �ø���
		
//		ActionForward forward = new ActionForward();
//		forward.setPath("./index.jsp?center=investRequest/investRequest3.jsp");
//		forward.setRedirect(false);
//		return forward;
		PrintWriter out =  response.getWriter();
		out.println("<script>");
		out.println("location.href='main1.jsp?center=investRequest/investRequest3.jsp';");
		out.println("</script>");
		return null;
	}

}
