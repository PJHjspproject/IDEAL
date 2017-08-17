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
		System.out.println("InvestRequestAction3 진입");
		System.out.println("request getContentType : " + request.getContentType());
		//한글 처리
		request.setCharacterEncoding("utf-8");
		//session 영역을 사용하기 위해서 session영역 받아오기
		HttpSession session = request.getSession();
		//session 영역에서 memberEmail 가져오기(만약 session에 없으면 request에서 가져오기)
		String memberEmail = (String)session.getAttribute("memberEmail");
		if(memberEmail==null) memberEmail = request.getParameter("memberEmail");
		//session 영역에서 irdto 가져오기(만약 session에 없으면 request에서 가져오기)
		InvestRequestDto irdto = (InvestRequestDto)session.getAttribute("irdto");
		if(irdto == null) irdto = (InvestRequestDto)request.getAttribute("irdto");
		//만약 session에도 request에도 없으면 가져온irdto에서 꺼내오기
		if(memberEmail==null) memberEmail = irdto.getMemberEmail();
		
		System.out.println("memberEmail:"+memberEmail);
		
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
		
		
		System.out.println("---ir1에서 넘어온파일+ir2 확인----");
		//1페이지
		System.out.println("memberEmail:"+irdto.getMemberEmail());
		//irdto.investRequestNum은 auto_increment
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
		//2페이지
		System.out.println("mainThumb:"+irdto.getMainThumb());
		System.out.println("mainImage:"+irdto.getMainImage());
		System.out.println("mainText:"+irdto.getMainText());
		//3페이지
		System.out.println(irdto.getFile());
		//default or DB
//		System.out.println(irdto.getFundsituation());//기본값 1
//		System.out.println(irdto.getPermitChk());
//		System.out.println(irdto.getUpdateChk());
		System.out.println("----------------------------");
		
		//DB처리메소드
		InvestRequestDao irdao = new InvestRequestDao();
		int check = irdao.InsertInvestRequst(irdto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./goMain.ma");
		forward.setRedirect(true);
		return forward;
	}

}
