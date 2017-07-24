package net.member.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO implements MemberMethod {

	
	// DB연결 메소드 저장하여 바로바로 불러낼수 있게함.
	private Connection getCon() throws Exception {
		Connection con = null;

		Context init = new InitialContext();

		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/jspbeginner");

		con = ds.getConnection();

		return con;
	}
	
	
	/*이메일 중복확인을 위한 메소드*/
	@Override
	public boolean CheckMember(String MemberEmail) {
		//중복확인을 위한 Check변수
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
					}else{//중복이아닐경우 Check에 0을 저장
						Check = true;
						
					}
					}catch(Exception e){
					System.out.println("CheckMember메소드 내부에서 오류 : "+e);
				}finally{//자원해제
					if(con!=null)try{con.close();}catch(Exception e){e.printStackTrace();}
					if(pstmt!=null)try{pstmt.close();}catch(Exception e){e.printStackTrace();}
					if(rs!=null)try{rs.close();}catch(Exception e){e.printStackTrace();}
				}
				System.out.println(Check);
				return Check;
		
	}
	/*회원가입 메소드*/
	@Override
	public Boolean JoinMember(MemberDTO mdto) {
		//회원가입 성공여부를 저장할 Check변수 선언
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
					System.out.println("JoinMember메소드 내부에서 오류 : ");
					e.printStackTrace();
					
				}finally{
					if(con!=null)try{con.close();}catch(Exception e){e.printStackTrace();}
					if(pstmt!=null)try{pstmt.close();}catch(Exception e){e.printStackTrace();}
				}
				
				return Check;
		
	}
	/*회원 수정메소드*/
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
			System.out.println("UpdateMember메소드 내부에서 오류 : "+e);
			
		}finally{
			if(con!=null)try{con.close();}catch(Exception e){e.printStackTrace();}
			if(pstmt!=null)try{pstmt.close();}catch(Exception e){e.printStackTrace();}
		}
		return check;
	}
	/*회원 삭제 메소드 완성(x) 다른 DB도 참고해야하므로*/
	@Override
	public void DeleteMember(String memberEmail, String pass) {
		
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        String dbpw = "";//DB상의 비밀번호 담아둘변수
        
        try{
        	//해당 회원의 비밀번호를 알기위한 쿼리
        	String sql1 ="select pass from member where memberEmail=?";
        	//해당 회원 삭제를 위한 쿼리
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
        			System.out.println("삭제 실패");
        		}
        	}
        	
        	
        }catch(Exception e){
        	System.out.println("Deletemember메소드 내부에서 오류 : "+e);
        	e.printStackTrace();
        }finally{
        	if(rs!=null)try{con.close();}catch(Exception ee){ee.printStackTrace();}
        	if(pstmt!=null)try{pstmt.close();}catch(Exception e){e.printStackTrace();}
        	if(con!=null)try{con.close();}catch(Exception e){e.printStackTrace();}
			
        }
	}


	/*로그인 DAO*/
	@Override
	public int LoginMember(String MemberEmail, String pass) {
		
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result=-1;//아이디x 
        String dbpw = "";//DB상의 비밀번호 담아둘변수
		
		try{
			
			String sql = "select pass from member where memberEmail = ?";
			
			con = getCon();
			pstmt = con.prepareStatement(sql);
        	pstmt.setString(1,MemberEmail);
        	rs = pstmt.executeQuery();
        	
        	if(rs.next()){//입력된 아이디 존재
        		dbpw = rs.getString(1);
        		if(dbpw.equals(pass)){
        			result =1; //아이디 비밀번호 일치
        		}else{
        			result =0; //비밀번호 불일치
        		}
        	}else{
        		result =-1;
        	}
			
		}catch(Exception e){
			System.out.println("Loginmember메소드 내부에서 오류 : "+e);
			e.printStackTrace();
		}finally{
			if(rs!=null)try{con.close();}catch(Exception ee){ee.printStackTrace();}
        	if(pstmt!=null)try{pstmt.close();}catch(Exception e){e.printStackTrace();}
        	if(con!=null)try{con.close();}catch(Exception e){e.printStackTrace();}
		}
		
		return result;
	}
	
	

	/*닉네임을 뿌려주기 위한 메소드*/
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
        	System.out.println("닉네임 뽑아내기 불가 : "+e);
        	e.printStackTrace();
        }finally{
        	if(rs!=null)try{con.close();}catch(Exception ee){ee.printStackTrace();}
        	if(pstmt!=null)try{pstmt.close();}catch(Exception e){e.printStackTrace();}
        	if(con!=null)try{con.close();}catch(Exception e){e.printStackTrace();}		
        }
		
		
		return name;
	}


	/*마이페이지에서 비밀번호 입력해서 유저를 판단하는 메소드*/
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
        	System.out.println("UserCheck메소드 내부에서 오류 : "+e);
        	e.printStackTrace();
        }finally {
			if(rs!=null)try{con.close();}catch(Exception ee){ee.printStackTrace();}
        	if(pstmt!=null)try{pstmt.close();}catch(Exception e){e.printStackTrace();}
        	if(con!=null)try{con.close();}catch(Exception e){e.printStackTrace();}			
		}
        		
        return result;
		
		
	}


	/*회원 정보를 뿌려주는메소드 기능은 구현 뿌리기는 하지않음*/
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
			if(rs!=null)try{con.close();}catch(Exception ee){ee.printStackTrace();}
        	if(pstmt!=null)try{pstmt.close();}catch(Exception e){e.printStackTrace();}
        	if(con!=null)try{con.close();}catch(Exception e){e.printStackTrace();}			
		}
		return mdto;
	}

	
	
	

}
