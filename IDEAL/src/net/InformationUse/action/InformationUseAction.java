package net.InformationUse.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.InformationUse.db.InformationUseDao;
import net.InformationUse.db.InformationUseDto;
import net.InformationUse.action.ActionForward;
/*FAQ 글 목록 출력*/
public class InformationUseAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		InformationUseDao dao = new InformationUseDao();

		ArrayList<InformationUseDto> array = null;
		array = dao.Information();
		request.setAttribute("array", array);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("main1.jsp?center=tap_ex2.jsp&centern=informationUse/informationUse.jsp");
		return forward;
	}

}
