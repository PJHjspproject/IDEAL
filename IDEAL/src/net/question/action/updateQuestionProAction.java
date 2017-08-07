package net.question.action;

import java.io.File;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.question.db.questionDao;
import net.question.db.questionDto;

public class updateQuestionProAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//session영역 받아오기
		HttpSession session = request.getSession();
		
		//session영역에서 값 가져오기
		String SessionNickName = (String)session.getAttribute("nickName");
		
		
		String realFolder = request.getServletContext().getRealPath("image\\"+SessionNickName);
		int max = 10*1024*1024;
		MultipartRequest multi = new MultipartRequest(request, realFolder, max, "utf-8", new DefaultFileRenamePolicy());
		String deleteFileName = multi.getParameter("deleteFileName");
		String originalFineName = multi.getParameter("originalFineName");
		String delfile = "";
		StringTokenizer st = new StringTokenizer(deleteFileName, "/");
		while(st.hasMoreTokens()){
			delfile = st.nextToken();
		}
		int num = Integer.parseInt(multi.getParameter("num"));
		String nickName = multi.getParameter("nickName");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		Enumeration files = multi.getFileNames();
		String file = (String) files.nextElement();//이미지가 단 하나 넘어오므로 있으면 값이 담기고 없으면 null
		String filename = "";
		if(deleteFileName==null) {//지울 파일이 없을때, 즉 파일을 지우지 않고 그대로 놔둘 경우
			filename = originalFineName;//원래의 파일 이름 
		}else{
			filename = multi.getFilesystemName(file);
			//지울 파일이 있다면 파일 제거
			File f = new File(realFolder+"\\"+delfile);
			if(f.isFile()){
				f.delete();
			}
		}
		
		
		
		System.out.println("deleteFileName:"+delfile);
		System.out.println("multifilename:"+multi.getFilesystemName(file));
		
		questionDto qdto = new questionDto();
		qdto.setNum(num);
		qdto.setTitle(title);
		qdto.setNickName(nickName);
		qdto.setContent(content);
		qdto.setImage(filename);
		
		System.out.println("updatequestionProAction()에서 확인");
		System.out.println("num:"+num);
		System.out.println("nickName:"+nickName);
		System.out.println("title:"+title);
		System.out.println("content:"+content);
		System.out.println("filename:"+filename);
		
		
		//update하기 위한 dao객체 생성
		questionDao qdao = new questionDao();
		qdao.UpdateQuestion(qdto);
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("question.qU");
		forward.setRedirect(true);
		return forward;
	}

}
