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
	/*브라우저 헤더를 얻기 위한 메소드 (파일 한글처리을 위해서 만들었다)*/
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
		//다운로드 파일을 서버에서 클라이언트의 브라우저로 내보내기 전에 헤더정보 설정하기
		//다운로드 문서형식으로 내보내겠다고 수정하기
		response.setContentType("Application/x-msdownload");
		//업로드된 서버에 있는 파일에 직접적으로 접근할수 있는 파일 객체 생성
		File file = new File(realPath + "/"+name);
		
		System.out.println(file);
		/*파일 한글처리 if문은 IE11에선 작동을 하지않는다.. else문은 크롬인데 크롬에선 정상적으로 작동한다.*/
		if(header.contains("MSIE")){
			String docName = URLEncoder.encode(name,"UTF-8").replaceAll("\\", "%20");
			response.setHeader("Content-Disposition", "attachment; filename=" + docName + ";");
			
		}else{
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(name,"UTF-8") + ";");
		}
		//서버에 있는 파일의 데이터를  1024바이트 씩 읽어 들이기위한 단위
		byte[] data = new byte[1024];
		
		//만약에  다운로드할 파일이  file형식이 맞다면?
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
				
				output.flush();
				output.close();
				input.close();

			} catch (Exception err) {
				err.printStackTrace();
			}
	}//if end
		//이동할 페이지가 없으므로 null을 리턴
		ActionForward forward =null;
		
		return forward;

		

	}
}
