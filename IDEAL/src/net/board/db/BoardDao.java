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
		//Connection ������ con 蹂���瑜� ����. 由ы�댄�댁＜湲곗����.
		Connection con = null;
		
		//Context������ init蹂������� InitialContext������ 媛�泥대� ���깊���� �대����
		Context init = new InitialContext();
		
		//init媛�泥대� lookup(�대�)���� datasource(而ㅻ�μ����)濡� �닿�
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/jspbeginner");
		
		//而ㅻ�μ������ �댁�⑺���� getConnection()硫����� �몄����� �곌껐�� db媛�泥대�� ����
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
			//DB占쎈염野�占�
			con = getCon();
			//SQL���겸�� �닌�揆 占쎌��占쎄쉐
			String sql = "SELECT * FROM board order by num desc limit ?,?";
			//���겸��占쎌�� 占쎈��占쎈뻬�얜��� pstmt揶��밴�占쎈� 占쎈��占쎈��占쎈��.
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, pageSize);
			//���겸��占쎌�� 占쎈��占쎈뻬揶�誘れ�� rs占쎈� 占쎈��占쎈��占쎈��
			rs = pstmt.executeQuery();
			
			// rs占쎈� 揶�誘れ�� 占쎌�놂옙��筌�占�
			while(rs.next()){
				BoardDto bdto = new BoardDto();
				
				// rs占쎈� 占쎄�占쎌��占쎈릭占쎈연 占쎈��占쎌����遺용�占쎈� 占쎌�놂옙�� 揶�誘れ�� 
				bdto.setNum(rs.getInt("num"));
				bdto.setContent(rs.getString("content"));
				bdto.setDate(rs.getTimestamp("date"));
				bdto.setFile(rs.getString("file"));
				bdto.setNickName(rs.getString("nickName"));
				bdto.setTitle(rs.getString("title"));
				
				BoardList.add(bdto);
			}
			
		} catch (Exception e) {
			System.out.println("BoardList筌�遺용�쇽옙諭� 占쎄땀�븝옙占쎈�占쎄� 占쎌�ㅷ��占� : "+e);
		} finally{
			if(con!=null){try{con.close();}catch(Exception e){e.printStackTrace();}}
			if(pstmt!=null){try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
			if(rs!=null){try{rs.close();}catch(Exception e){e.printStackTrace();}}
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
/*			System.out.println("BoardAdd");
			System.out.println(bdto.getTitle());
			System.out.println(bdto.getContent());
			System.out.println(bdto.getFile());
			System.out.println(bdto.getDate());
			System.out.println(bdto.getNickName());*/

			pstmt.executeUpdate();
			
			
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			if(con!=null){try{con.close();}catch(Exception e){e.printStackTrace();}}
			if(pstmt!=null){try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
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
			e.printStackTrace();
		} finally {
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
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
			e.printStackTrace();
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
			e.printStackTrace();
		} finally{
			if(con!=null)try{con.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
		}
	}
	
	public void commentInsert(CommentDto cdto){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		// request.getParameter() -> 由ы�댄���� String
		// request.getAttribute() -> 由ы�댄���� Object
		
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
			e.printStackTrace();
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
	
	//��洹쇱������� �몃���洹쇱�� 媛��ν��寃���怨�...
	//由ъ�ㅽ�몃�� 肉��ㅼ＜湲곗����... 由ы�닿��� ������寃�媛���..
	//由ъ�ㅽ�� ���� ���ㅻ┃������ 臾댁���� 諛����쇳��源�... �닿�肉��ㅼ＜怨� �띠�� 寃��� ��媛���硫� �듭�대����
	//洹몃���ㅻ㈃... 臾댁���� 留ㅺ�蹂���濡� 諛��� 由ъ�ㅽ�몃�� 肉��ㅼ＜怨� sql臾몄�� ���깊��源�...
	
	public ArrayList<CommentDto> CommentList(int num){
		ArrayList<CommentDto> commentList = new ArrayList<CommentDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// DB�곌껐
			con = getCon();
			// SQL荑쇰━ 援щЦ ����
			String sql = "SELECT * FROM comment where num=?";
			
			// 荑쇰━�� �ㅽ��臾몄�� pstmt媛�泥댁�� �대����.
			pstmt= con.prepareStatement(sql);
			// num�� ?媛��� Int���쇰� �명���댁���? => ��???
			// 寃���湲��� num媛��� 萸�吏� ������硫�  ������ 踰��몃�� �명���댁���?
			pstmt.setInt(1, num);
			
			// 荑쇰━�� �ㅽ��媛��� rs�� �대����.
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				CommentDto cdto = new CommentDto();
				// CommentDto媛�泥댁�� 荑쇰━�ㅽ��媛��� �닿릿 rs���� num媛��� 媛��몄���� �명������?
				cdto.setNum(rs.getInt("num"));
				cdto.setcNum(rs.getInt("cNum"));
				cdto.setNickName(rs.getString("nickName"));
				cdto.setContent(rs.getString("content"));
				cdto.setDate(rs.getTimestamp("date"));
				
				// ArrayList 李몄“蹂����� commentList�� cdto�� ����(?) 媛��ㅼ�� ���댁���? �ｌ�댁���?
				commentList.add(cdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{			
			freeRes(con, pstmt);
		}		
		return commentList;
	}
}