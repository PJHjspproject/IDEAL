package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.db.BoardDao;
import net.board.db.BoardDto;

public class BoardUpdate implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response){
		
		// request�쁺�뿭�뿉 �엳�뜕 num媛믪쓣 媛��졇���꽌 int�삎 蹂��닔 num�뿉 ���옣�븳�떎.
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDao bdao= new BoardDao();
		
		// BoardDao�쓽 getBoard硫붿냼�뱶 �떎�뻾媛�(?)�쓣  BoardDto媛앹껜�뿉 �떞�뒗�떎.
		BoardDto bdto =  bdao.getBoard(num);
		
		// request�쁺�뿭�뿉 媛앹껜瑜� �꽭�똿�빐以��떎
		request.setAttribute("bdto", bdto);
		System.out.println(bdto.getNickName());
		ActionForward forward = new ActionForward();
			
		forward.setPath("main1.jsp?center=board/Update.jsp");
		forward.setRedirect(false);
		return forward;
	}
}