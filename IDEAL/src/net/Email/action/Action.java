package net.Email.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*Action관련 클래스틀이....
 * Action 인터페이스의 추상메소드를 오버라이딩 함으로써
 * 클라이언트 요청처리 형태를 규격화 시킴*/
public interface Action {//클래스를 만들기 위한 틀
	//특정 클라이언트의 요청을 수행하여 그 결과값을 ActionForward클래스 타입으로 반환
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
	throws Exception;
	
}
