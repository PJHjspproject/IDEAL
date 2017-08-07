package net.question.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.question.db.questionDao;

public class deletequestAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		questionDao dao = new questionDao();
		
		int result = dao.DeleteQuestion(num);
		//result ==0 삭제 실패
		if(result == 0){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 실패');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		//삭제 성공일때
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("location.href='question.qU';");
		out.println("</script>");
		out.close();
		return null;
		
		
		
		
		
		
		
		
		
		
	}

}
