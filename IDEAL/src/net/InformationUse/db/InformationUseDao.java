package net.InformationUse.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class InformationUseDao implements InformationMethod{
	
	
	private Connection getCon(){
		Connection con = null;
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/jspbeginner");
			con = ds.getConnection();
			
		} catch (Exception err) {
			System.out.println("DB연결 에러 : " + err);
		}
		return con;
	}//DB getcon() end
	
	
	public void freeRes(Connection con, PreparedStatement pstmt, ResultSet rs){
		if(con!=null){try{con.close();}catch(Exception e){e.printStackTrace();}}
		if(pstmt!=null){try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
		if(rs!=null){try{rs.close();}catch(Exception e){e.printStackTrace();}}
	}
	public void freeRes(Connection con, PreparedStatement pstmt){
		if(con!=null){try{con.close();}catch(Exception e){e.printStackTrace();}}
		if(pstmt!=null){try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
	}//자원해제 끝
	
		//주제를 기준으로 모든 글들을 가져오는 메소드
		public ArrayList<InformationUseDto> Information() {
			//DB연결
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<InformationUseDto> Array = new ArrayList<InformationUseDto>();
			
			
			try {
				con = getCon();
				String sql = "select * from informationUse";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
			
				while (rs.next()) {
					InformationUseDto dto = new InformationUseDto();
					dto.setInfoNum(rs.getInt(1));
					dto.setInfoSubject(rs.getString(2));
					dto.setInfoTitle(rs.getString(3));
					dto.setInfoContent(rs.getString(4));
					dto.setInfoImage(rs.getString(5));
					Array.add(dto);
				}
						
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Information 메소드 에러: "+e);
			}finally {
				freeRes(con, pstmt, rs);
			}
			return Array;
		}//Information 끝

		//번호를 매개변수로 받아서 그번호에 따른 글을 상세보기하는 메소드
		
		public InformationUseDto getInfo(int infoNum) {
			
			InformationUseDto dto = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = getCon();
				String sql = "select * from informationUse where infoNum=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, infoNum);
				rs = pstmt.executeQuery();
			
				if(rs.next()){
					dto = new InformationUseDto();
					dto.setInfoNum(rs.getInt(1));
					dto.setInfoSubject(rs.getString(2));
					dto.setInfoTitle(rs.getString(3));
					dto.setInfoContent(rs.getString(4));
					dto.setInfoImage(rs.getString(5));
				
				}
			
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("getInfo() 메소드 에러"+e);
			}finally {
				freeRes(con, pstmt, rs);
			}
			
			return dto;
		}

		//관리자가 글쓰는 메소드
		
		public void InsertInfo(InformationUseDto iudto) {
			
			Connection con=null;
			PreparedStatement pstmt = null;
			try {
				con = getCon();
				String sql = "insert into informationUse (infoNum,infoSubject,infoTitle,infoContent,infoImage) "
					+ "values(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, iudto.getInfoNum());
			pstmt.setString(2, iudto.getInfoSubject());
			pstmt.setString(3, iudto.getInfoTitle());
			pstmt.setString(4, iudto.getInfoContent());
			pstmt.setString(5, iudto.getInfoImage());
			pstmt.executeUpdate();
			
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("InsertInfo메소드 에러 "+e);
			}finally {
				freeRes(con, pstmt);
			}
		}

		//FAQ 수정 
		public void UpdateInfo(InformationUseDto iudto) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
			
			con = getCon();
			
			String sql = "update informationUse set infoNum=?, infoSubject=?, infoTitle=?, infoContent=?,"
								+ " infoImage=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, iudto.getInfoNum());
			pstmt.setString(2, iudto.getInfoSubject());
			pstmt.setString(3, iudto.getInfoTitle());
			pstmt.setString(4, iudto.getInfoContent());
			pstmt.setString(5, iudto.getInfoImage());
			pstmt.executeUpdate();
			
			
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("UpdateInfo()메소드에러"+e);
			}finally {
				freeRes(con, pstmt);
			}
			
		}

		//FAQ 삭제
		public void DeleteInfo(int infoNum) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
			
				con = getCon();
				String sql ="delete from informationUse where infoNum=?";
			
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, infoNum);
				pstmt.executeUpdate();
			
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("DeleteInfo() 메서드 에러: "+e);
			}finally {
				freeRes(con, pstmt);
			}
			
		}

}
