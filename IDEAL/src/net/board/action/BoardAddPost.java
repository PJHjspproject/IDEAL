	package net.board.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.db.BoardDao;
import net.board.db.BoardDto;
/*게시판 글쓰기 */
public class BoardAddPost implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		BoardDao bdao = new BoardDao();
		BoardDto bdto = new BoardDto();
		
		
		HttpSession session = request.getSession();
		String sessionNickName = (String)session.getAttribute("nickName");
		String memberEmail = (String)session.getAttribute("memberEmail");
		//닉네임 폴더 안에 사진을 저장한다.
		String realFolder = request.getServletContext().getRealPath("image\\"+sessionNickName);
		System.out.println(realFolder);
		int max = 10*1024*1024;//10mb
		
		File dir = new File(realFolder);
		if(!dir.exists()){
			System.out.println("폴더가 없습니다");
			System.out.println(sessionNickName+"유저의 폴더를 생성합니다.");
			dir.mkdirs();
		}else{
			System.out.println("폴더가 존재합니다.");
		}
		
		
		MultipartRequest multi = new MultipartRequest(request, realFolder, max, "utf-8", new DefaultFileRenamePolicy());
		
		String nickName = multi.getParameter("nickName");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		
		Enumeration files = multi.getFileNames();
		String file = null;
		String filename = null; 
		if(files.hasMoreElements()){
			file = (String) files.nextElement();
			filename = multi.getFilesystemName("file");
		}
		//date�� dao�ʿ��� now()�Լ� ó��
		
		bdto.setTitle(title);
		bdto.setContent(content);
		bdto.setNickName(nickName);
		bdto.setDate(new Timestamp(System.currentTimeMillis()));
		if(file!=null) bdto.setFile(filename);
		
		System.out.println("BoardAddPost() DAO 실행전 변수들");
		System.out.println("title:"+bdto.getTitle());
		System.out.println("content:"+bdto.getContent());
		System.out.println("nickName:"+bdto.getNickName());
		if(file!=null) System.out.println("file:"+bdto.getFile());

		bdao.BoardAdd(bdto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("boardList.bo");
		forward.setRedirect(true);
		return forward;
	}
	
}