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
		// request.getParameter�뒗 String�삎�떇�쑝濡� 諛쏆븘�삤湲� �븣臾몄뿉 �븵�뿉 Integer.parseInt瑜�
		// �궗�슜�븯�뿬 int�삎�쑝濡� 蹂��솚 �떆耳쒖쨾�빞 int�삎 蹂��닔�뿉 ���옣媛��뒫
		int num = Integer.parseInt(request.getParameter("num"));
		String content = request.getParameter("comContent"); 
		String nickName = request.getParameter("nickName");
		Timestamp date = new Timestamp(System.currentTimeMillis());
		// BoardDao�븞�뿉 �엳�뒗 commentInsert硫붿냼�뱶瑜� �궗�슜�븯湲� �쐞�빐 媛앹껜�깮�꽦
		BoardDao bdao = new BoardDao();
		// CommentDto媛앹껜 �옄泥대�� commentInsert�쓽 留ㅺ컻蹂��닔濡� �궗�슜�븯湲� �쐞�빐 媛앹껜 �깮�꽦
		CommentDto cdto = new CommentDto();
		
		// CommentDto媛앹껜�뿉 媛� �꽭�똿
		cdto.setNum(num);
		cdto.setContent(content);
		cdto.setNickName(nickName);
		cdto.setDate(date);
		System.out.println("----BoardCommentInsert----");
		System.out.println(cdto.getNum());
		System.out.println(cdto.getNickName());
		System.out.println(cdto.getContent());
		System.out.println(cdto.getDate());
		
		
//		// request�쁺�뿭�뿉 媛앹껜 �꽭�똿�빐二쇨린 -> "媛앹껜紐�", 媛앹껜
//		request.setAttribute("cdto", cdto);
		
		// BoardDao媛앹껜�쓽 commentInsert硫붿냼�뱶瑜� �궗�슜? �샇異�?
		bdao.commentInsert(cdto);

		request.setAttribute("num", num);
		
		ActionForward forward = new ActionForward();		
		forward.setPath("content.bo");
		forward.setRedirect(false);
		return forward;
	}
}