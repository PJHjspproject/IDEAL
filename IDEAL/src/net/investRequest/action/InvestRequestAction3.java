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

		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();

		String memberEmail = (String)session.getAttribute("memberEmail");
		if(memberEmail==null) memberEmail = request.getParameter("memberEmail");

		InvestRequestDto irdto = (InvestRequestDto)session.getAttribute("irdto");
		if(irdto == null) irdto = (InvestRequestDto)request.getAttribute("irdto");

		if(memberEmail==null) memberEmail = irdto.getMemberEmail();
		
		System.out.println("memberEmail:"+memberEmail);
		

		String realFolder = request.getServletContext().getRealPath("image\\"+memberEmail);

		File folder = new File(realFolder);
		if(!folder.exists()){
			folder.mkdirs();
			System.out.println(memberEmail+"유저의 폴더를 생성합니다.");
		}
		
		

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
		System.out.println("----------------------------");
		
		//DB처리
		InvestRequestDao irdao = new InvestRequestDao();
		int check = irdao.InsertInvestRequst(irdto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./goMain.ma");
		forward.setRedirect(true);
		return forward;
	}

}
