package net.notice.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class noticeDao implements noticeMethod{
	//DB���� �޼ҵ�
	private Connection getConnection() throws Exception{
		Connection con=null;
		Context inti=new InitialContext();
		DataSource ds=(DataSource)inti.lookup("java:comp/env/jdbc/jspbeginner");
		con=ds.getConnection();
		return con;
	}//getConnection ��
	
	//�������� �� ��ü��� �ҷ����� �޼ҵ�
	@Override
	public ArrayList<noticeDto> AllNotice() {
		//DB���ѻ�
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<noticeDto> array = new ArrayList<noticeDto>();
		noticeDto dto;
		try {
			//DB���� 
			con = getConnection();
			//sql���� �ۼ�
			String sql = "select * from notice";
			//���� ����
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//�ݺ��� ���� rs���� ������ ���� dto�� ��� �ٽ� ��̸���Ʈ�� ���
			while (rs.next()) {
				dto = new noticeDto();
				dto.setNoticeNum(rs.getInt(1));
				dto.setNoticeSubject(rs.getString(2));
				dto.setNoticeContent(rs.getString(3));
				dto.setNoticeDate(rs.getTimestamp(4));
				dto.setNoticeImg(rs.getString(5));
				array.add(dto);
				
			}
			//�ڿ�����
			con.close();
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("AllNotice�޼ҵ� ���� :" +e);
		}
				
		
		
		return array;
	}// AllNotice ��

	//�������� �� ��ϸ޼ҵ�
	@Override
	public void InsertNotice(noticeDto ndto) {
		
		
	}

	//�������� �� �����޼ҵ�
	@Override
	public void UpdateNotice(noticeDto ndto) {
		
		
	}

	//�������� �� �����޼ҵ�
	@Override
	public void DeleteNotice(int num) {
		
		
	}

	//�������� �� �� �ϳ��� �� �ҷ����� �޼ҵ�
	@Override
	public noticeDto OneNotice(int noticeNum) {
		//DB���ѻ� 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		noticeDto dto = null;
		try {
			//DB����
			con = getConnection();
			//sql���� �ۼ�
			String sql = "select * from notice where noticeNum=?";
			//���� ����
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, noticeNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()){//���� ����� �˻� �Ǿ��ٸ�?
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
			System.out.println("OneNotice�޼ҵ� ���� :"+e);
		}
		
		return dto;
	}//OneNotice ��

}
