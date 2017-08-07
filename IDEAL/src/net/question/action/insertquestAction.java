package net.question.action;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.question.db.questionDao;
import net.question.db.questionDto;

public class insertquestAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Insertquestion ����");
		System.out.println("request getContentType : " + request.getContentType());
		
		request.setCharacterEncoding("UTF-8");	
		
		
		HttpSession session = request.getSession();
		String memberEmail = (String)session.getAttribute("memberEmail");
		String nickName = (String)session.getAttribute("nickName");
		System.out.println(memberEmail);
		System.out.println(nickName);
		String realFolder = request.getServletContext().getRealPath("image\\"+nickName);//�г��� ���� �ȿ� ������ �����Ѵ�.
		File folder = new File(realFolder);
		if(!folder.exists()){
			folder.mkdirs();
			System.out.println(memberEmail+"폴더를 생성합니다.");
		}
		int max = 10*1024*1024;//10MB
		MultipartRequest multi = new MultipartRequest(request, realFolder, max, 
				"UTF-8", new DefaultFileRenamePolicy());
		
		String subject= multi.getParameter("title");
		String content= multi.getParameter("content");
		String file = "";
		Enumeration files = multi.getFileNames();
		String dbimage = "";
		if(files.hasMoreElements()){
			file = (String) files.nextElement();
			String src = realFolder+"\\"+multi.getFilesystemName(file);
			dbimage += multi.getFilesystemName(file);

			System.out.println("--------iRA1--------");
			System.out.println("src:"+src);
			System.out.println("filename:"+multi.getFilesystemName(file));
			System.out.println("---------------------------------");
		}

		System.out.println("저장된 파일이름"+multi.getFilesystemName(file));
		questionDto qdto = new questionDto();
		qdto.setNickName(nickName);
		qdto.setTitle(subject);
		qdto.setContent(content);
		qdto.setInputDate(new Timestamp(System.currentTimeMillis())); 
		qdto.setImage(multi.getFilesystemName(file));	
		
		questionDao dao = new questionDao();
		int result = dao.InsertQuestion(qdto);
		
		session.setAttribute("image", dbimage);
		if(result == 0){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('글 등록 실패');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
			
		}
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("location.href='question.qU';");
		out.println("</script>");
		out.close();
		return null;
		
	}

}
