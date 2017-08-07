package net.board.action;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDao;
import net.board.db.BoardDto;
import net.board.db.CommentDto;

public class BoardCommentInsert implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");

		int num = Integer.parseInt(request.getParameter("num"));
		String content = request.getParameter("comContent"); 
		String nickName = request.getParameter("nickName");
		Timestamp date = new Timestamp(System.currentTimeMillis());

		BoardDao bdao = new BoardDao();

		CommentDto cdto = new CommentDto();
		

		cdto.setNum(num);
		cdto.setContent(content);
		cdto.setNickName(nickName);
		cdto.setDate(date);
		System.out.println("----BoardCommentInsert----");
		System.out.println(cdto.getNum());
		System.out.println(cdto.getNickName());
		System.out.println(cdto.getContent());
		System.out.println(cdto.getDate());
		
		bdao.commentInsert(cdto);

		request.setAttribute("num", num);
		
		ActionForward forward = new ActionForward();		
		forward.setPath("content.bo");
		forward.setRedirect(false);
		return forward;
	}
}