package net.board.action;

import java.io.File;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.db.BoardDao;
import net.board.db.BoardDto;

public class BoardUpdatePro implements Action {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		HttpSession session = request.getSession();
		String SessionNickName = (String)session.getAttribute("nickName");
		
		String realFolder = request.getServletContext().getRealPath("image\\"+SessionNickName);
		int max = 10*1024*1024;
		
		MultipartRequest multi = new MultipartRequest(request, realFolder, max, "utf-8", new DefaultFileRenamePolicy());
		
		String deleteFileName = multi.getParameter("deleteFileName");
		String originalFineName = multi.getParameter("originalFineName");
		String delfile = "";
				
		StringTokenizer st = null;
		if(multi.getParameter("deleteFileName")!=null){
			st = new StringTokenizer(deleteFileName, "/");
			while(st.hasMoreTokens()){
				delfile = st.nextToken();
			}
		}
		
		int num = Integer.parseInt(multi.getParameter("num"));
		String nickName = multi.getParameter("nickName");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		Enumeration files = multi.getFileNames();
		String file = (String) files.nextElement();
		String filename = "";
		if(deleteFileName==null) {
			filename = originalFineName;
		}else{
			filename = multi.getFilesystemName(file);
			File f = new File(realFolder+"\\"+delfile);
			if(f.isFile()){
				f.delete();
			}
		}
		
		System.out.println("deleteFileName:"+delfile);
		System.out.println("multifilename:"+multi.getFilesystemName(file));
		
		BoardDao bdao = new BoardDao();
		BoardDto bdto = new BoardDto();
		
		bdto.setNum(num);
		bdto.setTitle(title);
		bdto.setNickName(nickName);
		bdto.setContent(content);
		bdto.setFile(multi.getFilesystemName(file));
		
		System.out.println("BoardUpdatePro()");
		System.out.println("num:"+num);
		System.out.println("nickName:"+nickName);
		System.out.println("title:"+title);
		System.out.println("content:"+content);
		System.out.println("filename:"+filename);

		bdao.updateBoard(bdto);
		
		request.setAttribute("bdto", bdto);
		ActionForward forward= new ActionForward();
		forward.setPath("content.bo?num="+num);
		forward.setRedirect(false);
		return forward;
	}

}