package net.member.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.Card.db.CardDto;
import net.Investment.db.InvestmentDto;
import net.board.db.BoardDto;
import net.board.db.CommentDto;
import net.investRequest.db.InvestRequestDto;
import net.question.db.questionDto;

public class MemberDAO implements MemberMethod {

	

	private Connection getCon() throws Exception {
		Connection con = null;

		Context init = new InitialContext();

		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/jspbeginner");

		con = ds.getConnection();

		return con;
	}
	public void freeRes(Connection con, PreparedStatement pstmt, ResultSet rs){
		if(con!=null){try{con.close();}catch(Exception e){e.printStackTrace();}}
		if(pstmt!=null){try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
		if(rs!=null){try{rs.close();}catch(Exception e){e.printStackTrace();}}
	}
	public void freeRes(Connection con, PreparedStatement pstmt){
		if(con!=null){try{con.close();}catch(Exception e){e.printStackTrace();}}
		if(pstmt!=null){try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
	}
	
	
	//회원 중복 체크
	@Override
	public boolean CheckMember(String MemberEmail) {
	
				boolean Check = false;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try{
					con = getCon();
					String sql = "SELECT memberEmail FROM member WHERE memberEmail=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, MemberEmail);
					rs = pstmt.executeQuery();
					if(rs.next()){
						Check = false;
					}else{
						Check = true;
						
					}
					}catch(Exception e){
					System.out.println("CheckMember "+e);
				}finally{//占쎌쁽占쎌뜚占쎈퉸占쎌젫
					freeRes(con, pstmt, rs);
				}
				System.out.println(Check);
				return Check;
		
	}
	//회원가입
	@Override
	public Boolean JoinMember(MemberDTO mdto) {
		
				Boolean Check=false;
				Connection con = null;
				PreparedStatement pstmt = null;
			
				try{
					con = getCon();
					String sql = "INSERT INTO member(memberEmail,pass,name,birth,phone,nickName) VALUES(?,?,?,?,?,?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, mdto.getMemberEmail());
					pstmt.setString(2, mdto.getPass());
					pstmt.setString(3, mdto.getName());
					pstmt.setString(4, mdto.getBirth());				
					pstmt.setString(5, mdto.getPhone());
					pstmt.setString(6, mdto.getNickName());
					
					
					pstmt.executeUpdate();
					Check = true;
					
				}catch(Exception e){
					Check = false;
					System.out.println("JoinMember: ");
					e.printStackTrace();
					
				}finally{
					freeRes(con, pstmt);
				}
				
				return Check;
		
	}
	//회원 수정
	@Override
	public int UpdateMember(MemberDTO mdto){
		int check = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = getCon();
			
			String sql = "update member set name=?, nickName=?, birth=?, "
					+ "pass=?, phone=? where memberEmail=?";
			
		
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getName());
			pstmt.setString(2, mdto.getNickName());
			pstmt.setString(3, mdto.getBirth());
			pstmt.setString(4, mdto.getPass());
			pstmt.setString(5, mdto.getPhone());
			pstmt.setString(6, mdto.getMemberEmail());
			
			check = pstmt.executeUpdate();
			
			
			
		}catch(Exception e){
			System.out.println("UpdateMember : "+e);
			
		}finally{
			freeRes(con, pstmt);
		}
		return check;
	}
	//회원 삭제
	@Override
	public void DeleteMember(String memberEmail, String pass) {
		
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        String dbpw = "";//DB에 저장된 비밀번호
        
        try{
        	
        	String sql1 ="select pass from member where memberEmail=?";
       
        	String sql2 ="delete from member where id=?";
        	con = getCon();
        	
        	pstmt = con.prepareStatement(sql1);
        	pstmt.setString(1,memberEmail);
        	rs = pstmt.executeQuery();
        	
        	if(rs.next()){
        		dbpw = rs.getString("pass");
        		if(dbpw.equals(pass)){
        			
        			pstmt = con.prepareStatement(sql2);
        			pstmt.setString(1, memberEmail);
        			pstmt.executeUpdate();
        			
        			
        		}else{
        			System.out.println("errer");
        		}
        	}
        	
        	
        }catch(Exception e){
        	System.out.println("Deletemember "+e);
        	e.printStackTrace();
        }finally{
        	freeRes(con, pstmt, rs);
			
        }
	}


	//로그인 
	@Override
	public int LoginMember(String MemberEmail, String pass) {
		
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result=-1;
        String dbpw = "";
		
		try{
			
			String sql = "select pass from member where memberEmail = ?";
			
			con = getCon();
			pstmt = con.prepareStatement(sql);
        	pstmt.setString(1,MemberEmail);
        	rs = pstmt.executeQuery();
        	
        	if(rs.next()){
        		dbpw = rs.getString(1);
        		if(dbpw.equals(pass)){
        			result =1; 
        		}else{
        			result =0; 
        		}
        	}else{
        		result =-1;
        	}
			
		}catch(Exception e){
			System.out.println("Loginmember"+e);
			e.printStackTrace();
		}finally{
			freeRes(con, pstmt, rs);
		}
		
		return result;
	}
	
	

	//닉네임 중복
	@Override
	public boolean NickCheck(String nickName) {
		boolean check=false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getCon();
			String sql = "SELECT nickName FROM member WHERE nickName=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickName);
			rs = pstmt.executeQuery();
			if(rs.next()){
				check = false;
			}else{
				check = true;
				
			}
			
			}catch(Exception e){
			System.out.println("CheckMember "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		System.out.println(check);
		
		
		
		
		return check;
	}
	//Top.jsp에 닉네임을 뿌려주기위한 메소드
	@Override
	public String nickNamePick(String MemberEmail) {
		
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String name = "";
        try{
        	String sql = "select nickName from member where memberEmail =?";
        	con = getCon();
        	pstmt = con.prepareStatement(sql);
        	pstmt.setString(1,MemberEmail);
        	rs = pstmt.executeQuery();
        	
        	if(rs.next()){
        		name = rs.getString(1);
        	}
        	
        }catch(Exception e){
        	System.out.println(e);
        	e.printStackTrace();
        }finally{
        	freeRes(con, pstmt, rs);	
        }
		
		
		return name;
	}
	//마이페이지전에 비밀번호 확인을 위한 메서드
	@Override
	public boolean UserCheck(String memberEmail,String pass) {
		
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean result = false;
        
        String dbpw="";
        
        try{
        	String sql ="select pass from member where memberEmail = ?";
        	con = getCon();
        	
        	pstmt = con.prepareStatement(sql);
        	pstmt.setString(1, memberEmail);
        	rs = pstmt.executeQuery();
        	
        	if(rs.next()){
        		dbpw = rs.getString(1);
        		if(dbpw.equals(pass)){
        			result = true;
        		}else{
        			result = false;
        		}
        	}
        	System.out.println(result);
        }catch(Exception e){
        	System.out.println("UserCheck :"+e);
        	e.printStackTrace();
        }finally {
        	freeRes(con, pstmt, rs);		
		}
        		
        return result;
		
		
	}


	//회원정보 뿌려주기 메서드 Action과 뿌려주는거는 미구현
	@Override
	public MemberDTO InfoMember(String MemberEmail) {
		
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberDTO mdto = null;
       
		
		try{
			con = getCon();
			String sql ="select * from member where memberEmail=?";
			pstmt = con.prepareStatement(sql);
        	pstmt.setString(1, MemberEmail);
        	rs = pstmt.executeQuery();
        	
        	if(rs.next()){
        		mdto = new MemberDTO();
        		mdto.setBirth(rs.getString("birth"));
        		mdto.setMemberEmail(rs.getString("memberEmail"));
        		mdto.setName(rs.getString("name"));
        		mdto.setNickName(rs.getString("nickName"));
        		mdto.setPhone(rs.getString("phone"));
        		mdto.setPass(rs.getString("pass"));
        	}
			
		}catch(Exception e){
			
		}finally{
			freeRes(con, pstmt, rs);		
		}
		return mdto;
	}
	//goInvestmemtAction에서 쓰는데..
	public MemberDTO getMember(String memberEmail) {
		
		MemberDTO meDto = new MemberDTO();
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con=getCon();
			String sql = "select * from member where memberEmail=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberEmail);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				meDto = new MemberDTO();
				meDto.setMemberEmail(rs.getString(1));
				meDto.setPass(rs.getString(2));
				meDto.setName(rs.getString(3));
				meDto.setBirth(rs.getString(4));
				meDto.setPhone(rs.getString(5));
				meDto.setNickName(rs.getString(6));
			}
		} catch (Exception e) {
			System.out.println("getMemberList : " + e);
		} finally {
			freeRes(con, pstmt, rs);
		}
		return meDto;
	}
	//마이페이지 관련..
			////Memberinfo페이지에서 내 캠페인목록을 눌렀을때 캠페인목록 뿌려주는 메소드
			public ArrayList<InvestRequestDto> MemberMyinvestRequest(String email) {
				Connection con= null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				ArrayList<InvestRequestDto> array = new ArrayList<InvestRequestDto>();
				InvestRequestDto dto = null;
				
				try {
					con =getCon();
					
					String sql = "select * from investrequest where memberEmail=?";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, email);
					
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						dto = new InvestRequestDto();
						
						dto.setMemberEmail(rs.getString("memberEmail"));
						dto.setInvestRequestNum(rs.getInt("investRequestNum"));
						dto.setProgram(rs.getString("program"));
						dto.setEndDay(rs.getDate("endDay"));
						array.add(dto);
					}
				} catch (Exception e) {
					System.out.println("MemberMyinvestRequest에서 오류 : " + e);
				}finally {
					freeRes(con, pstmt, rs);
				}
				return array;
			}
			
			
			//내가 투자한 목록..
			
			//내가 투자한 목록 뿌려주는 메소드
			public ArrayList<InvestmentDto> MemberMyinvestment(String email) {
				Connection con= null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				ArrayList<InvestmentDto> array = new ArrayList<InvestmentDto>();
				InvestmentDto dto = null;
				
				try {
					con = getCon();
					
					String sql = "select * from investment where memberEmail=?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, email);
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						dto = new InvestmentDto();
						dto.setInvestMoney(rs.getInt("investMoney"));
						dto.setInvestName(rs.getString("investName"));
						dto.setInvestNum(rs.getInt("investNum"));
						dto.setInvestRequestNum(rs.getInt("investRequestNum"));
						dto.setMemberEmail(rs.getString("memberEmail"));
						dto.setProgram(rs.getString("program"));
						array.add(dto);
					}
					
				} catch (Exception e) {
					System.out.println("MemberMyinvestment에서 오류 : " + e);
				}finally {
					freeRes(con, pstmt, rs);
				}
				
				
				return array;
			}
			
			//보드 게시판에 내가 쓴 글
			public ArrayList<BoardDto> MemberMyWirteBoardView(String nickName) {
				Connection con= null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				ArrayList<BoardDto> array = new ArrayList<BoardDto>();
				BoardDto dto = null;
				
				try {
					con = getCon();
					
					String sql = "select * from board where nickName=?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, nickName);
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						dto = new BoardDto();
						dto.setNum(rs.getInt("num"));
						dto.setTitle(rs.getString("title"));
						dto.setDate(rs.getTimestamp("date"));
						array.add(dto);
					}
					
				} catch (Exception e) {
					System.out.println("MemberMyWirteBoardView에서 오류 : " + e);
				}finally {
					freeRes(con, pstmt, rs);
				}
				return array;
			}
			
			//1:1 문의에 내가 쓴 글
			public ArrayList<questionDto> MemberMyWirteQuestionView(String nickName) {
				Connection con= null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				ArrayList<questionDto> array = new ArrayList<questionDto>();
				questionDto dto = null;
				
				try {
					con = getCon();
					
					String sql = "select * from question where nickName=?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, nickName);
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						dto = new questionDto();
						dto.setNum(rs.getInt("num"));
						dto.setTitle(rs.getString("title"));
						dto.setInputDate(rs.getTimestamp("inputDate"));
						dto.setReContent(rs.getString("reContent"));
						dto.setQuestionStatement(rs.getBoolean("questionStatement"));
						array.add(dto);
					}
					
				} catch (Exception e) {
					System.out.println("MemberMyWirteQuestionView에서 오류 : " + e);
				}finally {
					freeRes(con, pstmt, rs);
				}
				return array;
			}
			
			//친목게시판 글 삭제 메소드
			public void Memberdeleteboard(int num) {//친목게시판 글 삭제 메소드
				Connection con= null;
				PreparedStatement pstmt = null;
				
				try {
					con = getCon();
					String sql = "delete from board where num=?";
					pstmt= con.prepareStatement(sql);
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
					
				} catch (Exception e) {
					System.out.println("Memberdeleteboard에서 오류 : " + e);
				}finally {
					freeRes(con, pstmt);
				}
				
			}
			//1:1문의 게시판 글 삭제 메소드
			public void Memberdeletequestion(int num) {
				Connection con= null;
				PreparedStatement pstmt = null;
				
				try {
					con = getCon();
					String sql = "delete from question where num=?";
					pstmt= con.prepareStatement(sql);
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
					
				} catch (Exception e) {
					System.out.println("Memberdeletequestion에서 오류 : " + e);
				}finally {
					freeRes(con, pstmt);
				}
			}
			//내가 쓴 댓글 보기 메소드
			public ArrayList<CommentDto> MemberMyReWirteView(String nickName) {
				Connection con= null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				ArrayList<CommentDto> array = new ArrayList<CommentDto>();
				CommentDto dto = null;
				
				try {
					con = getCon();
					
					String sql = "select * from comment where nickName=?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, nickName);
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						dto = new CommentDto();
						dto.setNum(rs.getInt("num"));
						dto.setcNum(rs.getInt("cNum"));
						dto.setContent(rs.getString("content"));
						dto.setDate(rs.getTimestamp("date"));
						array.add(dto);
					}
					
				} catch (Exception e) {
					System.out.println("MemberMyReWirteView에서 오류 : " + e);
				}finally {
					freeRes(con, pstmt, rs);
				}
				return array;
			}
			//내가 쓴 댓글 삭제 메소드
			public void Memberdeletecomment(int cNum) {
				Connection con= null;
				PreparedStatement pstmt = null;
				
				try {
					con = getCon();
					String sql = "delete from comment where cNum=?";
					pstmt= con.prepareStatement(sql);
					pstmt.setInt(1, cNum);
					pstmt.executeUpdate();
					
				} catch (Exception e) {
					System.out.println("Memberdeletequestion에서 오류 : " + e);
				}finally {
					freeRes(con, pstmt);
				}
				
			}
			
			//카드 잔액 보기
			public ArrayList<CardDto> MemberMyCard(String email) {
				Connection con= null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				ArrayList<CardDto> array = new ArrayList<CardDto>();
				CardDto dto = null;
				
				try {
					con = getCon();
					
					String sql = "select * from card where memberEmail=?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, email);
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						dto = new CardDto();
						dto.setCardBalance(rs.getInt("cardBalance"));
						dto.setMemberBirth(rs.getString("memberBirth"));
						dto.setCardBank(rs.getString("cardBank"));
						array.add(dto);
					}
					
				} catch (Exception e) {
					System.out.println("MemberMyCard에서 오류 : " + e);
				}finally {
					freeRes(con, pstmt, rs);
				}
				return array;
			}
			//회원탈퇴 메소드
			public int checkInvestReqDate(String email) {//등록한 프로젝트의 파운드시추에이션(상태)체크 메소드
				Connection con= null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				Date today = new Date(System.currentTimeMillis());//현재 날짜
				SimpleDateFormat fomday = new SimpleDateFormat("yyyy-MM-dd");
				InvestRequestDto dto = null;
				int check = -1;//혹시 투자요청한 프로젝트가 없을경우에 대비해서 디폴트값을 -1(페이데이 지나서 투자하기 리스트로 넘어가기)로 줍니다.
				String sql = "";
				
				try {
					con = getCon();
					/*sql = "select * from  investRequest As a Inner JOIN investMent As b "
							+ "on a.investRequestNum = b.investRequestNum where a.memberEmail =?";*/
					sql = "select * from  investRequest where memberEmail =?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, email);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						String	payday = fomday.format(rs.getDate("payDay")); 
						String[] payday_arr = payday.split("-");
						String todayRe = fomday.format(today);//현재날짜
						String[] todayRe_arr = todayRe.split("-");
						
						if(rs.getInt("fundSituation") == 3 || rs.getInt("fundSituation") == 4){
							
							Date abdat = new Date(Integer.parseInt(payday_arr[0])  , Integer.parseInt(payday_arr[1]),Integer.parseInt(payday_arr[2]));
							//payday
							Date paydat = new Date(Integer.parseInt(todayRe_arr[0]),Integer.parseInt(todayRe_arr[1]), Integer.parseInt(todayRe_arr[2]));	
							//현재날짜
							long total = (abdat.getTime()-paydat.getTime())/(24*60*60*1000);
							
							if(total < 0){
									check = -1;
							}else if (total == 0 || total >1) {
								check = 2;
							}
								
						}else if (rs.getInt("fundSituation") == 2 ) {
							
							check = 1;
							
						}else if (rs.getInt("fundSituation") == 1) {
							check = 0;
						}
						
					}
					
					
				} catch (Exception e) {
					System.out.println("checkInvestReqDate에서 오류 : " + e);
				}finally {
					freeRes(con, pstmt, rs);
				}
				return check;
			}
			
			
			
			public int checkInvestMent(String email) {//투자한 프로젝트의 페이데이 체크하는 메소드
				Connection con= null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				Date today = new Date(System.currentTimeMillis());//현재 날짜
				SimpleDateFormat fomday = new SimpleDateFormat("yyyy-MM-dd");
				int check = -1;//혹시 투자한 프로젝트가 없을경우에 대비해서 디폴트값을 -1(페이데이 지나서 삭제메소드 실행)로 줍니다.
				String sql = "";
				
				try {
					con = getCon();
					sql = "select * from  investMent As a Inner JOIN investRequest As b "
							+ "on a.investRequestNum = b.investRequestNum where a.memberEmail =?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, email);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						String	payday = fomday.format(rs.getDate("payDay")); 
						String[] payday_arr = payday.split("-");
						String todayRe = fomday.format(today);//현재날짜
						String[] todayRe_arr = todayRe.split("-");
						
						if(rs.getInt("fundSituation") == 3 || rs.getInt("fundSituation") == 4){
							
							Date abdat = new Date(Integer.parseInt(payday_arr[0])  , Integer.parseInt(payday_arr[1]),Integer.parseInt(payday_arr[2]));
							//payday
							Date paydat = new Date(Integer.parseInt(todayRe_arr[0]),Integer.parseInt(todayRe_arr[1]), Integer.parseInt(todayRe_arr[2]));	
							//현재날짜
							long total = (abdat.getTime()-paydat.getTime())/(24*60*60*1000);
							
							
							if(total < 0){
									check = -1;
									
							}else if (total == 0 || total >1) {
								check = 2;
							}
								
						}else if (rs.getInt("fundSituation") == 2 ) {
							
							check = 1;
							
						}else if (rs.getInt("fundSituation") == 1) {
							check = 0;
						}
						
					}
					
					
				} catch (Exception e) {
					System.out.println("checkInvestMent에서 오류 : " + e);
				}finally {
					freeRes(con, pstmt, rs);
				}
				
				
				
				return check;
			}
				
			
			
			
			
			
			
			
			
			
			
			
			
			
			public void MemberoutAction(String email) {//회원삭제 메소드
				Connection con= null;
				PreparedStatement pstmt = null;
				String sql;
				try {
					con = getCon();
					sql = "delete from member where memberEmail=?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, email);
					pstmt.executeUpdate();
							
				} catch (Exception e) {
					System.out.println("MemberOutAction에서 오류 : " + e);
				}finally {
					freeRes(con, pstmt);
				}
				
			}
}
