package net.member.action;
/*
 * ActionForward클래스는 Action인터페이스에서 명령을  수행하고..
 * 결과값을 가지고 페이지를 포워딩할때 사용되는 클래스
 * 
 * 1. 페이지 이동 방식 여부값 저장후 리턴
 * 		페이지 이동방식 여부값이 true -> Response.sendRedirect()
 * 		페이지 이동방식 여부값이 false -> forward()방식
 * 		
 * 2. 이동페이지 경로값을 저장하여 리턴해주는 역할
 * 
 * */
public class ActionForward {
	
	//페이지 이동여부값 저장 변수 선언
		private boolean isRedirect = false;
		//true -> sendRedirect - 이동할 페이지 주소 경로 노출
		//false => forward() - 이동할 페이지 주소 경로 노출x
		
		private String path = null;
		
		public void setRedirect(boolean isRedirect){
			this.isRedirect = isRedirect;
		}
		//페이지 이동방식 여부값 리턴
		public boolean isRedirect(){
			return isRedirect;
		}
		//이동할 페이지 경로 주소값을 저장할 메소드
		public void setPath(String path){
			this.path = path;
		}
		//이동할 페이지 경로값 리턴할 메소드
		public String getPath(){
			return path;
		}
	

}
