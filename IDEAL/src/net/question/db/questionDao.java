package net.question.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class questionDao implements questionMethod{
	
	
	
	
	private Connection getCon(){
		Connection con = null;
		
		try {
			
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/jspbeginner");
			con = ds.getConnection();
			
		} catch (Exception err) {
			System.out.println("DB���� ���� : " + err);
		}
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
	
	public int maxquestion(){//max값 구하기
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int max = 0;
				
		try {
			con = getCon();
			String sql = "select max(num) from question";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				max = rs.getInt(1)+1;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("maxquestion 에러 :"+e);
		}finally {
			freeRes(con, pstmt, rs);
		}
		return max;
	}
	
	
	//자기가 쓴 모든글을 볼수 있는 메소드
	public ArrayList<questionDto> AllQuestion(String nickName) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<questionDto> array = new ArrayList<questionDto>();
		try {
			
			con = getCon();
			
			String sql="select * from question where nickName=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickName);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				questionDto dto = new questionDto();
				
				dto.setNum(rs.getInt(1));
				dto.setTitle(rs.getString(2));
				dto.setNickName(rs.getString(3));
				dto.setInputDate(rs.getTimestamp(4));
				dto.setQuestionStatement(rs.getBoolean(5));
				dto.setContent(rs.getString(6));
				dto.setReContent(rs.getString(7));
				array.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("AllQuestion 메소드 에러 :"+e);
		}finally {
			freeRes(con, pstmt, rs);
		}
		
		return array;
	}//AllQuestion
	
	//1:1문의 글 작성메소드
	public int InsertQuestion(questionDto qdto) {
		
		Connection con=null;
		PreparedStatement pstmt=null;
		int num = maxquestion();
		int result = 0;
		try {
			
			con = getCon();
						
			String sql = "insert into question (num,title,nickName,inputDate,questionStatement,content,reContent,image) "
					+ "values(?,?,?,?,?,?,?,?)";
				 	
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, qdto.getTitle());
			pstmt.setString(3, qdto.getNickName());
			pstmt.setTimestamp(4, qdto.getInputDate());
			pstmt.setBoolean(5, qdto.getQuestionStatement());
			pstmt.setString(6, qdto.getContent());
			pstmt.setString(7, qdto.getReContent());
			pstmt.setString(8, qdto.getImage());
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("InsertQuestion 메소드 에러 :"+e);
		}finally {
			freeRes(con, pstmt);
		}
		
		return result;
	}//InsertQuestion end
	
	//1:1 수정
		public void UpdateQuestion(questionDto qdto) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				
				con = getCon();
				String sql = "update question set title=?, inputDate=now(), content=?, image=? where num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, qdto.getTitle());
				pstmt.setString(2, qdto.getContent());
				pstmt.setString(3, qdto.getImage());
				pstmt.setInt(4, qdto.getNum());
				pstmt.executeUpdate();
				
				
			} catch (Exception e) {
				System.out.println("UpdateQuestion 메소드 에러 :"+e);
			} finally {
				freeRes(con, pstmt);
			}
		}//UpdateQuestion end
	
	//1:1 삭제
	public int DeleteQuestion(int num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			
			con = getCon();
			
			String sql = "delete from question where num =? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
						
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DeleteQuestion 메소드 에러"+e);
		}finally {
			freeRes(con, pstmt);
		}
		
		return result;
		
	}
	//1:1� 글 상세보기
	public questionDto OneQuestion(int num) {
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	
		questionDto dto = null;
		
		try {
			
			con = getCon();
			
			String sql = "select * from question where num =?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				dto = new questionDto();
				
				dto.setNum(rs.getInt(1));
				dto.setTitle(rs.getString(2));
				dto.setNickName(rs.getString(3));
				dto.setInputDate(rs.getTimestamp(4));
				dto.setQuestionStatement(rs.getBoolean(5));
				dto.setContent(rs.getString(6));
				dto.setReContent(rs.getString(7));
				dto.setImage(rs.getString(8));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("OneQuestion�޼ҵ� ����"+e);
		}finally {
			freeRes(con, pstmt, rs);
		}
		return dto;
		
	}//OneQuestion end
	
	//1:1문의 글 답변 달아주는 메소드
	public void UpdateReContent(questionDto qdto) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = getCon();
			
			String sql="update question set reContent=? where num=? ";
			
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qdto.getReContent());
			pstmt.setInt(2, qdto.getNum());
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UpdateReContent에서 에러 :"+e);
		}finally {
			freeRes(con, pstmt);
		}
			
	}//UpdateReContent end

	
}
