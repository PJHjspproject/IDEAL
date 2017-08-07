package net.board.action;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDao;
import net.board.db.BoardDto;
import net.board.db.CommentDto;

public class BoardContent implements Action{
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		int num=Integer.parseInt(request.getParameter("num"));
		
		System.out.println("BoardContent()");
		System.out.println("request num:"+num);
		
		BoardDao bdao = new BoardDao();

		BoardDto bdto = bdao.getBoard(num);
		System.out.println("num:"+bdto.getNum());
		System.out.println("nickName:"+bdto.getNickName());
		System.out.println("title:"+bdto.getTitle());
		System.out.println("date:"+bdto.getDate());
		System.out.println("content:"+bdto.getContent());
		
		ArrayList commentList = bdao.CommentList(num);
		

		request.setAttribute("bdto", bdto);
		request.setAttribute("commentList", commentList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("main1.jsp?center=board/content.jsp");
		forward.setRedirect(false);
		
		return forward ;
	}

}
