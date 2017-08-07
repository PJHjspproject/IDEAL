package net.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.xml.transform.Result;

import org.w3c.dom.Comment;

public class BoardDao {

	private Connection getCon() throws Exception{
		
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
	
	
	public int BoardCount(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try{
			con = getCon();
			String sql = "select count(*) from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		}catch(Exception e){
			System.out.println("BoardCount 메소드 에러: "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return count;
	}
	
	public ArrayList<BoardDto> BoardList (int startRow, int pageSize){
		ArrayList<BoardDto> BoardList = new ArrayList<BoardDto>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = getCon();
			
			String sql = "SELECT * FROM board order by num desc limit ?,?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			while(rs.next()){
				BoardDto bdto = new BoardDto();

				bdto.setNum(rs.getInt("num"));
				bdto.setContent(rs.getString("content"));
				bdto.setDate(rs.getTimestamp("date"));
				bdto.setFile(rs.getString("file"));
				bdto.setNickName(rs.getString("nickName"));
				bdto.setTitle(rs.getString("title"));
				
				BoardList.add(bdto);
			}
			
		} catch (Exception e) {
			System.out.println("BoardList메소드 에서 에러 발생!: "+e);
		} finally{
			freeRes(con, pstmt, rs);
		}		
		return BoardList;
	}
	
	public void BoardAdd(BoardDto bdto){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{

			con = getCon();

			String sql = "INSERT INTO board(title,content,file,date,nickName) values(?,?,?,?,?)";

			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, bdto.getTitle());
			pstmt.setString(2, bdto.getContent());
			pstmt.setString(3, bdto.getFile());
			pstmt.setTimestamp(4, bdto.getDate());
			pstmt.setString(5, bdto.getNickName());

			pstmt.executeUpdate();
			
			
		} catch(Exception e){
			System.out.println("BoardAdd메소드 에서 에러 발생!: "+e);
		} finally{
			freeRes(con, pstmt);
		}
		
	}
	
	public BoardDto getBoard(int num){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		BoardDto bdto=null;
		
		try{
			con=getCon();
			sql="select * from board where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()){
				bdto=new BoardDto();
				bdto.setNum(rs.getInt(1));
				bdto.setTitle(rs.getString(2));
				bdto.setContent(rs.getString(3));
				bdto.setFile(rs.getString(4));
				bdto.setDate(rs.getTimestamp(5));
				bdto.setNickName(rs.getString(6));
//				System.out.println(rs.getString("nickName"));
//				System.out.println(rs.getString("title"));
//				System.out.println(rs.getTimestamp("date"));
//				System.out.println(rs.getString("content"));
			}
		} catch(Exception e){
			System.out.println("getBoard메소드 에서 에러 발생!: "+e);
		} finally {
			freeRes(con, pstmt, rs);
		}
		return bdto;		
	}
	
	public void updateBoard(BoardDto bdto){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = getCon();
			String sql = "update board set title=?, date=now(), content=?, file=? where num=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bdto.getTitle());
			pstmt.setString(2, bdto.getContent());
			pstmt.setString(3, bdto.getFile());
			pstmt.setInt(4, bdto.getNum());
			
			pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("UpdateBoard메소드 에서 에러 발생!: "+e);
		}finally{
			freeRes(con, pstmt);
			
		}
	}
	
	public void deleteBoard(int num){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = getCon();
			String sql = "delete from board where num=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch(Exception e){
			System.out.println("deleteBoard메소드 에서 에러 발생!: "+e);
		} finally{
			freeRes(con, pstmt);
		}
	}
	
	public void commentInsert(CommentDto cdto){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = getCon();
			String sql = "INSERT INTO comment(num,content, nickName,date) values(?,?,?,?)";
		
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cdto.getNum());
			pstmt.setString(2, cdto.getContent());
			pstmt.setString(3, cdto.getNickName());
			pstmt.setTimestamp(4, cdto.getDate());
			
			pstmt.executeUpdate();
		} catch(Exception e){
			System.out.println("commentInsert()메소드에서 오류 발생:"+e);
		} finally{
			freeRes(con, pstmt);
		}
	}
	public void commentDelete(int cNum){//num이 게시물번호, cNum이 코맨트 번호
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getCon();
			String sql = "delete from comment where cNum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cNum);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("commentDelete()메소드에서 오류 발생:"+e);
		} finally{
			freeRes(con, pstmt);
		}
	}

	public ArrayList<CommentDto> CommentList(int num){
		ArrayList<CommentDto> commentList = new ArrayList<CommentDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = getCon();
			String sql = "SELECT * FROM comment where num=?";
			pstmt= con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				CommentDto cdto = new CommentDto();
				cdto.setNum(rs.getInt("num"));
				cdto.setcNum(rs.getInt("cNum"));
				cdto.setNickName(rs.getString("nickName"));
				cdto.setContent(rs.getString("content"));
				cdto.setDate(rs.getTimestamp("date"));
				commentList.add(cdto);
			}
		} catch (Exception e) {
			System.out.println("CommentList(int num)메소드 에서 에러 발생!: "+e);
		} finally{			
			freeRes(con, pstmt);
		}		
		return commentList;
	}
}