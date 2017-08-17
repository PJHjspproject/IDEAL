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
		
//		bdto.setNum(Integer.parseInt(request.getParameter("num")));
//		bdto.setNickName(request.getParameter("nickName"));
//		bdto.setTitle(request.getParameter("title"));
//		bdto.setDate(new Timestamp(System.currentTimeMillis()));
//		bdto.setContent(request.getParameter("content"));
		BoardDto bdto = bdao.getBoard(num);
		System.out.println("num:"+bdto.getNum());
		System.out.println("nickName:"+bdto.getNickName());
		System.out.println("title:"+bdto.getTitle());
		System.out.println("date:"+bdto.getDate());
		System.out.println("content:"+bdto.getContent());
		
		ArrayList commentList = bdao.CommentList(num);
		
		//request������ �ø�
		request.setAttribute("bdto", bdto);
		request.setAttribute("commentList", commentList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("main1.jsp?center=board/content.jsp");
		forward.setRedirect(false);
		
		return forward ;
	}
/*	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		int num=Integer.parseInt(request.getParameter("num"));
		
//		System.out.println(num);
		
		BoardDao bdao = new BoardDao();

		// int num �뙆�씪誘명꽣 媛��졇�삤湲�
		

		// request.setAttribute("媛앹껜紐�", 媛앹껜);
		
//		bdto.setNum(Integer.parseInt(request.getParameter("num")));
//		bdto.setNickName(request.getParameter("nickName"));
//		bdto.setTitle(request.getParameter("title"));
//		bdto.setDate(new Timestamp(System.currentTimeMillis()));
//		bdto.setContent(request.getParameter("content"));
		BoardDto bdto = bdao.getBoard(num);
//		System.out.println(bdto.getNum());
//		System.out.println(bdto.getNickName());
//		System.out.println(bdto.getTitle());
//		System.out.println(bdto.getDate());
//		System.out.println(bdto.getContent());
		
		request.setAttribute("bdto", bdto);
		//content �럹�씠吏��뿉�꽌 肉뚮젮以섏빞 �븷 �궡�슜 
		//�븳 寃뚯떆臾쇱뿉 ���븳 �젙蹂� + 寃뚯떆臾쇱뿉 ���븳 �뙎湲�
		//�뿬湲곗꽌 鍮좎쭊 遺�遺� -> 寃뚯떆臾쇱뿉 ���븳 �뙎湲��쓣 媛��졇�삤�뒗 遺�遺�
		//寃뚯떆臾쇱뿉 ���븳 �뙎湲��� �빐�떦 寃뚯떆臾쇱쓽 NUM媛믪쓣 �뙆�씪誘명꽣 媛믪쑝濡� �꽆寃� 諛쏆븘���꽌
		//commentDAO�쓽 硫붿냼�뱶�쓽 �씤�옄媛믪쑝濡� �꽆寃⑥쨾�꽌 �빐�떦 寃뚯떆臾쇱쓽 �뙎湲��쓣 媛��졇�삩�떎(�뿬湲곗꽌 �뙎湲��씠 �븳媛� �씠�긽�씠�씪硫� dto媛� �븘�땲�씪 Arraylist�벑�쑝濡� 諛쏆븘���빞�븳�떎
		//諛쏆븘�삩 寃곌낵媛믪쓣 request�쁺�뿭�뿉 �삱�젮以��떎
		//洹� �썑 content.jsp�럹�씠吏��뿉�꽌 getAttribute濡� 諛쏆븘�꽌 �궗�슜�븳�떎(吏�湲덉� 諛쏆븘�삤�뒗嫄곕쭔 �엳�뼱�꽌 臾몄젣)
		
		ArrayList commentList = bdao.CommentList(num);
		request.setAttribute("commentList", commentList);
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("main1.jsp?center=board/content.jsp");
		forward.setRedirect(false);
		
		return forward ;
	}*/
}
