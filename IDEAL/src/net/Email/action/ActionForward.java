package net.Email.action;
/*
 * ActionForward클래스는 Action 인터페이스에서 명령을 수행하고..
 * 결과 값을 가지고 페이지를 포워딩(이동)할때 사용하는 클래스
 * 
 * 하는일1. 페이지 이동방식 여부값 저장휴 리턴
 * 				페이지 이동방식 여부값 true ->Response.sendRedirect()방식
 * 				페이지 이동방식 false일때 -> forward()방식
 * 
 * 하는일 2. 이동페이지 경로값 저장하여 리턴 해주는 역할.
 * 			
 * */
public class ActionForward {
	//페이지 이동방식 여부 값 저장할 변수
	private boolean isRedirect = false;
	//true sendRedirect() -- 주소경로 노출
	//false forward() -- 노출 안함
	
	//이동 페이지 경로 주소값 저장변수
	private String path = null;
	
	//페이지 이동방식 여부값 저장하는 메소드
	public void setisRedirect(boolean isRedirect){
		this.isRedirect = isRedirect;
	}
	//페이지 이동방식 여부값 리턴 메소드
	public boolean getisRedirect(){
		return isRedirect;
	}
	//이동할 페이지 경로 주소값 저장할 메소드
	public void setPath(String path){
		this.path = path;
	}
	//이동할 페이지 경로 주소값 리턴 메소드
	public String getPath(){
		return path;
	}

	
	
	
	
	
	
}
