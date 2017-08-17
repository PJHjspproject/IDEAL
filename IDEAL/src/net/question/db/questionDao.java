package net.question.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class questionDao implements questionMethod{
	
	
	
	//DB���� �޼ҵ�
	private Connection getCon(){
		Connection con = null;
		
		try {
			
			//1. WAS������ ����� ��������Ʈ�� ��� ������ ������ �ִ� ���ؽ�Ʈ ��ü ����
			Context init = new InitialContext();
			//2. ����� WAS�������� DataSource(Ŀ�ؼ�Ǯ) �˻��ؼ� ��������
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/jspbeginner");
			//3. DataSource(Ŀ�ؼ�Ǯ)���� �̸� ����� DB������ü(Ŀ�ؼ�) ��������
			con = ds.getConnection();
			
		} catch (Exception err) {
			System.out.println("DB���� ���� : " + err);
		}
		return con;
	}//DB���� �޼ҵ� getcon ��
	
	//�ڿ����� �޼ҵ�
	public void freeRes(Connection con, PreparedStatement pstmt, ResultSet rs){
		if(con!=null){try{con.close();}catch(Exception e){e.printStackTrace();}}
		if(pstmt!=null){try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
		if(rs!=null){try{rs.close();}catch(Exception e){e.printStackTrace();}}
	}
	public void freeRes(Connection con, PreparedStatement pstmt){
		if(con!=null){try{con.close();}catch(Exception e){e.printStackTrace();}}
		if(pstmt!=null){try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
	}//�ڿ����� �޼ҵ� ��
	
	public int maxquestion(){//max�� ���ϱ�
		//db ���ѻ�
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
		}finally {
			freeRes(con, pstmt, rs);
		}
		return max;
	}
	
	
	//�ڱⰡ �� ������ ���� �ִ� �޼ҵ�
	public ArrayList<questionDto> AllQuestion(String nickName) {
		//db ���ѻ�
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//����Ÿ�Կ� �´� ���� �� ����
		ArrayList<questionDto> array = new ArrayList<questionDto>();
		try {
			//DB����
			con = getCon();
			//sql����
			String sql="select * from question where nickName=?";
			//sql���� ����
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickName);
			rs = pstmt.executeQuery();
			//�ݺ����� ����
			while (rs.next()) {
				//dto ����
				questionDto dto = new questionDto();
				//questionDto��ü�ȿ� RS�� ������ ���
				dto.setNum(rs.getInt(1));//�۹�ȣ
				dto.setTitle(rs.getString(2));//������
				dto.setNickName(rs.getString(3));//ȸ���̸�
				dto.setInputDate(rs.getTimestamp(4));//�����
				dto.setQuestionStatement(rs.getBoolean(5));//�亯����
				dto.setContent(rs.getString(6));
				dto.setReContent(rs.getString(7));
				//questionDto��ü�� ArrayList�� ���
				array.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("AllQuestion�޼ҵ� ����"+e);
		}finally {
			freeRes(con, pstmt, rs);
		}
		
		return array;
	}//AllQuestion�޼ҵ� ��
	
	//1:1���� �� �ۼ��޼ҵ�
	public int InsertQuestion(questionDto qdto) {
		//DB���ѻ�
		Connection con=null;
		PreparedStatement pstmt=null;
		int num = maxquestion();
		int result = 0;
		try {
			//DB����
			con = getCon();
			//sql���� �����
					
		 String sql = "insert into question (num,title,nickName,inputDate,questionStatement,content,reContent,image) "
					+ "values(?,?,?,?,?,?,?,?)";
			//�������� ����
		 	
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
			System.out.println("InsertQuestion�޼ҵ� ����"+e);
		}finally {
			freeRes(con, pstmt);
		}
		
		return result;
	}//InsertQuestion�޼ҵ� ��
	
	//1:1���� �� ���� �޼ҵ�
		public void UpdateQuestion(questionDto qdto) {
			//DB 2�ѻ�
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				//��񿬰�
				con = getCon();
				//sql���� �ۼ�
				String sql = "update question set title=?, inputDate=now(), content=?, image=? where num=?";
				//���� ����
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, qdto.getTitle());
				pstmt.setString(2, qdto.getContent());
				pstmt.setString(3, qdto.getImage());
				pstmt.setInt(4, qdto.getNum());
				pstmt.executeUpdate();
				
				
			} catch (Exception e) {
				System.out.println("UpdateQuestion�޼ҵ� ����"+e);
			} finally {
				freeRes(con, pstmt);
			}
		}//UpdateQuestion�޼ҵ� ��
	
	//1:1���� �� ���� �޼ҵ�
	public int DeleteQuestion(int num) {
		//DB���ѻ�
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			//��񿬰�
			con = getCon();
			//sql���� �ۼ�
			String sql = "delete from question where num =? ";
			//���� ����
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
						
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DeleteQuestion�޼ҵ� ����"+e);
		}finally {
			freeRes(con, pstmt);
		}
		
		return result;
		
	}
	//1:1���� �� �� �ϳ��� �۸� �ҷ����� �޼ҵ�
	public questionDto OneQuestion(int num) {
		//DB���ѻ�
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//������ dto ��ü ����
		questionDto dto = null;
		
		try {
			//DB����
			con = getCon();
			//sql���� �ۼ�
			String sql = "select * from question where num =?";
			//��������
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){//����˻��� ������ �ִٸ�
				//dto ��ü ����
				dto = new questionDto();
				//dto�� ����
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
		
	}//OneQuestion�޼ҵ� ��
	
	//1:1���� �� �亯 �޾��ִ� �޼ҵ�
	public void UpdateReContent(questionDto qdto) {
		//������ѻ�
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			//��񿬰�
			con = getCon();
			//sql���� �ۼ�
			String sql="update question set reContent=? where num=? ";
			
			//��������
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qdto.getReContent());
			pstmt.setInt(2, qdto.getNum());
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UpdateReContent�޼ҵ� ����"+e);
		}finally {
			freeRes(con, pstmt);
		}
			
	}//UpdateReContent �޼ҵ� ��

	
}
