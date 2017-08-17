package net.InformationUse.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class InformationUseDao implements InformationMethod{
	//DB연결 메소드 만들어주세요
	
	private Connection getCon(){
		Connection con = null;
		
		try {
			
			//1. WAS서버와 연결된 웹프로젝트의 모든 정보를 가지고 있는 컨텍스트 객체 생성
			Context init = new InitialContext();
			//2. 연결된 WAS서버에서 DataSource(커넥션풀) 검색해서 가져오기
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/jspbeginner");
			//3. DataSource(커넥션풀)에서 미리 연결된 DB연동객체(커넥션) 빌려오기
			con = ds.getConnection();
			
		} catch (Exception err) {
			System.out.println("DB연결 오류 : " + err);
		}
		return con;
	}//DB연결 메소드 getcon 끝
	
	//자원해제 메소드
	public void freeRes(Connection con, PreparedStatement pstmt, ResultSet rs){
		if(con!=null){try{con.close();}catch(Exception e){e.printStackTrace();}}
		if(pstmt!=null){try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
		if(rs!=null){try{rs.close();}catch(Exception e){e.printStackTrace();}}
	}
	public void freeRes(Connection con, PreparedStatement pstmt){
		if(con!=null){try{con.close();}catch(Exception e){e.printStackTrace();}}
		if(pstmt!=null){try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
	}//자원해제 메소드 끝
	
		//주제를 기준으로 모든 글들을 가져오는 메소드
		public ArrayList<InformationUseDto> Information() {
			//DB연결 3총사
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			//리턴타입에 맞는 리턴 값 생성
			ArrayList<InformationUseDto> Array = new ArrayList<InformationUseDto>();
			
			
			try {
				
			//DB연결
				con = getCon();
			//SQL쿼리문 작성
			String sql = "select * from informationUse";
			//SQL쿼리문 실행값 RS에 담기
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//반복문 실행
			while (rs.next()) {
				//InformationUseDto객체 생성
				InformationUseDto dto = new InformationUseDto();
				//InformationUseDto객체안에 RS값 꺼내어 담기
				dto.setInfoNum(rs.getInt(1));
				dto.setInfoSubject(rs.getString(2));
				dto.setInfoTitle(rs.getString(3));
				dto.setInfoContent(rs.getString(4));
				dto.setInfoImage(rs.getString(5));
				//InformationUseDto객체를 ArrayList에 담기
				Array.add(dto);
			}
						
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Information메소드에서 오류 : "+e);
			}finally {
				freeRes(con, pstmt, rs);
			}
			
			//ArrayList리턴
			
			return Array;
		}//Information 메소드 끝

		//번호를 매개변수로 받아서 그번호에 따른 글을 상세보기하는 메소드
		
		public InformationUseDto getInfo(int infoNum) {
			//리턴타입의 변수 선언
			InformationUseDto dto = null;
			//DB연결 삼총사
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
			//DB연결
				con = getCon();
			//SQL쿼리문 작성
				String sql = "select * from informationUse where infoNum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, infoNum);
			rs = pstmt.executeQuery();
			//RS에 값이 있을경우~
			if(rs.next()){
				dto = new InformationUseDto();
			//InformationUseDto안에 RS값 꺼내어 담기
				dto.setInfoNum(rs.getInt(1));
				dto.setInfoSubject(rs.getString(2));
				dto.setInfoTitle(rs.getString(3));
				dto.setInfoContent(rs.getString(4));
				dto.setInfoImage(rs.getString(5));
				
				}
			
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("getInfo메소드 오류"+e);
			}finally {
				freeRes(con, pstmt, rs);
			}
			//리턴
			return dto;
		}

		//관리자가 글쓰는 메소드
		
		public void InsertInfo(InformationUseDto iudto) {
			//DB연결 2총사
			Connection con=null;
			PreparedStatement pstmt = null;
			try {
			//DB연결
			con = getCon();
			//SQL쿼리문 작성
			String sql = "insert into informationUse (infoNum,infoSubject,infoTitle,infoContent,infoImage) "
					+ "values(?,?,?,?,?)";
			//쿼리 실행
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, iudto.getInfoNum());
			pstmt.setString(2, iudto.getInfoSubject());
			pstmt.setString(3, iudto.getInfoTitle());
			pstmt.setString(4, iudto.getInfoContent());
			pstmt.setString(5, iudto.getInfoImage());
			pstmt.executeUpdate();
			
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("InsertInfo메소드 "+e);
			}finally {
				freeRes(con, pstmt);
			}
		}

		
		public void UpdateInfo(InformationUseDto iudto) {
			//DB연결 2총사
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
			//DB연결
			con = getCon();
			//SQL쿼리문 작성
			String sql = "update informationUse set infoNum=?, infoSubject=?, infoTitle=?, infoContent=?,"
					+ " infoImage=?";
			//쿼리 실행
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, iudto.getInfoNum());
			pstmt.setString(2, iudto.getInfoSubject());
			pstmt.setString(3, iudto.getInfoTitle());
			pstmt.setString(4, iudto.getInfoContent());
			pstmt.setString(5, iudto.getInfoImage());
			pstmt.executeUpdate();
			
			
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("UpdateInfo메소드 끝"+e);
			}finally {
				freeRes(con, pstmt);
			}
			
		}

		
		public void DeleteInfo(int infoNum) {
			//DB연결 2총사
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
			//DB연결
				con = getCon();
			//SQL쿼리문 작성
				String sql ="delete from informationUse where infoNum=?";
			//쿼리 실행
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, infoNum);
				pstmt.executeUpdate();
			
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("DeleteInfo메소드 오류: "+e);
			}finally {
				freeRes(con, pstmt);
			}
			
		}

}
