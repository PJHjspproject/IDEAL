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
		System.out.println("InvestRequestAction2 진입");
		System.out.println("request getContentType : " + request.getContentType());
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String memberEmail = (String)session.getAttribute("memberEmail");
		if(memberEmail==null) memberEmail = request.getParameter("memberEmail");
		InvestRequestDto irdto = (InvestRequestDto)session.getAttribute("irdto");
		if(irdto == null) irdto = (InvestRequestDto)request.getAttribute("irdto");
		if(memberEmail==null) memberEmail = irdto.getMemberEmail();
		
		System.out.println("memberEmail:"+memberEmail);
		
		
		//업로드할 파일의 경로//상대경로
		String realFolder = request.getServletContext().getRealPath("image\\"+memberEmail);
//		File folder = new File("D:\\jsp\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\test2\\image"+"\\"+memberEmail);//������ ������Ʈ ������ ����� �г������� ���� ����
//		File folder = new File("D:\\workspace_jquerymobile\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\test2\\image\\"+memberEmail);
		File folder = new File(realFolder);
		if(!folder.exists()){
			folder.mkdirs();
			System.out.println(memberEmail+"유저의 폴더를 생성합니다.");
		}
		
		
		
		
		int max = 100*1024*1024;//100MB
		
		//multipart로 파일처리를 위한 객체 생성
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
		
		//mainthumbcheck구분하여 arraylist에 넣기
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
		
//		multi.getFilesystemName(arg0);//파일의 업로드된 진짜 이름.
//		multi.getOriginalFileName(arg0);//input태그에 표기된파일이름 
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

		}
		
		System.out.println("file_arr list");
		for(String str:file_arr){
			System.out.println(str);
		}
		System.out.println("-----------------------------------");
		//여러 사진파일 배열에 담기!
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

		request.setAttribute("irdto", irdto);//request영역에 irdto 올리기
		session.setAttribute("irdto", irdto);//session영역에 irdto 올리기

		PrintWriter out =  response.getWriter();
		out.println("<script>");
		out.println("location.href='main1.jsp?center=investRequest/investRequest3.jsp';");
		out.println("</script>");
		return null;
	}

}
