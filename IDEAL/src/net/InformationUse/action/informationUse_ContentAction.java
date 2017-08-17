package net.InformationUse.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.InformationUse.db.InformationUseDao;
import net.InformationUse.db.InformationUseDto;

public class informationUse_ContentAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int infoNum = Integer.parseInt(request.getParameter("infoNum"));
		
		InformationUseDao dao = new InformationUseDao();
		
		InformationUseDto dto = dao.getInfo(infoNum);
	
		request.setAttribute("dto", dto);
		
		//�̵�
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./main1.jsp?center=informationUse/informationUse_content.jsp");
		
		return forward;
	}

}
