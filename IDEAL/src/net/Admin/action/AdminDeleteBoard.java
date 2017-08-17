package net.Admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Admin.db.AdminDao;

public class AdminDeleteBoard implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminDao adao = new AdminDao();
		int num = Integer.parseInt(request.getParameter("num"));
		
		int check = adao.DeleteBoard(num);
		
		if(check == 1){
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제되었습니다');");
			out.print("location.href='AdminBoardList.ad'");
			out.println("</script>");
			out.close();
		}
		
		return null;
	}

}
