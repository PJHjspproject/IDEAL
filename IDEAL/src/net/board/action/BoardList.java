package net.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.db.BoardDao;

public class BoardList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		BoardDao bdao = new BoardDao();
		
		
		String pageNum = request.getParameter("pageNum");
		int pageSize = 6;
		System.out.println("pagenum"+pageNum);
		
		if(pageNum == null){
			pageNum = "1";
		}
		
		int nowPage = Integer.parseInt(pageNum);
		int startRow = (nowPage - 1)*pageSize;
		int pageBlock = 5;
		
		int count = bdao.BoardCount();
		int startPage = 0;
		int endPage = 0;
		int pageCount = 0;
		if(count > 0){
			pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			startPage = ((nowPage/pageBlock)-(nowPage%pageBlock == 0? 1 : 0)) * pageBlock + 1;
			System.out.println("�떆�옉�럹�씠吏�"+startPage);
			endPage = startPage + pageBlock - 1;
			System.out.println("�걹�럹�씠吏�"+endPage);
			if(endPage > pageCount){
				endPage = pageCount;
			}
		}
		
		
		
		ArrayList BoardList = bdao.BoardList(startRow, pageSize);
		
		
		request.setAttribute("BoardList", BoardList);
		request.setAttribute("count", count);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("pageCount", pageCount);
		ActionForward forward = new ActionForward();
		forward.setPath("main1.jsp?center=board/Board.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
