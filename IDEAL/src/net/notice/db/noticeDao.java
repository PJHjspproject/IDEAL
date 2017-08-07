package net.notice.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class noticeDao implements noticeMethod{
	
	private Connection getConnection() throws Exception{
		Connection con=null;
		Context inti=new InitialContext();
		DataSource ds=(DataSource)inti.lookup("java:comp/env/jdbc/jspbeginner");
		con=ds.getConnection();
		return con;
	}
	
	//공지사항 글 전체목록 불러오는 메소드
	@Override
	public ArrayList<noticeDto> AllNotice() {
		
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<noticeDto> array = new ArrayList<noticeDto>();
		noticeDto dto;
		try {
			
			con = getConnection();
			
			String sql = "select * from notice";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dto = new noticeDto();
				dto.setNoticeNum(rs.getInt(1));
				dto.setNoticeSubject(rs.getString(2));
				dto.setNoticeContent(rs.getString(3));
				dto.setNoticeDate(rs.getTimestamp(4));
				dto.setNoticeImg(rs.getString(5));
				array.add(dto);
				
			}
			
			con.close();
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("AllNotice메소드에서 에러 :" +e);
		}
				
		
		
		return array;
	}// AllNotice end

	//공지사항 글 등록메소드
	@Override
	public void InsertNotice(noticeDto ndto) {
		
		
	}

	//공지사항 글 수정메소드
	@Override
	public void UpdateNotice(noticeDto ndto) {
		
		
	}

	//공지사항 글 삭제메소드
	@Override
	public void DeleteNotice(int num) {
		
		
	}

	//공지사항 글 중 하나의 글 불러오는 메소드
	@Override
	public noticeDto OneNotice(int noticeNum) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		noticeDto dto = null;
		try {
			
			con = getConnection();
			
			String sql = "select * from notice where noticeNum=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, noticeNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dto = new noticeDto();
				dto.setNoticeNum(rs.getInt(1));
				dto.setNoticeSubject(rs.getString(2));
				dto.setNoticeContent(rs.getString(3));
				dto.setNoticeDate(rs.getTimestamp(4));
				dto.setNoticeImg(rs.getString(5));
			}
			con.close();
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("OneNotice 메소드에서 에러 :"+e);
		}
		
		return dto;
	}//OneNotice end

}
