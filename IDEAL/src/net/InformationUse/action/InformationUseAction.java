package net.InformationUse.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.InformationUse.db.InformationUseDao;
import net.InformationUse.db.InformationUseDto;
import net.InformationUse.action.ActionForward;

public class InformationUseAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		InformationUseDao dao = new InformationUseDao();

		ArrayList<InformationUseDto> array = null;
			
		array = dao.Information();
		
		request.setAttribute("array", array);
		
		//페이지 이동 방식 여부값, 이동페이지 경로 값을 저장하여 리턴 해주는 객체 생성
		ActionForward forward = new ActionForward();
				
		//페이지 이동 방식 여부값 true로 저장
		forward.setRedirect(false);
		
		
		//이동할 페이지 주소 저장
		forward.setPath("main1.jsp?center=tap_ex2.jsp&centern=informationUse/informationUse.jsp");
				
				
		return forward;
	}

}
