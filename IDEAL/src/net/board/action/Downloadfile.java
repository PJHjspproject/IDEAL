package net.board.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Downloadfile implements Action {

	private String getBrowser(HttpServletRequest request) {
	    String header =request.getHeader("User-Agent");
	    if (header.contains("MSIE")) {
	           return "MSIE";
	    } else if(header.contains("Chrome")) {
	           return "Chrome";
	    } else if(header.contains("Opera")) {
	           return "Opera";
	    }
	    return "Firefox";
	}
	
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		HttpSession session = request.getSession();
		String path = request.getParameter("path");
		String name = request.getParameter("name");
		String nickName = (String)session.getAttribute("nickName");
		String realPath = request.getServletContext().getRealPath("image\\"+nickName);
		String header = getBrowser(request);
		
		response.setContentType("Application/x-msdownload");
		
		File file = new File(realPath + "/"+name);
		
		System.out.println(file);
		
		if(header.contains("MSIE")){
			String docName = URLEncoder.encode(name,"UTF-8").replaceAll("\\", "%20");
			response.setHeader("Content-Disposition", "attachment; filename=" + docName + ";");
			
		}else{
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(name,"UTF-8") + ";");
		}
		byte[] data = new byte[1024];
		
		
		if (file.isFile()) {
			
			try {
				/*서버에 있는 다운로드할 파일의 내용을 읽어 들이기 위한 통로 준비 */
				// new FileInputStream(file) : 
				//다운로드할 실제 파일객체 가 가리키는 파일을 1바이트씩 읽어 들이기 위한 스트림 통로 만들기
				// new BufferedInputStream(new FileInputStream(file)) : 
				//파일객체가 가리키는 파일 데이터 모두를 1바이트씩 읽어 들여서..
				//별도 내부버퍼 공간에 모아두었다가....
				//한꺼번에 파일자체를 읽어 들이기 위한 통로 만들기 
				BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));

				/*사용자에게 파일을 출력, 내보내기(다운로드)하기위한 통로 준비*/
				BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream());

				int read;

				while ((read = input.read(data)) != -1) {//read()메소드의 반환값은 읽어들이기 성공한 바이트수를 반환
					//전체 data배열의 0부터 1024개 바이트씩 묶어서 출력 버퍼에 내보낸다.
					output.write(data, 0, read);
				}
				//출력버퍼 공간이 가득 차지 않아도 파일내용을 강제적으로 사용자의 화면에 뿌려주는 기능을 제공한다.
				output.flush(); // 강제로 출력버퍼에서 데이터 비우기 
				output.close(); //자원 닫기
				input.close(); //자원 닫기

			} catch (Exception err) {
				err.printStackTrace();
			}
	}//if end
		
		ActionForward forward =null;
		
		return forward;

		

	}
}
