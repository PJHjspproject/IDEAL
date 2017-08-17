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
		System.out.println("InvestRequestAction1 진입");
		System.out.println("request getContentType : " + request.getContentType());
		
		//한글 처리
		request.setCharacterEncoding("utf-8");
		
		
		
		//session 영역을 사용하기 위해서 session영역 받아오기
		HttpSession session = request.getSession();
		String memberEmail = (String)session.getAttribute("memberEmail");
		
		//업로드할 파일의 경로//상대경로
		String realFolder = request.getServletContext().getRealPath("image\\"+memberEmail);
		
//		File folder = new File("D:\\jsp\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\test2\\image"+"\\"+memberEmail);//서버쪽 프로젝트 폴더에 사용자 닉네임으로 폴더 생성
//		File folder = new File("D:\\workspace_jquerymobile\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\test2\\image\\"+memberEmail);
		File folder = new File(realFolder);
		if(!folder.exists()){//해당 폴더가 있는지 체크
			folder.mkdirs();//폴더가 없다면 폴더 생성
			System.out.println(memberEmail+"유저의 폴더를 생성합니다.");
		}
		
		
		//업로드 파일의 최대 크기 -> 목록에 노출되는 썸네일 이미지 크기 제한 1mb
		int max = 10*1024*1024;//10MB
		
		InvestRequestDto irdto = new InvestRequestDto();
		MultipartRequest multi = new MultipartRequest(request, realFolder, max, "UTF-8", new DefaultFileRenamePolicy());
		
		String file = "";//임시로 저장할 섬네일 이미지 이름(단일)
		Enumeration files = multi.getFileNames();
		String thumbimage = "";//DB에 저장될 섬네일 이미지 이름
		if(files.hasMoreElements()){
			file = (String) files.nextElement();
			String src = realFolder+"\\"+multi.getFilesystemName(file);
			thumbimage += multi.getFilesystemName(file);
			
			
			System.out.println("--------iRA1<섬네일 파일 체크>--------");
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
		
		//investRequestDto에 앞에서 필요한 값 담아주기
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
		
		request.setAttribute("irdto", irdto);//request영역
		session.setAttribute("irdto", irdto);//session영역
		
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
