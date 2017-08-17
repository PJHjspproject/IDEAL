package net.member.action;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.investRequest.db.InvestRequestDto;
import net.member.db.MemberDAO;

public class MemberOutAction implements Action {

	//Memberinfo.jsp에서 회원탈퇴를 누르면 컨트롤러를 거쳐서 이 페이지로 온다.
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session=request.getSession();//세션 영역 가져오기
		String email = (String)session.getAttribute("MemberEmail");//MemberEmail을 세션에서 꺼내와서 스트링 변수에 저장
		
		MemberDAO dao = new MemberDAO();
		
		int result = 0; //투자요청 상태에 따라 메소드를 달리 하기 때문에 그걸 구분할 인트형 변수 선언
		
		//email을 매개변수로 자신이 투자요청한 리스트 검색해서 교부예정일이 지났는 지 체크하는 메소드를 만들어서 fundsituation을 비교하기 위해
		//값을 리턴해서 인트형 변수에 담아준다.
		result = dao.checkInvestReqDate(email); 
		
		System.out.println(result);
		
		//리턴 값에 따라 실행할 메소드를 달리해 준다.
		
		if(result == -1){//진행중인 프로젝트가 없지만, 기간완료성공(fundsituation = 4) 또는
		    			//조기성공(fundsituation = 3) 되었을 경우 해당 캠페인이 교부예정일(payDay)이 지났다면
			
			int resultRe = dao.checkInvestMent(email);//투자하기 펀드시추에이션 체크 메소드
			
			if(resultRe == -1){//페이데이가 지난 경우 삭제 메소드 실행
				System.out.println(resultRe);
				
				dao.MemberoutAction(email);//회원탈퇴 메소드
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('사이트를 이용해주셔서 감사합니다.');");
				out.print("location.href='./MemberLogoutAction.mf'");
				out.println("</script>");
				out.close();
				return null;
				
				
			}else if (resultRe == 2) {//페이데이가 지나지 않은경우
				System.out.println(resultRe);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('교부날짜가 지나지 않았어요. 다시 확인해 주세요');");
				out.print("location.href='./Memberinfo.mf'");
				out.println("</script>");
				out.close();
				return null;
			}else if (resultRe == 1) {//투자가 실패한 경우
				System.out.println(resultRe);
				dao.MemberoutAction(email);//회원탈퇴 메소드
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('사이트를 이용해주셔서 감사합니다.');");
				out.print("location.href='./MemberLogoutAction.mf'");
				out.println("</script>");
				out.close();
				return null;
			}else if (resultRe == 0) {//프로젝트가 진행중이라면..
				System.out.println(resultRe);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('프로젝트가 진행중 입니다. 다시 확인해 주세요');");
				out.print("location.href='./Memberinfo.mf'");
				out.println("</script>");
				out.close();
				return null;
			}
		    
			
		}else if (result == 1) {//투자요청이 실패했을 경우 투자한 리스트에서 페이데이 검색한다.
			
			int resultRe = dao.checkInvestMent(email);//투자하기 펀드시추에이션 체크 메소드
			System.out.println(resultRe);
			if(resultRe == -1){//페이데이가 지난 경우 삭제 메소드 실행
				System.out.println(resultRe);
				dao.MemberoutAction(email);//회원탈퇴 메소드
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('사이트를 이용해주셔서 감사합니다.');");
				out.print("location.href='./MemberLogoutAction.mf'");
				out.println("</script>");
				out.close();
				return null;
			}else if (resultRe == 2) {//페이데이가 지나지 않은경우
				System.out.println(resultRe);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('교부날짜가 지나지 않았어요. 다시 확인해 주세요');");
				out.print("location.href='./Memberinfo.mf'");
				out.println("</script>");
				out.close();
				return null;
				
			}else if (resultRe == 1) {//투자가 실패한 경우
				System.out.println(resultRe);
				dao.MemberoutAction(email);//회원탈퇴 메소드
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('사이트를 이용해주셔서 감사합니다.');");
				out.print("location.href='./MemberLogoutAction.mf'");
				out.println("</script>");
				out.close();
				return null;
			}else if (resultRe == 0) {//프로젝트가 진행중이라면..
				System.out.println(resultRe);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('프로젝트가 진행중 입니다. 다시 확인해 주세요');");
				out.print("location.href='./Memberinfo.mf'");
				out.println("</script>");
				out.close();
				return null;
			}
		    
			
		}else if (result == 0) {//투자요청한 프로젝트가 진행중이라면
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('프로젝트가 진행중 입니다. 다시 확인해 주세요');");
			out.print("location.href='./Memberinfo.mf'");
			out.println("</script>");
			out.close();
			return null;
			
		}else if (result == 2) {//프로젝트는 끝났지만 교부날짜가 지나지 않았다면?
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('교부날짜가 지나지 않았어요. 다시 확인해 주세요');");
			out.print("location.href='./Memberinfo.mf'");
			out.println("</script>");
			out.close();
			return null;
		}
		
		
		
		
		
		
		return null;
		
		
		
		
			
	}

}
