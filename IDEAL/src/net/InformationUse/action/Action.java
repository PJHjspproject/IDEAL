package net.InformationUse.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.InformationUse.action.ActionForward;

public interface Action {

	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
