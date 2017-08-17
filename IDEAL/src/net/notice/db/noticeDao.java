package net.notice.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class noticeDao implements noticeMethod{
	//DB연결 메소드
	private Connection getConnection() throws Exception{
		Connection con=null;
		Context inti=new InitialContext();
		DataSource ds=(DataSource)inti.lookup("java:comp/env/jdbc/jspbeginner");
		con=ds.getConnection();
		return con;
	}//getConnection 끝
	
	//공지사항 글 전체목록 불러오는 메소드
	@Override
	public ArrayList<noticeDto> AllNotice() {
		//DB삼총사
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<noticeDto> array = new ArrayList<noticeDto>();
		noticeDto dto;
		try {
			//DB연결 
			con = getConnection();
			//sql구문 작성
			String sql = "select * from notice";
			//구문 실행
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//반복문 돌려 rs에서 정보를 꺼내 dto에 담고 다시 어레이리스트에 담기
			while (rs.next()) {
				dto = new noticeDto();
				dto.setNoticeNum(rs.getInt(1));
				dto.setNoticeSubject(rs.getString(2));
				dto.setNoticeContent(rs.getString(3));
				dto.setNoticeDate(rs.getTimestamp(4));
				dto.setNoticeImg(rs.getString(5));
				array.add(dto);
				
			}
			//자원해제
			con.close();
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("AllNotice메소드 오류 :" +e);
		}
				
		
		
		return array;
	}// AllNotice 끝

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
		//DB삼총사 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		noticeDto dto = null;
		try {
			//DB연결
			con = getConnection();
			//sql구문 작성
			String sql = "select * from notice where noticeNum=?";
			//구문 실행
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, noticeNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()){//만약 제대로 검색 되었다면?
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
			System.out.println("OneNotice메소드 오류 :"+e);
		}
		
		return dto;
	}//OneNotice 끝

}
