package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;
import net.member.db.MemberDTO;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

				String MemberEmail=request.getParameter("memberEmail");
				String pass=request.getParameter("pass");
				System.out.println(MemberEmail);
				System.out.println(pass);
				//DB占쎌삂占쎈씜 揶쏆빘猿� 占쎄문占쎄쉐
				MemberDAO mdao=new MemberDAO();
				
			 

				//占쎄텢占쎌뒠占쎌쁽揶쏉옙 占쎌뿯占쎌젾占쎈립 id占쏙옙 pass揶쏅�⑤궢...DB占쎈퓠 占쎌뿳占쎈뮉 id,pass揶쏅�れ뱽 �뜮袁㏉꺍占쎈릭占쎈연 嚥≪뮄�젃占쎌뵥 筌ｌ꼶�봺 占쎈릭疫뀐옙 
				//: check = 1 -> 占쎈툡占쎌뵠占쎈탵, �뜮袁⑨옙甕곕뜇�깈 筌띿쉸�벉
				//: check = 0 -> 占쎈툡占쎌뵠占쎈탵, �뜮袁⑨옙甕곕뜇�깈 占쏙옙�뵳占�
				//: check = -1 -> 占쎈툡占쎌뵠占쎈탵 占쏙옙�뵳占�
				int check=mdao.LoginMember(MemberEmail, pass);
				
				System.out.println(check);
				// check==0  "�뜮袁⑨옙甕곕뜇�깈占쏙옙�뵳占�" 占쎈츟嚥≪뮇�뵠占쎈짗
				if(check==0){
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out=response.getWriter();
					out.println("<script>");
					out.println("alert('로그인에 실패하였습니다. \\n 비밀번호를 확인해주세요');");
					out.println("location.href='goMain.ma';");
					out.println("</script>");
					out.close();
					return null;
				
				// check==-1 "占쎈툡占쎌뵠占쎈탵占쎈씨占쎌벉" 占쎈츟嚥≪뮇�뵠占쎈짗	
				}else if(check==-1){
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out=response.getWriter();
					out.println("<script>");
					out.println("alert('로그인에 실패하였습니다. \\n 아이디를 확인해주세요');");
					out.println("location.href='goMain.ma';");
					out.println("</script>");
					out.close();
					return null;	
				}
				
				
		
				//占쎄쉭占쎈�▼첎�빘猿� 占쎄문占쎄쉐
				HttpSession session=request.getSession();
				String nickName = mdao.nickNamePick(MemberEmail);
				//login.jsp 占쎌넅筌롫똻肉됵옙苑� 占쎌뿯占쎌젾占쎈립 占쎈툡占쎌뵠占쎈탵�몴占� 占쎄쉭占쎈�▼첎�빘猿쒙옙�겫占쎈열占쎈퓠 占쏙옙占쎌삢
				
				session.setAttribute("memberEmail", MemberEmail);
				session.setAttribute("nickName", nickName);
				//session.setAttribute("nickName", nickName);
			/*嚥≪뮄�젃占쎌뵥 占쎄쉐�⑤벊�뻻.... CarMain.jsp 占쎈읂占쎌뵠筌욑옙嚥∽옙 占쎌뵠占쎈짗 占쎈뻻占쎄텚占쎈뼄.*/
				//占쎈읂占쎌뵠筌욑옙 占쎌뵠占쎈짗 獄쎻뫗�뻼 占쎈연�겫占� 揶쏉옙,占쎌뵠占쎈짗占쎈읂占쎌뵠筌욑옙 野껋럥以� 揶쏉옙 占쏙옙占쎌삢 占쎈릭占쎈연 �뵳�뗪쉘 占쎈퉸雅뚯눖�뮉 揶쏆빘猿� 占쎄문占쎄쉐
				ActionForward forward=new ActionForward();
				
				//占쎈읂占쎌뵠筌욑옙 占쎌뵠占쎈짗 獄쎻뫗�뻼 占쎈연�겫占� 揶쏉옙 true嚥∽옙 占쏙옙占쎌삢  
				//true sendRedirect() <-占쎌뵠獄쎻뫗�뻼占쏙옙 占쎌뵠占쎈짗占쎈막 占쎈읂占쎌뵠筌욑옙 雅뚯눘�꺖 野껋럥以� 占쎈걗�빊占� 占쎈맙.	
				forward.setRedirect(true);
				forward.setPath("./index.mf"); 

				return forward;
		
		
	}
	
	

}
